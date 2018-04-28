package com.hiekn.plantdata.mapper;

import com.hiekn.plantdata.Entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 实体类-Mapper
 */
@Repository
public interface ModelMapper {
    /**
     * 保存实体类
     *
     * @param modelType
     */
    void saveEntitysClassInfo(EntityType modelType);

    /**
     * 保存实体类-关系
     *
     * @param modelType
     */
    void saveEntClassRelInfo(EntRelType modelType);

    /**
     * 保存实体类-属性
     *
     * @param modelType
     */
    void saveEntClassAttrInfo(EntAttribType modelType);


    /**
     * 根据实体类ID ，删除其下的属性
     *  @param userId
     * @param strId
     */
    void deleteEntClassAttrInfoByEntID(String userId,String strId);

    /**
     * 根据实体类ID ，删除其下的关系
     *@param userId
     * @param strId
     */
    void deleteEntClassRelInfoByEntID(String userId,String strId);

    /**
     * 根据条件查询，该用户下的实体类型
     *
     * @param usrID
     * @return
     */
    List<Map<String, String>> getUsrGraphEntityTypeList(String usrID);

    /**
     * 根据条件查询，该用户下的实体类型
     *
     * @param usrID * @param EntName
     * @return
     */
    List<Map<String, String>> getUsrGraphEntityTypeListByName(String usrID, String EntName);

    /**
     * 根据条件查询，该用户下，此实体类的属性
     *
     * @param usrID
     * @param entID
     * @return
     */
    List<Map<String, String>> getUsrGraphEntityAttrList(String usrID, String entID);

    /**
     * 根据条件查询，该用户下，此实体类的关系
     *
     * @param usrID
     * @param entID
     * @return
     */
    List<Map<String, String>> getUsrGraphEntityRelList(String usrID, String entID);
}
