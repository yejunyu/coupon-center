package com.yejunyu.coupon.template.dao;

import com.yejunyu.coupon.template.dao.entity.CouponTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: jpa
 * example 方式查询
 * couponTemplate.setName(" 查询名称 ");
 * templateDao.findAll(Example.of(couponTemplate));
 **/
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {
    /**
     * 根据shopid查出所有券的模板
     *
     * @param shopId id
     * @return 模板
     */
    List<CouponTemplate> findAllByShopId(Long shopId);

    /**
     * in查询，分页
     *
     * @param id       id
     * @param pageable 分页
     * @return 模板
     */
    Page<CouponTemplate> findAllByIdIn(List<Long> id, Pageable pageable);

    /**
     * 根据shopid查询店铺有多少券的模板
     *
     * @param shopId    id
     * @param available 可用
     * @return 模板数量
     */
    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    /**
     * 将优惠券设置为不可用
     *
     * @param id id
     * @return
     */
    @Modifying
    @Query("update CouponTemplate c set c.available=0 where c.id=:id")
    int makeCouponUnavailable(@Param("id") Long id);
}
