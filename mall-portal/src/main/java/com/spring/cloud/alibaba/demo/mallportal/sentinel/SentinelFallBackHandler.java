package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SentinelFallBackHandler {
    
    // 降级触发调用方法, 需要和被限流的Resource返回类型相同
    public static String fallbackTest() {
        log.info("Degrade Server is called by user");
        return "Please try again later";
    }
}
