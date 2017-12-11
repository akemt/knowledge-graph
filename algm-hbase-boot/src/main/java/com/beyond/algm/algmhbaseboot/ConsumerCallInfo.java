package com.beyond.algm.algmhbaseboot;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * RocketMQ Consumer
 * create by JR.Elephant on 2017/11/30
 * */
@Slf4j
public class ConsumerCallInfo {
    private static JSONObject jsonValue;

    public void consume () throws MQClientException {
        //create HBaseUtils instance
        HBaseUtils hbaseUtils = new HBaseUtils();

        // create PushConsumer instance

        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(SiteAndParameter.rocketMQGroup1);
        // Specify which nameServer to connect
        pushConsumer.setNamesrvAddr(SiteAndParameter.rocketMQNameSrvAddr);
        // Specify the topic of the subscription
        pushConsumer.subscribe(SiteAndParameter.rocketMQTopic1, "*");
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
                    jsonValue = Utils.stringToJsonObject(new String(msg.getBody()));

                    //---------------------test2----------------------
                    log.info(jsonValue.toString());
                    //-----------------------------------------------
                    //------------------有问题，少条，少字段（同时大批量）；无问题（非同时）

                    try {
                        hbaseUtils.insertTableByJson(SiteAndParameter.tableName, jsonValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        pushConsumer.start();
    }
}
