package com.yuan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/6/26 22:13
 * @Description null
 */
@Component
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(){
        //定义要发送的消息
        String msg="hello world";
        Message message=new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.fanout","",message);
        log.info("消息发送完毕，发送时间为：{}",new Date());
    }
}
