package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SentinelExceptionHandler {

    // 限流触发调用方法, 需要和被限流的Resource返回类型相同
    public static String handleSentinelTest(BlockException e) {
        log.error("Blocked DDOS");
        return "You have been Blocked";
    }
}
