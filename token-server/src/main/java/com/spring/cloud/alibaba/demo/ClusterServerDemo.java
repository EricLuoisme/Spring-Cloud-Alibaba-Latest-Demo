package com.spring.cloud.alibaba.demo;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

public class ClusterServerDemo {
    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer = new SentinelDefaultTokenServer();

        // Server的网络通信配置
        ClusterServerConfigManager.loadGlobalTransportConfig(
                new ServerTransportConfig()
                        .setIdleSeconds(600)
                        .setPort(11111));

        // 区分集群 (放入需要处理的服务名称)
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("user-service-provider"));

        // 启动服务
        tokenServer.start();
    }
}
