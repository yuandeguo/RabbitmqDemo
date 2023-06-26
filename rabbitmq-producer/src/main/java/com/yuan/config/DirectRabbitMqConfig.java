package com.yuan.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/6/26 23:22
 * @Description null
 */
@Configuration
@ConfigurationProperties(prefix = "my")  //属性配置
public class DirectRabbitMqConfig {
    @Value("${my.directExchangeName}")
    //交换机的名字
    private String directExchangeName;
    @Value("${my.directQueueAName}")
    // 队列A的名字
    private String directQueueAName;
    //队列B的名字
    @Value("${my.directQueueBName}")
    private String directQueueBName;
    //三部曲
    //1 定义交换机
    @Bean
    public DirectExchange directExchange(){
        //使用建造者模式创建
        return ExchangeBuilder.directExchange(directExchangeName).build();
    }
    //2 定义队列
    @Bean
    public Queue queueA(){
        //使用建造者模式创建
        return QueueBuilder.durable(directQueueAName).build();
    }
    @Bean
    public Queue queueB(){
        return QueueBuilder.durable(directQueueBName).build();
    }
    //3 交换机和队列进行绑定
    @Bean
    public Binding bindingA(DirectExchange directExchange, Queue queueA){
        //使用建造者模式创建
        return BindingBuilder.bind(queueA).to(directExchange).with("error");
    }

    @Bean
    public Binding bindingB1(DirectExchange directExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("info");
    }
    @Bean
    public Binding bindingB2(DirectExchange directExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("error");
    }

    @Bean
    public Binding bindingB3(DirectExchange directExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("warning");
    }

}
