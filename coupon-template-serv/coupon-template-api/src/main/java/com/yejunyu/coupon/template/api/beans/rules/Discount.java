package com.yejunyu.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: 优惠券折扣
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    /**
     * 对于满减券： quota是减掉的钱数，单位是分
     * 对于打折券： quota是折扣（100是原价，90就是9折）
     * 对于随机立减券： quota是最高的随机立减额
     * 对于晚间特别优惠： quota是日间优惠额，晚间优惠翻倍
     */
    private Long quota;
    // 订单最低要到多少钱才能使用优惠券折扣，单位为分
    private Long threshold;
}
