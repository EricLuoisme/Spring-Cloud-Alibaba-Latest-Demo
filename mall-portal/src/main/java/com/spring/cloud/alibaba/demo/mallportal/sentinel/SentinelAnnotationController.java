package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class SentinelAnnotationController {

    @Autowired
    private SentinelAnnotationService service;

    @GetMapping("/test")
    public String testSentinel() {
        return service.sentinelTest();
    }

}
