package com.yejunyu.coupon.gateway.dynamic;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/18
 * @Description: TODO
 **/
@Component
@Slf4j
public class DynamicRoutesListener implements Listener {
    @Autowired
    private gatewayService gatewayService;

    @Override
    public Executor getExecutor() {
        return null;
    }

    /**
     * DynamicRoutesListener 实现了 Listener 接口，后者是 Nacos Config 提供的标准监听
     * 器接口，当被监听的 Nacos 配置文件发生变化的时候，框架会自动调用
     * receiveConfigInfo 方法执行自定义逻辑。在这段方法里，我将接收到的文本对象
     * configInfo 转换成了 List类，并调用 GatewayService 完成路由表的更新。
     *
     * @param s
     */
    // 用字符串存routes的配置
    @Override
    public void receiveConfigInfo(String s) {
        List<RouteDefinition> definitions = JSON.parseArray(s, RouteDefinition.class);
        gatewayService.updateRoutes(definitions);
    }
}
