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
        obj.put("UMC_SN", "1186b6b1516a420dbaca9d1ccb18d1482" );
        obj.put("VER_SN", "0.0.1" );
        obj.put("CALL_USR_SN", "a989dd39c757408a99c7ba535d191482a" );
        obj.put("DURATION",78);
        obj.put("CALL_PAY_AMOUNT", 1.55);
        obj.put("MOD_SN", "a989dd39c757408a99c7ba535d82a");
        obj.put("ALL_CALL_AMOUNT", 26);
        jsonArray.add(obj);
        JSONObject obj1 = new JSONObject();
        obj1.put("UMC_SN", "11c0190ca13b4b3796e40d5834efb17310" );
        obj1.put("VER_SN", "0.0.1" );
        obj1.put("CALL_USR_SN", "64ab1a38489941909862c0e0cfd90531" );
        obj1.put("DURATION", 15);
        obj1.put("CALL_PAY_AMOUNT", 1.599);
        obj1.put("MOD_SN", "a989dd39c7574087ba535d19482a");
        obj1.put("ALL_CALL_AMOUNT", 15);
        jsonArray.add(obj1);
        System.out.println(jsonArray.toString());
		callQuartzService.callQuartzs(jsonArray);
	}
}
