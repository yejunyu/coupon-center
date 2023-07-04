package com.yejunyu.coupon.template.dao.entity;

import com.yejunyu.coupon.template.api.beans.rules.TemplateRule;
import com.yejunyu.coupon.template.api.enums.CouponType;
import com.yejunyu.coupon.template.dao.converter.CouponTypeConverter;
import com.yejunyu.coupon.template.dao.converter.RuleConverter;
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
 * @Date: Created in 2023/7/3
 * @Description: 优惠券模板表
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
// 用来捕获监听信息，当entity发生持久化和更新操作时
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate {

    @Id
    // 自增id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 状态是否可用
    @Column(name = "available", nullable = false)
    private Boolean available;

    // 优惠券名
    @Column(name = "name", nullable = false)
    private String name;

    // 优惠券描述
    @Column(name = "description", nullable = false)
    private String description;
    /**
     * 优惠券类型
     *
     * @see com.yejunyu.coupon.template.api.enums.CouponType
     */
    @Column(name = "type", nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType type;

    // 优惠券适用的门店， 若无则全店通用
    @Column(name = "shop_id")
    private Long shopId;

    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Column(name = "rule", nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;
}
