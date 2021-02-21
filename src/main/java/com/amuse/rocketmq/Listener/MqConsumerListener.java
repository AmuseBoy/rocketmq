package com.amuse.rocketmq.Listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MqConsumerListener
 * @Description TODO
 * @Author 刘培振
 * @Date 2018/8/28 16:02
 * @Version 1.0
 */
public class MqConsumerListener implements MessageListener {

    private final static Logger logger = LoggerFactory.getLogger(MqConsumerListener.class);

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        logger.info("message:"+new String(message.getBody()));
        if (message.getTag() != null && message.getTag().equals("tagA")){
            logger.info("我再处理......");
            return Action.CommitMessage;
        }
        return Action.ReconsumeLater;
    }
}
