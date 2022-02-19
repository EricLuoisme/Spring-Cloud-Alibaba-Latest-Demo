package com.spring.cloud.alibaba.demo.userserviceprovider.service;

import com.spring.cloud.alibaba.demo.userserviceapi.service.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(loadbalance = "random")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String say(String msg) {
        return "Hello, " + msg + " I'm Dubbo Service";
    }
}
