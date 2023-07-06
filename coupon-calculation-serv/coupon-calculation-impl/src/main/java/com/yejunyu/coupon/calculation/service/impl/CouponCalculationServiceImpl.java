package com.yejunyu.coupon.calculation.service.impl;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import com.yejunyu.coupon.calculation.service.CouponCalculationService;
import com.yejunyu.coupon.calculation.template.CouponTemplateFactory;
import com.yejunyu.coupon.calculation.template.RuleTemplate;
import com.yejunyu.coupon.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: TODO
 **/
@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Resource
    private CouponTemplateFactory couponTemplateFactory;


    @Override
    public ShoppingCart calculateOrderPrice(ShoppingCart cart) {
        log.info("CouponCalculationServiceImpl#calculateOrderPrice cart={}", cart);
        RuleTemplate template = couponTemplateFactory.getTemplate(cart);
        return template.calculate(cart);
    }

    /**
     * 对所有优惠券试计算,看哪个最省钱
     *
     * @param simulationOrder order
     * @return 模拟结果
     */
    @Override
    public SimulationResponse simulate(SimulationOrder simulationOrder) {
        SimulationResponse simulationResponse = new SimulationResponse();
        Long minCost = Long.MAX_VALUE;
        for (CouponInfo couponInfo : simulationOrder.getCouponInfos()) {
            ShoppingCart cart = new ShoppingCart();
            cart.setCouponId(couponInfo.getId());
            cart.setProducts(simulationOrder.getProducts());
            cart.setCouponInfos(Collections.singletonList(couponInfo));
            cart = calculateOrderPrice(cart);
            // 设置当前的计算结果
            simulationResponse.getCouponToOrderPrice()
                    .put(couponInfo.getId(), cart.getCost());
            if (cart.getCost() < minCost) {
                minCost = cart.getCost();
                simulationResponse.setBestCouponId(couponInfo.getId());
            }
        }
        return simulationResponse;
    }
}
