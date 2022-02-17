package com.spring.cloud.alibaba.demo.mallportal;

import com.spring.cloud.alibaba.demo.mallportal.sentinel.SentinelAnnotationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MallPortalApplication {
    public static void main(String[] args) {
        // 加载配置限流规则
        SentinelAnnotationService.flowRule();
        // 启动程序
        SpringApplication.run(MallPortalApplication.class, args);
    }
}
