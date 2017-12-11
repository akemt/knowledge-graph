package com.beyond.algm.algmhbaseboot;

import com.alibaba.fastjson.JSONObject;

public class Utils {
    // String to Json
    public static JSONObject stringToJsonObject(String context){
        JSONObject jsonObject = JSONObject.parseObject(context);

        //----------------------test1------------------------
        System.out.println(jsonObject.toString());
        //--------------------------------------------------
        //-----------------------200条，测试4次，没有问题

        return jsonObject;
    }
}
