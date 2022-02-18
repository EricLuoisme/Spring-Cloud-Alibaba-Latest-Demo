package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用SPI的方式, 切入Sentinel预先设置逻辑
 */
public class FlowRuleInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        // 将自定义的限流规则加载
        loadFlowRule();
    }

    /**
     * 自定义限流规则
     */
    private static void loadFlowRule() {
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
