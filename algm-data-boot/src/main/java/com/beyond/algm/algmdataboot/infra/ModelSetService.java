package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgModel;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgModelSetVo;
import com.beyond.algm.vo.ModelDataVo;
import com.github.pagehelper.PageInfo;

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
    Result addAlgModel(AlgModel algModel,String usrCode) throws AlgException;

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
    PageInfo<AlgModelSetVo> queryAlgModelSet(String usrSn, PageInfo pageInfo) throws AlgException;


    /**
     * @author ：huangjinqing
     * @Description: 查询模型集的模型
     * @param： algModelSet
     * @date ：13：46 2017/10/21
     */
    PageInfo<ModelDataVo> queryAlgModel(AlgModel algModel, PageInfo pageInfo) throws AlgException;

     AlgUser findByUsrCode(String usrCode);

    PageInfo<ModelDataVo>  queryModelDataSet(ModelDataVo modelDataVo,PageInfo pageInfo) throws AlgException;

}
