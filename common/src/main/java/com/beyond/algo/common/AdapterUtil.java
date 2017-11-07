package com.beyond.algo.common;

import com.beyond.algo.exception.AlgException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：zhangchuanzhi
 * @Description:共同方法
 * @date ：16:59 2017/10/30
 */
@Slf4j
public class AdapterUtil {
    public static Object moduleAdapter(String lanName)  throws AlgException {
        try {
            return Class.forName("com.beyond.algo.algoalgorithmsboot.adapter."+ lanName +"ModuleAdapter").newInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AlgException("BEYOND.ALG.MODULE.COPY.0000006",new String[]{},e);
        }
    }
}
