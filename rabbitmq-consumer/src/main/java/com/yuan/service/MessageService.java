package com.yuan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    //接收两个队列的消息
    @RabbitListener(queues = {"queue.fanout.a","queue.fanout.b"})
    public void receiveMsg(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("接收到的消息为：{}",msg);
    }
}
