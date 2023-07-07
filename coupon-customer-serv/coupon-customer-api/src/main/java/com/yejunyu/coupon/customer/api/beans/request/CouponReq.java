package com.yejunyu.coupon.customer.api.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponReq {
    // 领券的用户
    @NotNull
    private Long uid;

    // 券的模板id
    @NotNull
    private Long couponTemplateId;
}
