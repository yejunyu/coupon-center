package com.yejunyu.coupon.customer.service;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import com.yejunyu.coupon.customer.api.beans.request.CouponReq;
import com.yejunyu.coupon.customer.api.beans.request.SearchCouponReq;
import com.yejunyu.coupon.customer.dao.entity.Coupon;
import com.yejunyu.coupon.template.api.beans.CouponInfo;

import java.util.List;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
public interface CouponCustomerService {
    /**
     * 领券接口
     *
     * @param request req
     * @return 优惠券
     */
    Coupon requestCoupon(CouponReq request);

    /**
     * 核销优惠券
     *
     * @param cart
     * @return
     */
    ShoppingCart placeOrder(ShoppingCart cart);

    /**
     * 价格试算
     *
     * @param order
     * @return
     */
    SimulationResponse simulateOrderPrice(SimulationOrder order);

    void deleteCoupon(Long uid, Long couponId);

    /**
     * 查询用户优惠券
     *
     * @param request
     * @return
     */
    List<CouponInfo> findCoupon(SearchCouponReq request);

    /**
     * 删除模板
     *
     * @param templateId
     */
    void deleteCouponTemplate(Long templateId);
}
