package com.yejunyu.coupon.template.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/7
 * @Description: TODO
 **/
@Component
@Slf4j
public class SentinelOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        log.info("request:{}, header:{}", httpServletRequest.getParameterMap(), httpServletRequest.getHeaderNames());
        return httpServletRequest.getHeader("SentinelSource");
    }
}
