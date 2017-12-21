import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class ProducerTest {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        //Instantiate with a producer group name
        DefaultMQProducer producer = new DefaultMQProducer("group_p_no2");
        /*
        *是设定向哪个nameserver请求
        * 发送失败重试的次数
        * */
        producer.setNamesrvAddr("210.30.96.55:9876;210.30.96.59:9876");
        producer.setRetryTimesWhenSendFailed(10);
        //Lanch the instance
        producer.start();
        int x = 0;

        for (int i = 0; i < 10; i++){
            try {

                //test
                JSONObject obj = new JSONObject();
                obj.put("algSn", "alg_sn" + i);
                obj.put("umcSn", "umc_sn" + (i));
                obj.put("modSn", "mod_sn" + (i));
                obj.put("verSn", "ver_sn" + (i));
                obj.put("callUsrSn", "call_usr_sn" + (i));
                obj.put("ownerUsrSn", "owner_usr_sn" + (i));
                obj.put("duration", 1000);
                obj.put("callPayAmount", 66.66);
                obj.put("isCharging", 1);
                obj.put("startTime", 666);
                obj.put("endTime", 666);

                //Create a message instance, specifying topic, tag and message body.
                //这里的tags如果是我们的计费系统，那么最好为订单号也好用户id也好，因为这里有可能一个订单号下有多条数据或者一个用户id下有多条数据
                Message msg = new Message("Topic_no2",
                        "Tag_no1",
                        "OrderID188",
                        (obj.toString()).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //Call send message to deliver message to one of brokers.
                SendResult result = producer.send(msg);
//                System.out.printf("%s%n", result+"send out");
                x++;
                System.out.println(x);


                //QueryResult is to see the message list when test the project, this is unnecessary
//                QueryResult qm =
//                        producer.queryMessage("Topic_no1",
//                                "OrderID188",
//                                10,
//                                0,
//                                System.currentTimeMillis());
//                for (MessageExt m : qm.getMessageList()){
//                    System.out.printf("%s%n",m);
//                }
//                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //Shut down once the producer instance is not loger in use.
        producer.shutdown();
    }
}
