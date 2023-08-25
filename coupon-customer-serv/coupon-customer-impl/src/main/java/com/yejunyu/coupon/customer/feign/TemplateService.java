package com.yejunyu.coupon.customer.feign;

import com.yejunyu.coupon.TemplateServiceFallbackFactory;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/20
 * @Description: TODO
 **/
@FeignClient(value = "coupon-template-serv", path = "/template",
        fallbackFactory = TemplateServiceFallbackFactory.class)
public interface TemplateService {

    /**
     * 获取优惠券模板
     *
     * @param id
     * @return
     */
    @PostMapping("/getTemplate")
    CouponTemplateInfo getTemplate(@RequestParam Long id);

    /**
     * 获取优惠券模板(批量)
     *
     * @param ids
     * @return
     */
    @PostMapping("/getBatch")
    Map<Long, CouponTemplateInfo> getTemplateBatch(@RequestParam Collection<Long> ids);
}
