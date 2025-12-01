package com.sprotygroup.betsettlement.config.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQProducerConfig {

    @Value("${spring.rocketmq.named-server}")
    private String namedServer;

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        DefaultMQProducer producer = new DefaultMQProducer("bet-settlements-producer");
        producer.setNamesrvAddr(namedServer);
        producer.setSendMsgTimeout(30000);

        RocketMQTemplate template = new RocketMQTemplate();

        template.setProducer(producer);
        template.setMessageConverter(new RocketMQMessageConverter().getMessageConverter());

        return template;
    }
}
