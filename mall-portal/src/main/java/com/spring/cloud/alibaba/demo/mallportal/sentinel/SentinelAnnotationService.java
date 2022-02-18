package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SentinelAnnotationService {

    // 注解限流, 传入的value是指为该方法限流,
    // blockHandler指向限流异常触发方法 + blockHandlerClass指向方法的类, fallback同样
    // 需要注意, blockHandler和fallback方法, 返回类型与@SentinelResource注释的方法, 要一致
    @SentinelResource(value = "sentinelTest",
            blockHandler = "handleSentinelTest", blockHandlerClass = SentinelExceptionHandler.class,
            fallback = "fallbackTest", fallbackClass = SentinelFallBackHandler.class)
    public String sentinelTest() {
        return "Request Success";
    }


}
