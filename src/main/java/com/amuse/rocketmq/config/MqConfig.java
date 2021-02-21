package com.amuse.rocketmq.config;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.amuse.rocketmq.Listener.MqConsumerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName MqConfig
 * @Description TODO
 * @Author 刘培振
 * @Date 2018/8/28 10:03
 * @Version 1.0
 */
@Configuration
public class MqConfig {

    @Value("${mq.topic}")
    private String topic;

    @Value("${mq.address}")
    private String address;

    @Value("${mq.producerId}")
    private String producerId;

    @Value("${mq.consumerId}")
    private String consumerId;

    @Value("${mq.accessKey}")
    private String accessKey;

    @Value("${mq.secretKey}")
    private String secretKey;

    @Value("${mq.consumeThreadNums}")
    private String consumeThreadNums;

    @Value("${mq.maxReconsumeTimes}")
    private String maxReconsumeTimes;


    @Bean(initMethod = "start",destroyMethod = "shutdown")
    public Producer producer() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, producerId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        properties.put(PropertyKeyConst.ONSAddr, address);
        return ONSFactory.createProducer(properties);
    }

    @Bean(initMethod = "start",destroyMethod = "shutdown")
    public Consumer consumer() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, consumerId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        properties.put(PropertyKeyConst.ONSAddr, address);
        properties.put(PropertyKeyConst.ConsumeThreadNums, consumeThreadNums);
        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(topic, "*", new MqConsumerListener());
        //...
        return consumer;
    }

}
