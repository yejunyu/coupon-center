package com.yejunyu.coupon.calculation.api.beans;

import com.yejunyu.coupon.template.api.beans.CouponInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: 购物车
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    // 购物车商品列表
    @NotEmpty
    private List<Product> products;
    /**
     * 封装优惠券信息(暂时只计算一张)
     * 为了扩展性(多张优惠券组合),定义成list
     */
    private Long couponId;
    private List<CouponInfo> couponInfos;
    // 最终价格
    private Long cost;
    @NotNull
    private Long uid;
}
