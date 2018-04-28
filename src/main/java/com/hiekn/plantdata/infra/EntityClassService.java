package com.hiekn.plantdata.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实体类业务逻辑层
 */
public interface EntityClassService {
    /**
     * 新建实体类
     *
     * @param userId
     * @param eClassname
     * @return
     */
    Map<String, Object> saveEntitysClassInfo(String userId, String eClassname);

    /**
     * 根据实体ID ，获取实体信息
     *
     * @param id
     * @return
     */
    public Map<String, Object> getEntitysClassInfo(long id,String usrID);

    /**
     * 更新实体类数据
     *
     * @param userId
     * @param id
     * @param json
     * @return
     */
    public boolean updateEntityClassInfo(String userId, long id, String json);

    /**
     * 模糊查询实体类列表
     *
     * @param searchStr
     * @param page
     * @return
     */
    List<Map<String, Object>> getEntitysClassList(String userId,String searchStr, Integer page);


    /**
     * 获取属性列表
     *
     * @param mid
     * @param searchStr
     * @param iPage
     * @return
     */
    List<Map<String, Object>> getAttributesList(String mid, String searchStr, Integer iPage);

    /**
     * 获取关系列表
     *
     * @param mid
     * @param searchStr
     * @param iPage
     * @return
     */
    List<Map<String, Object>> getRelbutesList(String mid, String searchStr, Integer iPage);

    /**
     * 根据当前用户，查询该用户下的实体类型
     * @param usrID
     * @return
     */
    public List<Long>  getUsrGraphEntityTypeList(String usrID);

}
