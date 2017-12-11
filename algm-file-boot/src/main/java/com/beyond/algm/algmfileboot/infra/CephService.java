package com.beyond.algm.algmfileboot.infra;

import com.beyond.algm.exception.AlgException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：zhangchuanzhi
 * @Description:ceph调用
 * @date ：13:14 2017/11/27
 */
public interface CephService {
  // 图片上传
  void upload(MultipartFile file, String usrCode) throws AlgException;

}
