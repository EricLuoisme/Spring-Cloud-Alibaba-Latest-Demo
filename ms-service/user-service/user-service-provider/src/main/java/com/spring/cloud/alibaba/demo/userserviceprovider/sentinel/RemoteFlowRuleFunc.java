package com.spring.cloud.alibaba.demo.userserviceprovider.sentinel;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

public class RemoteFlowRuleFunc implements InitFunc {

    // 远程配置拉取
    private final String remoteAddress = "127.0.0.1:8848";
    private final String groupId = "SENTINEL_GROUP";
    private final String dataId = "HelloService";


    @Override
    public void init() throws Exception {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource
                = new NacosDataSource<>(remoteAddress, groupId, dataId,
                s -> JSON.parseObject(s, new TypeReference<List<FlowRule>>() {
                }));
    }
}
