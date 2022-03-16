package com.spring.cloud.alibaba.demo.userserviceprovider.event;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

public class UserServiceMsgListener implements RocketMQLocalTransactionListener {

    // 执行本地事务, 根据执行结果返回给RocketMq本地事务执行状态
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        // 并不清楚状态, RocketMQ会调用checkLocalTransaction()去查询具体执行完成的状态
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    // 本地事务回调
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return null;
    }
}
