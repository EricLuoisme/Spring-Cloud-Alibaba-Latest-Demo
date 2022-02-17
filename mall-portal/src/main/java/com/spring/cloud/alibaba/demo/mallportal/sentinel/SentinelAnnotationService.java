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
    // blockHandler指向限流异常触发方法 + blockHandlerClass指向方法的类
    @SentinelResource(value = "sentinelTest", blockHandler = "handleSentinelTest", blockHandlerClass = SentinelExceptionHandler.class)
    public String sentinelTest() {
        return "Request Success";
    }

    // 设置限流规则 (放入启动类调用)
    public static void flowRule() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule flowRule = new FlowRule();
        // 对哪个资源设置限流
        flowRule.setResource("sentinelTest");
        // 限流的维度, QPS/线程总数
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(2);
        rules.add(flowRule);
        // 将限流规则加入
        FlowRuleManager.loadRules(rules);
    }


}
