package com.yejunyu.coupon.customer.dao;

import com.yejunyu.coupon.customer.dao.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
public interface CouponDao extends JpaRepository<Coupon, Long> {
    /**
     * 统计用户领取了多少张优惠券
     *
     * @param uid        uid
     * @param templateId 模板id
     * @return 统计几张
     */
    long countByUidAndTemplateId(Long uid, Long templateId);

    /**
     * @param id
     * @param uid
     * @return
     */
    Optional<Coupon> findByIdAndUid(Long id, Long uid);
}
