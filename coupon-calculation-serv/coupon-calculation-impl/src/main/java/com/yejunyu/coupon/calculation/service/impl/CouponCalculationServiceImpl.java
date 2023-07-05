package com.yejunyu.coupon.calculation.service.impl;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import com.yejunyu.coupon.calculation.service.CouponCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: TODO
 **/
@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Override
    public ShoppingCart calculateOrderPrice(ShoppingCart cart) {
        log.info("CouponCalculationServiceImpl#calculateOrderPrice cart={}", cart);

        return null;
    }

    @Override
    public SimulationResponse simulate(SimulationOrder simulationOrder) {
        return null;
    }
}
