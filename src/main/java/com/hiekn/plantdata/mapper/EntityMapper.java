package com.hiekn.plantdata.mapper;

import com.hiekn.plantdata.Entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 实体-Mapper
 */
@Repository
public interface EntityMapper {


    /**
     * 保存实体
     *
     * @param entityType
     */
    void saveEntityInfo(UsrEntity entityType);

    /**
     * 保存实体-关系
     *
     * @param entRelType
     */
    void saveEntRelInfo(UsrEntRel entRelType);

    /**
     * 保存实体-属性
     *
     * @param entAttribType
     */
    void saveEntAttrInfo(UsrEntAttrib entAttribType);



    /**
     * 根据实体ID ，删除其下的属性
     * @param strId
     */
    void deleteEntAttrInfoByEntID(String userId, String strId);

    /**
     * 根据实体ID ，删除其下的关系
     * @param strId
     * @param type
     */
    void deleteEntRelInfoByEntID(String strId,String type,String userId);

    /**
     * 根据关系ID ，删除其下的关系
     * @param strId
     */
    void deleteEntRelInfoByID(String strId);

    /**
     * 根据条件查询，该用户下的实体关系
     * @param usrID
     * @param strType
     * @return
     */
    List<Map<String,String>> getUsrGraphRelationList(String usrID,String strType);

    /**
     * 根据条件查询，该用户下,该实体下关系
     * @param usrID
     * @param strType
     * @param entID
     * @return
     */
    List<Map<String,String>> getUsrGraphRelationListByUsrIDAndEntID(String usrID,String strType,String entID);

    /**
     * 根据条件查询，查询该用户下的实体
     * @param usrID
     * @return
     */
    List<Map<String,String>> getUsrGraphNodesList(String usrID);

    /**
     * 根据条件查询，该用户下的属性
     *
     * @param usrID
     * @param entID
     * @return
     */
    List<Map<String, String>> getUsrGraphEntityAttrList(String usrID, String entID);


    /**
     * 根据条件查询，该用户下的实体
     *
     * @param usrID * @param EntName
     * @return
     */
    List<Map<String, String>> getUsrGraphEntityListByName(String usrID, String EntName);
}
