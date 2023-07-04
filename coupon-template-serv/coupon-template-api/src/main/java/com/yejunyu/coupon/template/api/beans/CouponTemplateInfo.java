package com.yejunyu.coupon.template.api.beans;

import com.yejunyu.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: 优惠券模板，用于装配优惠券详细信息
 * @see CouponInfo
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {
    private Long id;
    // 优惠券名称
    @NotNull
    private String name;
    // 优惠券描述
    @NotNull
    private String description;
    /**
     * 优惠券类型
     *
     * @see com.yejunyu.coupon.template.api.enums.CouponType
     */
    @NotNull
    private String type;
    // 优惠券适用的门店， 若无则全店通用
    private Long shopId;
    // 优惠券使用规则
    @NotNull
    private TemplateRule rule;
    // 当前模板是否可用
    private Boolean available;

}
