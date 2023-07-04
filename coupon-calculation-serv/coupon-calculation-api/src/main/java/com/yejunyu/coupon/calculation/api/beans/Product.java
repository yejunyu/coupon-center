package com.yejunyu.coupon.calculation.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: 商品
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // 价格,单位分
    private Long price;
    private Integer count;
    private Long shopId;
}
