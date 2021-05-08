package kafkademo.consumer;

import kafkademo.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by yzd on 2021/4/30
 */
@Component
public class Demo01Consumer {

    private final static Logger logger = LoggerFactory.getLogger(Demo01Consumer.class);

    @KafkaListener(topics = Demo01Message.TOPIC, groupId = "demo01-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(Demo01Message message) {
        logger.info("[onMessage][线程序号:{} 消息内容:{}]", Thread.currentThread().getId(), message);
    }
}
