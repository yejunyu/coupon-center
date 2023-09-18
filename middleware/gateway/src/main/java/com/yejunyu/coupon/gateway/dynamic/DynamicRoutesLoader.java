package com.yejunyu.coupon.gateway.dynamic;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/18
 * @Description: TODO
 **/
@Slf4j
@Configuration
public class DynamicRoutesLoader implements InitializingBean {
    @Autowired
    private NacosConfigManager configManager;
    @Autowired
    private NacosConfigProperties configProperties;
    @Autowired
    private DynamicRoutesListener routesListener;

    private static final String ROUTES_CONFIG = "routes-config.json";


    @Override
    public void afterPropertiesSet() throws Exception {
        // 首次加载配置
        String routes = configManager.getConfigService().getConfig(
                ROUTES_CONFIG, configProperties.getGroup(), 10000
        );
        routesListener.receiveConfigInfo(routes);
        // 注册监听器
        configManager.getConfigService().addListener(
                ROUTES_CONFIG, configProperties.getGroup(), routesListener
        );
    }
}
