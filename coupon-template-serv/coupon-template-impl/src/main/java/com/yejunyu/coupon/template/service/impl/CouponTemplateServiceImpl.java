package com.yejunyu.coupon.template.service.impl;

import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import com.yejunyu.coupon.template.api.beans.request.PagedCouponTemplateInfo;
import com.yejunyu.coupon.template.api.beans.request.TemplateSearchParams;
import com.yejunyu.coupon.template.api.enums.CouponType;
import com.yejunyu.coupon.template.converter.CouponTemplateConverter;
import com.yejunyu.coupon.template.dao.CouponTemplateDao;
import com.yejunyu.coupon.template.dao.entity.CouponTemplate;
import com.yejunyu.coupon.template.service.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private static final int COUPON_NUM_LIMIT = 100;

    @Resource
    private CouponTemplateDao couponTemplateDao;

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        Integer count = couponTemplateDao.countByShopIdAndAvailable(request.getShopId(), true);
        if (count >= COUPON_NUM_LIMIT) {
            log.error("the totals of coupon template exceeds maximum number");
            throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
        }
        CouponTemplate template = CouponTemplate.builder()
                .name(request.getName())
                .desc(request.getDesc())
                .shopId(request.getShopId())
                .type(CouponType.convert(request.getType()))
                .rule(request.getRule())
                .available(true)
                .build();
        template = couponTemplateDao.save(template);

        return CouponTemplateConverter.convertToCouponTemplateInfo(template);
    }

    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        Optional<CouponTemplate> template = couponTemplateDao.findById(id);
        return template.map(CouponTemplateConverter::convertToCouponTemplateInfo).orElse(null);
    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("cloning template id {}", templateId);
        CouponTemplate source = couponTemplateDao.findById(templateId).orElseThrow(() -> new IllegalArgumentException("invalid template id"));
        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);
        target.setAvailable(true);
        target.setId(null);
        couponTemplateDao.save(target);
        return CouponTemplateConverter.convertToCouponTemplateInfo(target);
    }

    @Override
    public void deleteTemplate(Long id) {
        int rows = couponTemplateDao.makeCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }
    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> templates = couponTemplateDao.findAllById(ids);
        return templates.stream().map(CouponTemplateConverter::convertToCouponTemplateInfo)
                .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }

    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        return null;
    }
}
