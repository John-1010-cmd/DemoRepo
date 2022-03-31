package com.example.demo.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyDubboConfig {
    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("boot-user-service-provider3");
//        applicationConfig.setRegistry();
        return applicationConfig;
    }
    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        return registryConfig;
    }
    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20883);
        return protocolConfig;
    }

    @Bean
    public ServiceConfig<UserService> serviceConfig(UserService userService){
        ServiceConfig<UserService> serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setVersion("1.0.0");

        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getUserAddressList");
        methodConfig.setTimeout(1000);
        List<MethodConfig> methodConfigs = new ArrayList<>();
        methodConfigs.add(methodConfig);
        serviceConfig.setMethods(methodConfigs);
        return serviceConfig;
    }
}
