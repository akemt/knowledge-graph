package com.beyond.algm.algmcallboot.config;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Slf4j
public class RocketMQProducerConfig {
    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize ;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout ;
    @Bean
    public DefaultMQProducer getRocketMQProducer() throws AlgException {
        if (Assert.isEmpty(this.groupName)) {
            throw new AlgException("groupName is blank");
        }
        if (Assert.isEmpty(this.namesrvAddr)) {
            throw new AlgException("nameServerAddr is blank");
        }
        if (Assert.isEmpty(this.instanceName)){
            throw new AlgException("instanceName is blank");
        }
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        producer.setInstanceName(this.instanceName);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        try {
            producer.start();
            log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            log.error(String.format("producer is error {}"
                    , e.getMessage(),e));
            throw new AlgException(e);
        }
        return producer;
    }
}
