package com.yejunyu.coupon.template.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.google.common.collect.Maps;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import com.yejunyu.coupon.template.api.beans.request.PagedCouponTemplateInfoReq;
import com.yejunyu.coupon.template.api.beans.request.TemplateSearchReq;
import com.yejunyu.coupon.template.service.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

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
     *
     * @param request
     * @return
     */
    @PostMapping("/addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("CouponTemplateController#addTemplate: data={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * clone优惠券模板
     *
     * @param id
     * @return
     */
    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam Long id) {
        log.info("CouponTemplateController#cloneTemplate: data={}", id);
        return couponTemplateService.cloneTemplate(id);
    }

    /**
     * 获取优惠券模板
     *
     * @param id
     * @return
     */
    @PostMapping("/getTemplate")
    @SentinelResource(value = "getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam Long id) {
        log.info("CouponTemplateController#getTemplate: data={}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }

    /**
     * 获取优惠券模板(批量)
     *
     * @param ids
     * @return
     */
    @PostMapping("/getBatch")
    @SentinelResource(value = "getTemplateBatch", blockHandler = "getTemplateBatchBlock")
    public Map<Long, CouponTemplateInfo> getTemplateBatch(@RequestParam Collection<Long> ids) {
        log.info("CouponTemplateController#getTemplateBatch: data={}", ids);
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    public Map<Long, CouponTemplateInfo> getTemplateBatchBlock(@RequestParam Collection<Long> ids, BlockException exception) {
        log.info("接口被限流", exception);
        return Maps.newHashMap();
    }

    /**
     * 搜索模板(分页)
     *
     * @param request
     * @return
     */
    @PostMapping("/search")
    public PagedCouponTemplateInfoReq search(@Valid @RequestBody TemplateSearchReq request) {
        log.info("CouponTemplateController#search: data={}", request);
        return couponTemplateService.search(request);
    }

    /**
     * 搜索模板(分页)
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteTemplate")
    public void deleteTemplate(@RequestParam Long id) {
        log.info("CouponTemplateController#deleteTemplate: data={}", id);
        couponTemplateService.deleteTemplate(id);
    }
}
