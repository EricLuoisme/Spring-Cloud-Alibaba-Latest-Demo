package com.spring.cloud.alibaba.demo.mallportal.controller;

import com.spring.cloud.alibaba.demo.userserviceapi.service.IHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DubboController {

    @DubboReference
    IHelloService helloService;

    @GetMapping("/say")
    public String say() {
        return helloService.say("Roylic");
    }
}
