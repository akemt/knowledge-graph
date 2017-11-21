package com.beyond.algm.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：zhangchuanzhi
 * @Description:各种校验
 * @date ：9:44 2017/10/10
 */
public class NumCheckUtil {

    /**
     * @author ：zhangchuanzhi
     * @Description:电话校验
     * @date ：9:44 2017/10/10
     */
    public static boolean checkTel(String tel) {
        if( Assert.isEmpty(tel)){
            return true;
        }
        Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
        Matcher matcher = regex.matcher(tel);
        boolean flag=  matcher.matches();
        return flag;
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:邮箱校验
     * @date ：9:44 2017/10/10
     */
    public static boolean checkEmail(String email) {
        if( Assert.isEmpty(email)){
            return true;
        }
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean flag=  matcher.matches();
        return flag;
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:网址校验
     * @date ：9:44 2017/10/10
     */
    public static boolean checkWebsite(String path) {
        if( Assert.isEmpty(path)){
            return true;
        }
        String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
                + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
                + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
                + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
                + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
                + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
                + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
                + "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(path);
        return matcher.matches();
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:中文汉字
     * @date ：9:44 2017/10/10
     */
    public static boolean isChineseStr(String str){
        if( Assert.isEmpty(str)){
            return true;
        }
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        char c[] = str.toCharArray();
        for(int i=0;i<c.length;i++){
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if(!matcher.matches()){
                return false;
            }
        }
        return true;
    }
}
