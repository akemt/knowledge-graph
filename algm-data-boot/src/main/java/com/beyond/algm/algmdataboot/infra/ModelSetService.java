package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.model.AlgModel;
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
     * @author ：huangjinqing
     * @Description:添加模型集
     * @param：User
     * @date ：20:22 2017/10/17
     */
    void addModelSet(AlgModelSet modelSet) throws AlgException;

    /**
     * @author ：huangjinqing
     * @Description: 删除模型集
     * @param： String modelSetSn
     * @date ： 18:59 2017/10/18
     */
    int deleteModelSet(AlgModel algModel) throws AlgException;

    /**
     * @author ：huangjinqing
     * @Description: 添加模型
     * @param： AlgModel
     * @date ：19：11 2017/10/18
     */
    Result addAlgModel(AlgModel algModel) throws Exception;

    /**
     * @author ：huangjinqing
     * @Description:  删除模型
     * @param： String modelSn
     * @date ：19:22 2017/10/18
     */
    int deleteModel( AlgModel algModel ) throws AlgException;

    /**
     * @author ：huangjinqing
     * @Description: 根据模型集串号删除模型
     * @param： String
     * @date ：15:213 2017/10/19
     */
    Result deleteModelByModelSetSn(String modelSetSn) throws Exception;

    /**
     * @author ：huangjinqing
     * @Description: 修改模型信息
     * @param： AlgModel
     * @date ：19：11 2017/10/18
     */
    Result modifyAlgModel(AlgModel algModel) throws Exception;

    /**
     * @author ：huangjinqing
     * @Description: 查询用户的模型集
     * @param： String usrSn
     * @date ：13：46 2017/10/21
     */
    List<AlgModelSetVo> queryAlgModelSet(String usrSn) throws AlgException;


    /**
     * @author ：huangjinqing
     * @Description: 查询模型集的模型
     * @param： algModelSet
     * @date ：13：46 2017/10/21
     */
    List<ModelDataVo> queryAlgModel(AlgModel algModel) throws AlgException;

     AlgUser findByUsrCode(String usrCode);

    List<ModelDataVo>  queryModelDataSet(ModelDataVo modelDataVo) throws AlgException;

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
    void uploadModelSet(MultipartFile file, String usrCode, String modelName, String dataUuid,String usrSn) throws AlgException;

    /**
     * @author ：zhangchuanzhi
     * @Description:用户下载数据
     * @param： String usrSn，dataSetName
     * @date ： 2017-12-06 21:54:06
     */
    void  downModelUrl(String  usrSn,String modelSet,String fileName,String usrCode,HttpServletResponse response)throws AlgException;
}
