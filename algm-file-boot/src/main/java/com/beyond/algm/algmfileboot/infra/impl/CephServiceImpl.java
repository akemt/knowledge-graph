package com.beyond.algm.algmfileboot.infra.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.beyond.algm.algmfileboot.infra.CephService;
import com.beyond.algm.algmfileboot.util.FileDownLoad;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author ：
 * @Description:
 * @date ：13:21 2017/11/27
 */
@Service
@Slf4j
public class CephServiceImpl implements CephService {

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    private AlgUserMapper algUserMapper;

    @Value("${ceph.path}")
    private String path;
    @Value("${ceph.host}")
    private String host;

    @Override
    public void userHeadImgUpload(MultipartFile file, String usrCode) throws AlgException{
        String cephKey = UUIDUtil.createUUID();;
        log.info("文件名:{},用户code:{},path:{}",file.getOriginalFilename(),usrCode,path);
        File targetFile=targetFile(file);
        // 注意之前上传图片还在需要优化
        Bucket bucket=amazonS3.createBucket("algmarket");
        amazonS3.putObject(bucket.getName(), cephKey,targetFile);
        amazonS3.setObjectAcl(bucket.getName(), cephKey, CannedAccessControlList.PublicRead);
        String pathUrl=  amazonS3.getUrl(bucket.getName(),cephKey).getPath();
        log.info("ceph的url的存储:{}",pathUrl);
        AlgUser user=new AlgUser();
        user.setUsrCode(usrCode);
        user.setUsrUrl(pathUrl);
        algUserMapper.update(user);
        targetFile.delete();
    }

    public  void  userHeadImgDownload(String path,HttpServletResponse response)throws AlgException{
        String url=host+File.separator+path;
        log.info("生成url:{}",url);
        if(Assert.isNotEmpty(url)){
            // 数据集+文件名
            File downloadFile=new File(path);
            amazonS3.getObject(
                    new GetObjectRequest("algmarket", path),
                    downloadFile
            );
            //设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + downloadFile.getName());
            FileDownLoad.fileDownLoad(response,downloadFile,downloadFile.getName());
        }else{
            String[] checkMessage = {" 查询结果为空",""};
            throw new AlgException("BEYOND.ALG.MODEL.COMMON.VALID.0000003",checkMessage);
        }
    }

    @Override
    public String uploadEditorImage(MultipartFile file, String usrCode) throws AlgException{
        File targetFile=targetFile(file);
        String pathUrl=path(targetFile,usrCode);
        return pathUrl;
    }
    public String path(File file, String usrCode){
        String cephKey=UUIDUtil.createUUID();
        log.info("用户code:{},path:{}",usrCode,path);

        // 注意之前上传图片还在需要优化
        Bucket bucket=amazonS3.createBucket("algmarket");
        amazonS3.putObject(bucket.getName(), cephKey,file);
        amazonS3.setObjectAcl(bucket.getName(), cephKey, CannedAccessControlList.PublicRead);
        String pathUrl=  amazonS3.getUrl(bucket.getName(),cephKey).toString();
        file.delete();
        return pathUrl;
    }
    public File targetFile(MultipartFile file){
        File targetFile = new File(path+file.getOriginalFilename());
        log.info("文件名:{}",file.getOriginalFilename());
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.info("文件转存错误",e);
        }
        return targetFile;
    };


}
