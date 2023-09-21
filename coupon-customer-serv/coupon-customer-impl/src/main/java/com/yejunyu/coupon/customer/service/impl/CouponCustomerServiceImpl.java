package com.yejunyu.coupon.customer.service.impl;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import com.yejunyu.coupon.customer.api.beans.enums.CouponStatus;
import com.yejunyu.coupon.customer.api.beans.request.CouponReq;
import com.yejunyu.coupon.customer.api.beans.request.SearchCouponReq;
import com.yejunyu.coupon.customer.converter.CouponConverter;
import com.yejunyu.coupon.customer.dao.CouponDao;
import com.yejunyu.coupon.customer.dao.entity.Coupon;
import com.yejunyu.coupon.customer.feign.CalculationService;
import com.yejunyu.coupon.customer.feign.TemplateService;
import com.yejunyu.coupon.customer.service.CouponCustomerService;
import com.yejunyu.coupon.template.api.beans.CouponInfo;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import com.yejunyu.coupon.template.api.beans.rules.TemplateRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Slf4j
@Service
public class CouponCustomerServiceImpl implements CouponCustomerService {
    @Resource
    private CouponDao couponDao;
    @Resource
    private WebClient.Builder clientBuilder;
    @Resource
    private CalculationService calculationService;
    @Resource
    private TemplateService templateService;


    @Override
    public Coupon requestCoupon(CouponReq request) {
        CouponTemplateInfo couponTemplateInfo = templateService.getTemplate(request.getCouponTemplateId());
//        CouponTemplateInfo couponTemplateInfo = loadTemplateInfo(request.getCouponTemplateId());

        // 模板不存在
        if (couponTemplateInfo == null) {
            log.error("invalid template id={}", request.getCouponTemplateId());
            throw new IllegalArgumentException("Invalid template id");
        }
        // 模板过期了
        LocalDateTime now = LocalDateTime.now();
        long epochSecond = now.toEpochSecond(ZoneOffset.ofHours(8));
        TemplateRule rule = couponTemplateInfo.getRule();
        if ((rule != null && epochSecond > Optional.ofNullable(rule.getDeadline()).orElse(Long.MAX_VALUE))
                || !couponTemplateInfo.getAvailable()) {
            log.error("template is not available id={}", request.getCouponTemplateId());
            throw new IllegalArgumentException("template is unavailable");
        }
        // 用户领券数量超过上限
        long count = couponDao.countByUidAndTemplateId(request.getUid(), couponTemplateInfo.getId());
        if (count > Objects.requireNonNull(rule).getLimitation()) {
            log.error("exceeds maximum number");
            throw new IllegalArgumentException("exceeds maximum number");
        }
        Coupon coupon = Coupon.builder()
                .couponTemplateInfo(couponTemplateInfo)
                .status(CouponStatus.AVAILABLE)
                .uid(request.getUid())
                .templateId(couponTemplateInfo.getId())
                .shopId(couponTemplateInfo.getShopId())
                .build();
        return couponDao.save(coupon);
    }

    @Transactional
    @Override
    public ShoppingCart placeOrder(ShoppingCart cart) {
        // fixme 这里放controller去校验更好
        if (CollectionUtils.isEmpty(cart.getProducts())) {
            log.error("invalid check out request, order={}", cart);
            throw new IllegalArgumentException("cart if empty");
        }
        Coupon coupon = null;
        if (cart.getCouponId() != null) {
            // 查询用户优惠券信息
            Coupon example = Coupon.builder()
                    .id(cart.getCouponId())
                    .uid(cart.getUid())
                    .status(CouponStatus.AVAILABLE)
                    .build();
            coupon = couponDao.findAll(Example.of(example)).stream().findFirst().orElseThrow(() -> new RuntimeException("coupon not found"));
            CouponInfo couponInfo = CouponConverter.convertToCouponInfo(coupon);
            CouponTemplateInfo couponTemplateInfo = templateService.getTemplate(couponInfo.getTemplateId());
//            CouponTemplateInfo couponTemplateInfo = loadTemplateInfo(couponInfo.getTemplateId());
            couponInfo.setTemplate(couponTemplateInfo);
            cart.setCouponInfos(Collections.singletonList(couponInfo));
        }
        // order清算
        ShoppingCart checkout = calculationService.calculateOrderPrice(cart);
//        ShoppingCart checkout = clientBuilder.build()
//                .post()
//                .uri("http://coupon-calculation-serv/calculation/checkout")
//                .bodyValue(cart)
//                .retrieve()
//                .bodyToMono(ShoppingCart.class)
//                .block();
        if (coupon != null) {
            // couponInfos为空说明并没有结算优惠券,不符合优惠券结算的条件
            if (CollectionUtils.isEmpty(checkout.getCouponInfos())) {
                log.error("cannot apply coupon to order, couponId={}", coupon.getId());
                throw new IllegalArgumentException("coupon is not applicable to this order");
            }
            log.info("update coupon status to used, couponId={}", coupon.getId());
            coupon.setStatus(CouponStatus.USED);
            couponDao.save(coupon);
        }
        return checkout;
    }

