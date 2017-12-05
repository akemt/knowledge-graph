package com.beyond.algm.algmalgorithmsboot.infra.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.beyond.algm.algmalgorithmsboot.infra.CephService;
import com.beyond.algm.algmalgorithmsboot.model.CephConfigModel;
import com.beyond.algm.algmalgorithmsboot.util.CephUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgUser;
import com.ceph.rados.IoCTX;
import com.ceph.rados.Rados;
import com.ceph.rbd.Rbd;
import com.ceph.rbd.RbdException;
import com.ceph.rbd.RbdImage;
import com.ceph.rbd.jna.RbdImageInfo;
import com.ceph.rbd.jna.RbdSnapInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：
 * @Description:
 * @date ：13:21 2017/11/27
 */
@Service
@Slf4j
public class CephServiceImpl implements CephService {
    @Value("${ceph.host}")
    private  String host;
    @Value("${ceph.accessKey}")
    private   String accessKey;
    @Value("${ceph.secretKey}")
    private  String secretKey;
    @Value("${ceph.path}")
    private  String path;
    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    private CephConfigModel cephConfigModel;
    // 图片上传
    @Override
    public void upload(MultipartFile file, String usrCode) throws AlgException{
        String cephKey=""+System.currentTimeMillis();
        log.info("文件名:{},用户code:{},accessKey:{},secretKey:{},path:{}",file.getOriginalFilename(),usrCode,accessKey,secretKey,path);
        File targetFile = new File(path+file.getOriginalFilename());
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
           file.transferTo(targetFile);
       } catch (Exception e) {
         System.out.println(e);
       }
        AmazonS3 conn= CephUtil.connectCeph(accessKey,secretKey,host);
        Bucket bucket=null;
        // 此方法以后废弃目前先用后期优化，或者循环判断是否存在
        // 判断bucket是否存在
   /*     if(!conn.doesBucketExist(image)){
             bucket=conn.createBucket(image);
            File filePath = new File(  file.getOriginalFilename());
            System.out.println(targetFile.getName());
            conn.putObject("image", targetFile.getName(),targetFile);
        }else{
            conn.deleteObject(bucket.getName(), file.getName());
            conn.putObject("image", targetFile.getName(),targetFile);
        }*/
        bucket=conn.createBucket(usrCode);
        conn.putObject(usrCode, cephKey,targetFile);
        conn.setObjectAcl(bucket.getName(), cephKey, CannedAccessControlList.PublicRead);
        String pathUrl=  conn.getUrl(bucket.getName(),cephKey).toString();
        log.info("ceph的url的存储:{}",pathUrl);
        AlgUser user=new AlgUser();
        user.setUsrCode(usrCode);
        user.setUsrUrl(pathUrl);
        algUserMapper.update(user);
        targetFile.delete();

    }

}