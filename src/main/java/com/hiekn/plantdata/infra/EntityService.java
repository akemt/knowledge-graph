package com.hiekn.plantdata.infra;


import java.util.List;
import java.util.Map;

public interface EntityService {


    /**
     * 获得实体列表
     *
     * @param searchStr
     * @return
     */
    public List<Map<String, Object>> getEntitysList(String userId,String searchStr, Integer iPage);


    /**
     * 更新实体数据
     *
     * @param userId
     * @param id
     * @param json
     * @return
     */
    public boolean updateEntitysInfo(String userId, long id, String json);


    /**
     * 根据实体ID ，获取实体信息
     *
     * @param id
     * @return
     */
    public Map<String, Object> getEntitysInfo(long id,String usrID);

    /**
     * 新建实体关系
     *
     * @param userId
     * @param id
     * @param json
     * @return
     */
    public Map<String, Object> addEntitysRelation(String userId, long id, String json);


    /**
     * 获得实体关系列表
     *
     * @param id
     * @param userID
     * @return
     */
    public List<Map<String, Object>> getEntitysRelation(long id, String userID);


    /**
     * 删除关系
     *
     * @param userId
     * @param id
     * @return
     */
    public boolean deleteEntitysRelation(String userId, long id);


    /**
     * 以实体类新建实体
     *
     * @param userId
     * @param eClassname
     * @return
     */
    public Map<String, Object> saveEntitysInfo(String userId, String eClassname,long mID);

    /**
     * 根据当前用户，查询该用户下的实体关系
     * @param usrID
     * @return
     */
    public List<Long>  getUsrGraphRelationList(String usrID);

    /**
     * 根据当前用户，查询该用户下的实体
     * @param usrID
     * @return
     */
    public List<Long>  getUsrGraphNodesList(String usrID);
}
