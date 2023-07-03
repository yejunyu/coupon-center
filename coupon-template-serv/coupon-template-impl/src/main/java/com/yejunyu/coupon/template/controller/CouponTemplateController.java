package com.yejunyu.coupon.template.controller;

import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import com.yejunyu.coupon.template.service.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@Slf4j
@RestController
@RequestMapping("/template")
public class CouponTemplateController {

    @Resource
    private CouponTemplateService couponTemplateService;

    /**
     * 创建优惠券模板
     * @param request
     * @return
     */
    @PostMapping("/addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("create coupon template: data={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * clone优惠券模板
     * @param templateId
     * @return
     */
    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("id") Long templateId) {
        log.info("clone coupon template: data={}", templateId);
        return couponTemplateService.cloneTemplate(templateId);
    }

    /**
     * clone优惠券模板
     * @param id
     * @return
     */
    @PostMapping("/getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
        log.info("get coupon template: data={}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }
}
