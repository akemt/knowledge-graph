package com.beyond.algo.algodataboot.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgModelSet;
import com.beyond.algo.model.AlgModel;

import javax.management.relation.RoleList;
import java.lang.reflect.Array;
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
    Result addModelSet(AlgModelSet modelSet) throws Exception;

    /**
     * @author ：huangjinqing
     * @Description: 删除模型集
     * @param： String modelSetSn
     * @date ： 18:59 2017/10/18
     */
    Result deleteModelSet(String modelSetSn) throws Exception;

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
    Result deleteModel(String modelSn) throws Exception;

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
    Result queryAlgModelSet(String usrSn) throws Exception;


    /**
     * @author ：huangjinqing
     * @Description: 查询模型集的模型
     * @param： String AlgModelSetSn
     * @date ：13：46 2017/10/21
     */
    Result queryAlgModel(String modelSetSn) throws Exception;

}
