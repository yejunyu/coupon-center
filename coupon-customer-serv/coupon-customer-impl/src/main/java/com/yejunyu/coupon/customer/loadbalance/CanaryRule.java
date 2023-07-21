package com.yejunyu.coupon.customer.loadbalance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/14
 * @Description: 继承 ReactorServiceInstanceLoadBalancer 实现自己的负载均衡策略
 **/
@Slf4j
public class CanaryRule implements ReactorServiceInstanceLoadBalancer {

    private String serviceId;

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSuppliers;

    // 定义一个轮训策略的种子
    final AtomicInteger position;

    public CanaryRule(String serviceId, ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSuppliers) {
        this.serviceId = serviceId;
        this.serviceInstanceListSuppliers = serviceInstanceListSuppliers;
        position = new AtomicInteger(new Random().nextInt(1000));
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSuppliers.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next()
                .map(serviceInstances -> processInstanceResponse(supplier, serviceInstances, request));
    }

    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier, List<ServiceInstance> serviceInstances, Request request) {

        return null;
    }
}
