package com.yejunyu.coupon.template.service;

import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
public interface CouponTemplateService {

    /**
     * 创建优惠券模板
     *
     * @param request
     * @return
     */
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    /**
     * 通过模板id查询优惠券模板
     *
     * @param id
     * @return
     */
    CouponTemplateInfo loadTemplateInfo(Long id);

    /**
     * 克隆优惠券模板
     *
     * @param templateId
     * @return
     */
    CouponTemplateInfo cloneTemplate(Long templateId);

    /**
     * 删除优惠券模板
     *
     * @param id
     */
    void deleteTemplate(Long id);

    /**
     * 批量读取模板
     *
     * @param ids
     * @return
     */
    Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);
}
