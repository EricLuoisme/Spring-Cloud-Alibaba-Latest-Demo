package com.spring.cloud.alibaba.demo.userserviceprovider;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

public class FlowRuleInitFunc implements InitFunc {

    private static final String CLUSTER_SERVER_HOST = "localhost";
    private static final int CLUSTER_SERVER_PORT = 7777;
    private static final int REQUEST_TIME_OUT = 200000;


    private final String REMOTE_ADDRESS = "127.0.0.1:8848";
    private final String GROUP_ID = "SENTINEL_GROUP";
    private final String FLOW_POSTFIX = "-flow-rules";

    private final String APP_NAME = "user-service-provider";


    @Override
    public void init() throws Exception {

        // 声明作为Token-client
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);

        loadClusterClientConfig();
        registerClusterFlowRule();
    }

    private void loadClusterClientConfig() {
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();

        assignConfig.setServerHost(CLUSTER_SERVER_HOST);
        assignConfig.setServerPort(CLUSTER_SERVER_PORT);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);

        ClusterClientConfig clientConfig = new ClusterClientConfig();
        clientConfig.setRequestTimeout(REQUEST_TIME_OUT);
        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }

    private void registerClusterFlowRule() {
        ReadableDataSource<String, List<FlowRule>> dataSource = new NacosDataSource<List<FlowRule>>(
                REMOTE_ADDRESS, GROUP_ID, APP_NAME,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(dataSource.getProperty());
    }

}
