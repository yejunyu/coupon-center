package com.yejunyu.coupon.customer.dao.entity;

import com.yejunyu.coupon.customer.api.beans.enums.CouponStatus;
import com.yejunyu.coupon.customer.dao.converter.CouponStatusConverter;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
public class Coupon {
    @Id
    // 自增id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 模板id
    @Column(name = "template_id", nullable = false)
    private Long templateId;

    // uid
    @Column(name = "uid", nullable = false)
    private Long uid;

    // shop id 冗余一下方便查询
    @Column(name = "shop_id")
    private Long shopId;
    /**
     * 优惠券状态
     *
     * @see com.yejunyu.coupon.customer.api.beans.enums.CouponStatus
     */
    @Column(name = "status", nullable = false)
    @Convert(converter = CouponStatusConverter.class)
    private CouponStatus status;

    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Transient
    private CouponTemplateInfo couponTemplateInfo;
}
