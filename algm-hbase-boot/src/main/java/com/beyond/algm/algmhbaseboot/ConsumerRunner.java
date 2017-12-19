package com.beyond.algm.algmhbaseboot;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmhbaseboot.infra.HBaseService;
import com.beyond.algm.algmhbaseboot.infra.PhoenixRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringBootConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * RocketMQ Consumer
 * create by JR.Elephant on 2017/11/30
 * */

@SpringBootConfiguration
@Slf4j
public class ConsumerRunner implements ApplicationRunner {

    @Autowired
    private HBaseService hBaseService;

    @Value("${rocketMQ.group1}")
    private String rocketMQGroup1;
    @Value("${rocketMQ.nameSrvAddr}")
    private String nameSrvAddr;
    @Value("${rocketMQ.topic1}")
    private String rocketMQTopic1;
    @Value("${rocketMQ.topic2}")
    private String rocketMQTopic2;
    @Value("${hbase.tableName1}")
    private String tableName1;


    @Override
    public void run(ApplicationArguments var1) throws Exception{

        // phoenix对象
        PhoenixRunner phoenixRunner = new PhoenixRunner();

        // create PushConsumer instance
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(rocketMQGroup1);
        // Specify which nameServer to connect
        pushConsumer.setNamesrvAddr(nameSrvAddr);
        // Specify the topic of the subscription
        try {
            pushConsumer.subscribe(rocketMQTopic2, "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // Where is it read when it starts
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // Consume Timestamp
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss" + "00");
        pushConsumer.setConsumeTimestamp(dateFormat.format(currentDate));
        // Number of threads on the consumer
        pushConsumer.setConsumeThreadMin(1);
        pushConsumer.setConsumeThreadMax(1);

        // Register Message Listener
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //
                for (int i = 0; i<list.size(); i++) {
                    // Gets the elements in the list
                    Message msg = list.get(i);
                    // Change the data type
                    JSONObject jsonValue = JSONObject.parseObject(new String(msg.getBody()));
                    //---------------------test2----------------------
                    log.info(jsonValue.toString());
                    //-----------------------------------------------
                    //------------------有问题，少条，少字段（同时大批量）；无问题（非同时）
                    /* 直接写入hbase
                    try {
                        hBaseService.insertTableByJson(tableName1, jsonValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    */
                    // 使用phoenix写入hbase
                    try {
                        phoenixRunner.insertByJson(jsonValue);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            pushConsumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}
