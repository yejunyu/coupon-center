package com.yejunyu.coupon.customer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/12
 * @Description: TODO
 **/
@SpringBootTest
public class Test1 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test1() {
        String msg = "send test!!!";

        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.sms", msg);
    }
}
