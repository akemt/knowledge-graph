package com.beyond.algm.algmarketpublish;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class AlgmarketPublishApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgmarketPublishApplication.class, args);
	}

	@RequestMapping(value = "/", method = {RequestMethod.POST})
	public String index(@RequestBody String jsonStr) throws Exception {
		Object json = new JSONTokener(jsonStr).nextValue();
		if(json instanceof JSONObject){
			JSONObject jsonObject = (JSONObject)json;
		}else if (json instanceof JSONArray){

			JSONArray jsonArray = (JSONArray)json;
		}
		Object object = Class.forName("algmarket.${projectName}.${projectName}").newInstance();
		Method m = object.getClass().getMethod("apply",new Class[] { String.class});
		String result = (String) m.invoke(object,jsonStr);
		return result;
	}
}
