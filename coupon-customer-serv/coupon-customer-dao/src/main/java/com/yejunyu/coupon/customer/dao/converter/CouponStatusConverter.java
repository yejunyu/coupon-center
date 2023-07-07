package com.yejunyu.coupon.customer.dao.converter;

import com.yejunyu.coupon.customer.api.beans.enums.CouponStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Converter
public class CouponStatusConverter implements AttributeConverter<CouponStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CouponStatus couponStatus) {
        return couponStatus.getCode();
    }

    @Override
    public CouponStatus convertToEntityAttribute(Integer code) {
        return CouponStatus.convert(code);
    }
}
