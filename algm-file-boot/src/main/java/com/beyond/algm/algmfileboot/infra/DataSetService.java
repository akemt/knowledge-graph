package com.beyond.algm.algmfileboot.infra;

import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgAuthCode;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ZhangJiayue
 * @Description 数据管理数据模块service接口声明
 * @data 2017-10-19 10:29:44
 */
public interface DataSetService {

    /**
     * @author ：zhangchuanzhi
     * @Description: 个人数据文件上传
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:06
     */
    AlgData uploadDateSet(MultipartFile file, String usrCode, String dataSetName, String dataUuid, String usrSn) throws AlgException;
    /**
     * @author ：zhangchuanzhi
     * @Description:检查文件上传名字
     * @param： String dataSn
     * @date ： 2017-12-06 21:54:06
     */
    int checkFileName(AlgData algData) throws AlgException;

    /**
     * @author ：zhangchuanzhi
     * @Description:查找数据url
     * @param： String usrSn，dataSetName
     * @date ： 2017-12-06 21:54:06
     */
    String  dataUrl(AlgData algData)throws AlgException;


    /**
     * @author ：zhangchuanzhi
     * @Description:用户下载数据
     * @param： String usrSn，dataSetName
     * @date ： 2017-12-06 21:54:06
     */
    void  downDataUrl(String usrSn, String dataSet, String fileName, String usrCode, HttpServletResponse response)throws AlgException;


    /**
     * @author ：zhangchuanzhi
     * @Description:查询用户usrSn
     * @param： String acdId，
     * @date ： 2017-12-19 21:54:06
     */
    AlgAuthCode selectUsr(String acdId)throws AlgException;
}
