package com.beyond.algo.algoconsoleboot.exception;

import com.beyond.algo.base.Error;
import com.beyond.algo.common.JsonUtil;
import com.beyond.algo.common.Result;
import com.beyond.algo.constant.Constant;
import com.beyond.algo.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/27 10:53
 */
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    public ReloadableResourceBundleMessageSource messageSource;

    @ExceptionHandler(value = AlgException.class)
    @ResponseBody
    public Result algErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.warn("algErrorHandler Handler,Host: {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        Result failureData = Result.failure(e.getMessage());
        if (e instanceof AlgException) {
            String message = e.getMessage();
            String[] split = message.split(Constant.SCRIPT_DELIMIT);
            if(split.length==1) {
                String errorCode = split[0];//错误码
                failureData = Result.failure(new Error(errorCode, getErrorInfo(errorCode, null)));
            }else if (split.length == 2) {
                String errorCode = split[0];//错误码
                Object[] params = JsonUtil.parseJson(split[1], Object[].class);//占位参数
                //包装Error对象：错误码和错误信息
                failureData = Result.failure(new Error(errorCode, getErrorInfo(errorCode, params)));
            }
        }
        return failureData;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return Result.unknowResponse().addDetail(e.toString());
    }

    /**
     * 获取错误码对应的错误信息
     *
     * @param errorCode 错误码
     * @param params    错误信息中需要填充占位符的数据
     * @return
     */
    private String getErrorInfo(String errorCode, Object[] params) {
        try {
            return messageSource.getMessage(errorCode, params, Locale.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
            return errorCode;
        }
    }
}
