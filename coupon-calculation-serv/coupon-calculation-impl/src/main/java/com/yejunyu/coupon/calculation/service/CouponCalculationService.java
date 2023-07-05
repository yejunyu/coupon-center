package com.yejunyu.coupon.calculation.service;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: TODO
 **/
public interface CouponCalculationService {

    /**
     * 优惠券结算
     * 通过factory类决定使用哪个rule去计算金额
     *
     * @param cart 购物车
     * @return 结算后的购物车
     */
    ShoppingCart calculateOrderPrice(ShoppingCart cart);

    /**
     * 试计算每个优惠券使用后的订单价格
     *
     * @param simulationOrder order
     * @return 试计算后的结果
     */
    SimulationResponse simulate(SimulationOrder simulationOrder);
}
