package com.yejunyu.coupon.customer.event;

import com.yejunyu.coupon.customer.api.beans.request.CouponReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/18
 * @Description: TODO
 **/
@Service
@Slf4j
public class CouponProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void sendCoupon(CouponReq req) {
        log.info("sent: {}", req);
        streamBridge.send(EventConstant.ADD_COUPON_EVENT, req);
    }

    public void deleteCoupon(Long uid, Long couponId) {
        log.info("sent delete coupon event: uid:{},couponId:{}", uid, couponId);
        streamBridge.send(EventConstant.DELETE_COUPON_EVENT, uid + "," + couponId);
    }
}
