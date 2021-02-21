package com.amuse.rocketmq.controller;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProducerController
 * @Description TODO
 * @Author 刘培振
 * @Date 2018/8/28 16:06
 * @Version 1.0
 */
@RestController
public class ProducerController {

    private final static Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private Producer producer;

    @Value("${mq.topic}")
    private String topic;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        for(int i=0;i<100;i++){
            Message message = new Message(topic, "tagA", ("哇哈哈不是娃娃哈"+i).getBytes());
            SendResult sendResult = producer.send(message);
            logger.info("sendResult:"+sendResult);
        }
    }

}
