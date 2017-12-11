package com.beyond.algm.algmfileboot.infra;

import com.beyond.algm.exception.AlgException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：zhangchuanzhi
 * @Description:ceph调用
 * @date ：13:14 2017/11/27
 */
public interface CephService {
  /**
   *
   * @param file
   * @param usrCode
   * @throws AlgException
   */
  void upload(MultipartFile file, String usrCode) throws AlgException;

}