    @Override
    public SimulationResponse simulateOrderPrice(SimulationOrder order) {
        List<CouponInfo> couponInfos = new ArrayList<>();
        List<Coupon> coupons = couponDao.findAllById(order.getCouponIds());
        for (Coupon coupon : coupons) {
            CouponInfo couponInfo = CouponConverter.convertToCouponInfo(coupon);
//            couponInfo.setTemplate(loadTemplateInfo(couponInfo.getTemplateId()));
            couponInfo.setTemplate(templateService.getTemplate(couponInfo.getTemplateId()));
            couponInfos.add(couponInfo);
        }
        order.setCouponInfos(couponInfos);
        // 调用试算接口
        return calculationService.simulate(order);
//        return clientBuilder.build()
//                .post()
//                .uri("http://coupon-calculation-serv/calculation/simulate")
//                .bodyValue(order)
//                .retrieve()
//                .bodyToMono(SimulationResponse.class)
//                .block();
    }

    @Override
    public void deleteCoupon(Long uid, Long couponId) {
        Optional<Coupon> byId = couponDao.findByIdAndUid(couponId, uid);
        Coupon coupon = byId.orElseThrow(() -> new RuntimeException("could not find available coupon"));
        coupon.setStatus(CouponStatus.INACTIVE);
        couponDao.save(coupon);
    }

//    @ServiceActivator(inputChannel = "delete-coupon-topic.delete-coupon-group.errors")
//    public void deleteCouponFallback(ErrorMessage errorMessage) throws Exception {
//        log.info("consumer error:{}", errorMessage);
//        // 后面实现自己的降级逻辑
//
//    }

    @Override
    public List<CouponInfo> findCoupon(SearchCouponReq request) {
        Coupon coupon = Coupon.builder()
                .uid(request.getUid())
                .shopId(request.getShopId())
                .status(CouponStatus.convert(request.getCouponStatus()))
                .build();
        List<Coupon> couponList = couponDao.findAll(Example.of(coupon));
//        String templateIds = couponList.stream()
//                .map(Coupon::getTemplateId)
//                .map(String::valueOf)
//                .distinct()
//                .collect(Collectors.joining(","));
        List<Long> templateIds = couponList.stream()
                .map(Coupon::getTemplateId)
                .distinct()
                .toList();
        Map<Long, CouponTemplateInfo> templateInfoMap = templateService.getTemplateBatch(templateIds);
//        Map<Long, CouponTemplateInfo> templateInfoMap = clientBuilder.build()
//                .post()
//                .uri("http://coupon-template-serv/template/getBatch")
//                .body(BodyInserters.fromFormData("ids", templateIds))
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<Long, CouponTemplateInfo>>() {
//                })
//                .block();
        couponList.forEach(c -> c.setCouponTemplateInfo(templateInfoMap.get(c.getTemplateId())));
        return couponList.stream().map(CouponConverter::convertToCouponInfo).toList();
    }

//    private CouponTemplateInfo loadTemplateInfo(Long templateId) {
//        return clientBuilder.build()
//                .post()
//                .uri("http://coupon-template-serv/template/getTemplate")
//                .body(BodyInserters.fromFormData("id", String.valueOf(templateId)))
//                .retrieve()
//                .bodyToMono(CouponTemplateInfo.class)
//                .block();
//    }
}
