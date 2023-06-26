package com.yuan.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/6/26 22:12
 * @Description null
 */
@Configuration
public class FanoutRabbitMqConfig {
    //rabbitmq 三部曲
    // 1定义交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }
    // 2 定义队列
    @Bean
    public Queue queueA(){
        return new Queue("queue.fanout.a");
    }
    @Bean
    public Queue queueB(){
        return new Queue("queue.fanout.b");
    }
    // 3 绑定交换机和队列
    @Bean
    public Binding bingingA(FanoutExchange fanoutExchange, Queue queueA){
        //将队列A绑定到扇形交换机
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bingingB(FanoutExchange fanoutExchange, Queue queueB){
        //将队列B绑定到扇形交换机
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
}
