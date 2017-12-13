package com.beyond.algm.algmfileboot.infra;

import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgModel;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgModelSetVo;
import com.beyond.algm.vo.ModelDataVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author huangjinqing
 * @version Created in：20:22 2017/10/17
 * @Description:数据管理模型模块service接口声明
 */

public interface ModelSetService {

    /**
     * @author ：zhangchuanzhi
     * @Description:检查模型上传名字
     * @param： AlgData
     * @date ： 2017-12-06 21:54:06
     */
    int checkFileName(AlgModel algData) throws AlgException;

    /**
     * @author ：zhangchuanzhi
     * @Description: 个人模型文件上传
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:06
     */
    void uploadModelSet(MultipartFile file, String usrCode, String modelName, String dataUuid, String usrSn) throws AlgException;

    /**
     * @author ：zhangchuanzhi
     * @Description:用户下载数据
     * @param： String usrSn，dataSetName
     * @date ： 2017-12-06 21:54:06
     */
    void  downModelUrl(String usrSn, String modelSet, String fileName, String usrCode, HttpServletResponse response)throws AlgException;
}
