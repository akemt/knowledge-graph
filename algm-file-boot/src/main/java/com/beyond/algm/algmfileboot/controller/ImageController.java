package com.beyond.algm.algmfileboot.controller;

import com.beyond.algm.algmfileboot.base.BaseController;
import com.beyond.algm.algmfileboot.infra.CephService;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/11 15:54
 */
@Controller
public class ImageController extends BaseController {
    @Value("${ceph.host}")
    private String host;
    @Autowired
    private CephService cephService;

    /**
     * @author ：zhangchuanzhi
     * @Description:个人用户图片上传
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/11/24
     */
    @ResponseBody
    @RequestMapping(value = "/head/upload",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result uploadImage(MultipartFile file) throws AlgException {
        AlgUser algUser = getUserInfo();
        cephService.userHeadImgUpload(file,algUser.getUsrCode());
        return Result.successResponse();
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:富文本编辑器
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/11/24
     */
    @ResponseBody
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result uploadEditorImage(MultipartFile file) throws AlgException {
        AlgUser algUser = getUserInfo();
        String path = cephService.uploadEditorImage(file,algUser.getUsrCode());
        return Result.ok(path);
    }

    /**
     *
     * @param request
     * @param response
     * @throws AlgException
     */
    @RequestMapping(value = "/image/**", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws AlgException {
        String path = request.getServletPath();
        path = path.replace("/image","");
        try {
            response.sendRedirect(host+path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
