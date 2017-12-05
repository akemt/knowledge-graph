package com.beyond.algm.algmcallboot.infra;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.exception.AlgException;

public interface RocketMQService {
    void modCallProducer(JSONObject algUserCall) throws Exception;
}
