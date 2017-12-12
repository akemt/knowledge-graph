package com.beyond.algm.algmfileboot.infra;

import com.beyond.algm.exception.AlgException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
  void userHeadImgUpload(MultipartFile file, String usrCode) throws AlgException;

  /**
   *
   * @param path
   * @param response
   * @throws AlgException
   */
  void  userHeadImgDownload(String path,HttpServletResponse response)throws AlgException;

  /**
   *
   * @param
   * @param
   * @throws AlgException
   */
  String uploadEditorImage(MultipartFile file, String usrCode) throws AlgException;

}
