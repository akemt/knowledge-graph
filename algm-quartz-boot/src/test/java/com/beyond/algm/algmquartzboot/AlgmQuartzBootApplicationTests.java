package com.beyond.algm.algmquartzboot;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONArray;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmquartzboot.infra.CallQuartzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgmQuartzBootApplicationTests {
	@Autowired
	private CallQuartzService callQuartzService;

	@Test
	public void contextLoads() throws Exception{
		JSONArray jsonArray = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("UMC_SN", "11c0190ca13b34efb17310" );
        obj.put("VER_SN", "0.7.1" );
        obj.put("CALL_USR_SN", "64ab1a38489909862c0e0cfd90531" );
        obj.put("OWNER_USR_SN", "64ab1a381909862c0e0cfd9asa" );
        obj.put("DURATION", 157777);
        obj.put("BILLED_CALL_CNT", 4545 );
        obj.put("CALL_PAY_AMOUNT", 177.599);
        obj.put("MOD_SN", "a989dd39c7574087ba539482a");
        obj.put("ALL_CALL_AMOUNT", 114);
        jsonArray.add(obj);
        JSONObject obj1 = new JSONObject();
        obj1.put("UMC_SN", "11c0190ca13fb17310" );
        obj1.put("VER_SN", "0.0.1" );
        obj1.put("CALL_USR_SN", "64ab1a41909862c0e0cfd90531" );
        obj1.put("OWNER_USR_SN", "64ab1a38489941909862c9asa" );
        obj1.put("DURATION", 154444);
        obj1.put("BILLED_CALL_CNT", 4545 );
        obj1.put("CALL_PAY_AMOUNT", 1444.599);
        obj1.put("MOD_SN", "a989dd39c7574087ba535");
        obj1.put("ALL_CALL_AMOUNT", 154);
        jsonArray.add(obj1);
        System.out.println(jsonArray.toString());
		callQuartzService.callQuartzs(jsonArray);
	}
}
