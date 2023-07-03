package com.yejunyu.coupon.template.service.impl;

import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import com.yejunyu.coupon.template.api.enums.CouponType;
import com.yejunyu.coupon.template.converter.CouponTemplateConverter;
import com.yejunyu.coupon.template.dao.CouponTemplateDao;
import com.yejunyu.coupon.template.dao.entity.CouponTemplate;
import com.yejunyu.coupon.template.service.CouponTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private static final int COUPON_NUM_LIMIT = 100;

    @Resource
    private CouponTemplateDao couponTemplateDao;

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        Integer count = couponTemplateDao.countByShopIdAndAvailable(request.getShopId(), true);
        if (count >= COUPON_NUM_LIMIT) {
            throw new RuntimeException("");
        }
        CouponTemplate template = CouponTemplate.builder()
                .name(request.getName())
                .desc(request.getDesc())
                .shopId(request.getShopId())
                .type(CouponType.convert(request.getType()))
                .rule(request.getRule())
                .available(true)
                .build();
        template = couponTemplateDao.save(template);
        return CouponTemplateConverter.convertToCouponTemplateInfo(template);
    }

    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        return null;
    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        return null;
    }

    @Override
    public void deleteTemplate(Long id) {

    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        return null;
    }
}
