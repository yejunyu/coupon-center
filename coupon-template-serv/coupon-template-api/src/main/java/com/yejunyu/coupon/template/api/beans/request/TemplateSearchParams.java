package com.yejunyu.coupon.template.api.beans.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: 查询参数
 * @see com.yejunyu.coupon.template.api.beans.CouponTemplateInfo
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateSearchParams {

    private Long id;
    private String name;
    private String type;
    private Long shopId;
    private Boolean available;
    private int page;
    private int pageSize;
}
