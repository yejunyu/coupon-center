package com.yejunyu.coupon.customer;

import com.yejunyu.coupon.customer.feign.TemplateService;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/21
 * @Description: TODO
 **/
@Slf4j
@Component
public class TemplateServiceFallbackFactory implements FallbackFactory<TemplateService> {
    @Override
    public TemplateService create(Throwable cause) {
        return new TemplateService() {
            @Override
            public CouponTemplateInfo getTemplate(Long id) {
                log.info("fallback factory method test");
                return null;
            }

            @Override
            public Map<Long, CouponTemplateInfo> getTemplateBatch(Collection<Long> ids) {
                log.info("fallback factory method test");
                return null;
            }
        };
    }
}
