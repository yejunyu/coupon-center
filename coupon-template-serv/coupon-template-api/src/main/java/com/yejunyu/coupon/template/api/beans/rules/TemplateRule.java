package com.yejunyu.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: 优惠券规则模板
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {
    // 可以享受的折扣
    private Discount discount;
    // 每个人最多可以领券数量
    private Integer limitation;
    // 过期时间 时间戳(s)
    private Long deadline;
}
