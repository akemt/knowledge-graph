package com.${packetName}.${username}.controller;

import .${packetName}.${projectName}.${projectName};
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@EnableAutoConfiguration
@RequestMapping("/${username}/${projectName}")
public class MainController {

    @ResponseBody
    @RequestMapping(value = "/0.1.0", method = {RequestMethod.POST})
    public String index(@RequestBody String jsonStr) throws Exception {
        Object json = new JSONTokener(jsonStr).nextValue();
        if (json instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) json;
        } else if (json instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) json;
        }
        Object object = Class.forName("${projectName}.${projectName}.${projectName}").newInstance();
        Method m = object.getClass().getMethod("apply", new Class[]{String.class});
        String result = (String) m.invoke(object, jsonStr);
        return result;
    }
}

