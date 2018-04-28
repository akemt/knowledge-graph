package com.hiekn.plantdata.dao;

import com.hiekn.plantdata.Entity.ResultheadData;
import org.neo4j.ogm.annotation.Labels;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 实体DAO层
 */
public interface EntitysRepository  extends GraphRepository<ResultheadData> {

    /**
     * 通过ID 。查询实体信息
     * @param entityId
     * @return
     */
    @Query("Match path=(n) where id(n)={id} return nodes(path) as nodes")
    List<ResultheadData> findById(@Param("id") long entityId);


    /**
     * 通过ID 。查询其下树节点属性信息-level2和level3
     * @param entityId
     * @return
     */
    @Query("MATCH path = (n)-[*1..10]->(p:属性) where id(n)={entityId} and id(p) in {attrID} return nodes(path) as nodes")
    List<ResultheadData> findTreeDataById(@Param("entityId") long entityId,@Param("attrID") long []attrID);


    /**
     * 通过ID 。查询该实体信息上一级节点
     * @param entityId
     * @return
     */
    @Query("Match path = (m)-[r]->(n) where id(n) ={entityId} return nodes(path) as nodes")
    List<ResultheadData> findParentNodeById(@Param("entityId") long entityId);


    /**
     * 通过ID 。查询此实体下属关系和对应实体信息
     * @param entityId
     * @return
     */
    @Query("MATCH path=(m:实体:level3)-[r]->(n:实体:level3)  where id(m) = {entityId} return nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> getEntitysRelation(@Param("entityId") long entityId);


    /**
     * 通过ID 。查询实体信息
     * @param global_name
     * @return
     */
    @Query("MERGE  (n:{global_name} {name:{global_name}) RETURN id(n)")
    long saveEntitys(@Param("labels_name") String labels_name,@Param("global_name") String global_name);


    /**
     * 根据名称查询实体类Level2层 下面所有节点
     * @param name
     * @param limit
     * @return
     */
    @Query("MATCH path = (n:level2:实体类) where id(n) in {id} and n.name =~{name} RETURN nodes(path) as nodes LIMIT {limit};")
    List<ResultheadData> findEntitysClassList(@Param("id") long[] id,@Param("name") String name, @Param("limit") Integer limit);


    /**
     * 根据节点ID ,查询实体类Level2层 节点名称
     * @param id
     * @return
     */
    @Query("MATCH path = (n:level2:实体类) where id(n) ={id} RETURN nodes(path) as nodes;")
    ResultheadData findEntitysClassInfo(@Param("id") long id);

    /**
     * 根据名称,查询实体类Level2层节点-验证此名称是否存在
     * @param name
     * @return
     */
    @Query("MATCH path = (n:level2:实体类) where n.name ={name} RETURN nodes(path) as nodes;")
    List<ResultheadData> findEntitysClassList(@Param("name") String name);

    /**
     * 根据名称,查询实体类Level3层节点-验证此名称是否存在
     * @param name
     * @return
     */
    @Query("MATCH path = (n:level3) where n.name ={name} RETURN nodes(path) as nodes;")
    List<ResultheadData> findEntitysList(@Param("name") String name);


    /**
     * 根据名称查询实体类Level3层 下面所有节点
     * @param name
     * @param limit
     * @return
     */
    @Query("MATCH path = (n:level3:实体) where  id(n)  in {en_id} and n.name =~{name} RETURN nodes(path) as nodes LIMIT {limit};")
    List<ResultheadData> findEntitysList(@Param("en_id") long[] en_id, @Param("name") String name, @Param("limit") Integer limit);


    /**
     * 根据关系ID ，删除关系
     * @param id
     */
    @Query("match(a)-[r]-(b)where id(r)={id} delete r")
    void deleteRelationById(@Param("id") long id);


    /**
     * 根据名称查询实体类Level2层 下面所有属性
     * @param mId
     * @param name
     * @param limit
     * @return
     */
    @Query("MATCH path = (m:level2:实体类)-[*1..10]->(n:level2:属性) where id(m)={mId} and n.name =~{name} RETURN n as nodes LIMIT {limit};")
    List<LinkedHashMap> findEntitysAttrList(@Param("mId") long mId, @Param("name") String name, @Param("limit") Integer limit);

    /**
     * 根据名称查询实体类Level2层 下面所有属性
     * @param name
     * @param limit
     * @return
     */
    @Query("MATCH path = (n:level2:属性) where  n.name =~{name} RETURN nodes(path) as nodes LIMIT {limit};")
    List<ResultheadData> findEntitysAttrList(@Param("name") String name, @Param("limit") Integer limit);

    /**
     * 根据名称查询实体类Level2层 下面所有属性
     * @param mId
     * @param name
     * @param limit
     * @return
     */
    @Query("MATCH path = (m:level3:实体)-[r]->(n:level3:实体) where m.parentId={mId} and n.name =~{name} RETURN n as relationType LIMIT {limit};")
    List<LinkedHashMap> findEntitysRelationList(@Param("mId") long mId, @Param("name") String name, @Param("limit") Integer limit);

    /**
     * 删除该实体类 该用户下的节点和关系
     * @param mId
     * @param longsAttr
     * @param longsRel
     */
    @Query("MATCH (m)-[r]->(n) where id(m) = {mId} and id(n) in {longsAttr}   and id(r) in {longsRel}  DETACH DELETE r,n;")
     void deleteEntitysTypeRelation(@Param("mId") long mId, @Param("longsAttr") long[] longsAttr, @Param("longsRel") long[] longsRel);
}
