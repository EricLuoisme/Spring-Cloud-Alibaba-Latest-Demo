package com.spring.cloud.alibaba.demo.userserviceprovider.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void sendMsg2RocketMq(String msg) {
        // 构建RocketMq需要的msg格式
        Message<String> message = MessageBuilder.withPayload(msg).build();
        // 事务性发送, 需要类似绑定一个监听的listener, 接收rocket返回的内容
        rocketMQTemplate.sendMessageInTransaction("user_register", message, null);
    }
}
