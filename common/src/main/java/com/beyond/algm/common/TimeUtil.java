package com.beyond.algm.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：zhangchuanzhi
 * @Description:时间工具处理
 * @date ：14:18 2017/12/8
 */
public class TimeUtil {
    public static String getNowDateString(Date nowDate) {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(nowDate);
        return nowTime;
    }
}
