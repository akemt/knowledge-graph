package com.beyond.algm.algmfileboot.controller;

import com.beyond.algm.algmfileboot.base.BaseController;
import com.beyond.algm.algmfileboot.infra.CephService;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/11 15:54
 */
public class ImageController extends BaseController {

    @Autowired
    private CephService cephService;

    /**
     * @author ：zhangchuanzhi
     * @Description:个人用户图片上传
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/11/24
     */
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result uploadImage(MultipartFile file) throws AlgException {
        AlgUser algUser = getUserInfo();
        cephService.upload(file,algUser.getUsrCode());
        return Result.successResponse();
    }
}
