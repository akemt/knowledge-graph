package com.beyond.algm.algmcallboot.infra.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.beyond.algm.algmcallboot.infra.RocketMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RocketMQServiceImpl implements RocketMQService {
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    public void modCallProducer(JSONObject algUserCall) throws Exception{
        log.info("rocketMQ：开始准备数据");
        Message msg = new Message("Topic_no1",// topic
                "TEST",// tag
                "KKK",//key用于标识业务的唯一性
                algUserCall.toJSONString().getBytes()//调用消费信息
        );
        SendResult result = null;
        try {
            result = defaultMQProducer.send(msg);
            log.info("rocketMQ：发送完毕");
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
