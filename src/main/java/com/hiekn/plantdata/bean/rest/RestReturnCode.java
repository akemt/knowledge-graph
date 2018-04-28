package com.hiekn.plantdata.bean.rest;

import org.springframework.dao.DuplicateKeyException;

import javax.ws.rs.core.Response;


public class RestReturnCode {
    public static final int SUCCESS = 200;
    public static final int PARAM_ERROR = 300;
    public static final int SERVICE_ERROR = 400;
    public static final int REMOTE_INVOKE_ERROR = 401;
    public static final int REMOTE_PARSE_ERROR = 402;
    public static final int DUP_KEY_ERROR = 403;
    public static final int MCODE_ERROR = 501;
    public static final int ACCESS_TOKEN_ERROR = 502;
    public static final int USER_NO_TRAIL_ERROR = 601;
    public static final int USER_TRAIL_TIME_OUT_ERROR = 602;
    public static final int USER_NO_REGIST_ERROR = 603;
    public static final int USER_NO_PERMISSION = 604;
    public static final int USER_LOGIN_ERROR = 605;
    public static final int USER_TODAY_TRAIL_LIMIT = 701;
    public static final int CONNECT_FAILDS = 801;
    public static final int HTTP_ERROR = 901;
    public static final int FILE_TYPE_ERROR = 902;
    public static final int PROJECT_CONFIG_ERROR = 903;

    public static String getMsg(int code) {
        switch (code) {
            case 200:
                return "成功";
            case 300:
                return "参数解析错误";
            case 400:
                return "服务端错误";
            case 401:
                return "服务端错误";
            case 402:
                return "服务端错误";
            case 403:
                return "重复添加";
            case 501:
                return "验证错误";
            case 502:
                return "验证错误";
            case 601:
                return "用户没有被邀请试用";
            case 602:
                return "用户试用已到期";
            case 603:
                return "用户未注册";
            case 604:
                return "用户无权限";
            case 605:
                return "用户名或密码错误";
            case 701:
                return "用户今日试用次数已达上限";
            case 801:
                return "无法连接";
            case 901:
                return "http相关错误";
            case 902:
                return "只支持上传docx、pptx、pdf格式文件";
            case 903:
                return "项目配置项存在错误";
        }

        return "";
    }

    public static Response paramErrorHandler(Exception e, Long tt) {
        RestResp resp = new RestResp(Integer.valueOf(300), tt);
        return Response.ok().entity(resp).build();
    }

    public static Response serviceErrorHandler(Exception e, Long tt) {
        RestResp resp = null;

        if (e instanceof DuplicateKeyException) {
            resp = new RestResp(Integer.valueOf(403), tt);
            return Response.ok().entity(resp).build();
        }

        resp = new RestResp(Integer.valueOf(400), tt);
        return Response.ok().entity(resp).build();
    }
}
