package com.spring.cloud.alibaba.demo.mallportal.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Deprecated
public class SentinelExample {

    private final static String RESOURCE = "SentinelExample";

    static {
        flowRule();
    }

    /**
     * 设置限流规则
     */
    private static void flowRule() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule flowRule = new FlowRule();
        // 对哪个资源设置限流
        flowRule.setResource(RESOURCE);
        // 限流的维度, QPS/线程总数
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(5);
        rules.add(flowRule);

        // 将限流规则加入
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 模拟限流触发
     */
    public static void main(String[] args) {
        Random random = new Random();
        while (true) {
            try (Entry entry = SphU.entry(RESOURCE)) {
                System.out.println("Request Success");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(350));
            } catch (BlockException e) {
                System.out.println("被限流");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
