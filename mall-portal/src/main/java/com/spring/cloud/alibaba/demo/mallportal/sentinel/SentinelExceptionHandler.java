package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SentinelExceptionHandler {
    public static void handleSentinelTest(BlockException e) {
        log.error("Block Exception Occur: " + e);
    }
}
