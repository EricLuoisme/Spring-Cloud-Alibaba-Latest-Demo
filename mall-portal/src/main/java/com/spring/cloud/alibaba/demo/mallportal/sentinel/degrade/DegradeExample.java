package com.spring.cloud.alibaba.demo.mallportal.sentinel.degrade;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;

import java.util.ArrayList;
import java.util.List;

public class DegradeExample {

    private static final String RESOURCE = "say";

    public static void main(String[] args) {
        initDegradeRule();
    }

    private static void initDegradeRule() {
        List<DegradeRule> degradeRuleList = new ArrayList<>();
        DegradeRule rule = new DegradeRule(RESOURCE)
                .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
                .setCount(20)
                .setTimeWindow(5)
                .setSlowRatioThreshold(0.2)
                .setMinRequestAmount(5)
                .setStatIntervalMs(1000); //ms
        degradeRuleList.add(rule);
        DegradeRuleManager.loadRules(degradeRuleList);

    }
}
