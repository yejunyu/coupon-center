package com.yejunyu.coupon.gateway.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/18
 * @Description: TODO
 **/
@Service
@Slf4j
public class gatewayService {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 这段代码接收了一个 RouteDefinition List 对象作为入参，它是 Gateway 网关组件用来封
     * 装路由规则的标准类，在里面包含了谓词、过滤器和 metadata 等一系列构造路由规则所
     * 需要的元素。在主体逻辑部分，我调用了 Gateway 内置的路由编辑类
     * RouteDefinitionWriter，将路由规则写入上下文，再调用 ApplicationEventPublisher 类
     * 发布一个路由刷新事件。
     *
     * @param routes
     */
    public void updateRoutes(List<RouteDefinition> routes) {
        if (CollectionUtils.isEmpty(routes)) {
            // no routers
            return;
        }
        routes.forEach(r -> {
            try {
                routeDefinitionWriter.save(Mono.just(r)).subscribe();
                if (r.getMetadata() != null && r.getMetadata().get("delete").equals(1)) {
                    routeDefinitionWriter.delete(Mono.just(r.getId())).subscribe();
                }
                publisher.publishEvent(new RefreshRoutesEvent(this));
            } catch (Exception e) {
                System.out.println("can't update route,id" + r.getId());
            }
        });
    }
}
