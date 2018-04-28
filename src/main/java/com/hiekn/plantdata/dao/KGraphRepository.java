package com.hiekn.plantdata.dao;

import com.hiekn.plantdata.Entity.ResultheadData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/*
    CoderRepositiory 继承 GraphRepository类，实现增删查改
    实现自己的接口：通过名字查询Entity（可以是单个节点，也可以是一组节点List集合）
    spring-data-neo4j 支持方法命名约定查询 findBy{Coder的属性名}
    findBy后面的属性名一定要在Entity节点实体类里存在，否则会报错
    */
@Repository
public interface KGraphRepository extends GraphRepository<ResultheadData> {


    /**
     * 查找全部分析主体
     * @return
     */
//    @Query("MATCH (n) RETURN  distinct labels(n) as labels ")
    @Query("MATCH path = (n:level2:实体类)  where id(n) in {id}  RETURN nodes(path) as nodes;")
    List<ResultheadData> findAllLabels(@Param("id") long[] id);

    /**
     * 查找此用户下全部分析关系
     * @param id
     * @return
     */
    @Query("MATCH ()-[r]-()   where id(r) in {id}  RETURN  distinct type(r) as relationType")
    List<LinkedHashMap> findAllRelationTypes(@Param("id") long[] id);


    @Query("MATCH path = (n)-{distance}-(p) where id(n)={en_id} and n<>p and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findGraphArrangement(@Param("distance") String[] distance,@Param("en_id") long en_id, @Param("type") String[] type, @Param("labels") String[] labels);

//    @Query("MATCH path = (n)-[r]-(p) where id(n)={en_id} and n<>p and type(r) in {type} " +
//            "and ALL (x in labels(p) where x in {labels}) " +
//            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    @Query("MATCH path = (n)-[*1]-(p) where id(n) in {en_id} and n<>p and ALL ( r in relationships(path) " +
        "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
        "RETURN  nodes(path) as nodes,relationships(path) as relationships LIMIT {pageSize};")
    List<ResultheadData> findGraphArrangement1(@Param("en_id") long[] en_id, @Param("type") String[] type, @Param("labels") String[] labels,@Param("pageSize")Integer pageSize);

//    @Query("MATCH path = (n)-[r]-(p) where id(n)={en_id} and n<>p and type(r) in {type} " +
//            "and ALL (x in labels(p) where x in {labels}) " +
//            "RETURN  nodes(path) as nodes,relationships(path) as relationships " +
//            "union " +
//            "MATCH path = (n)-[r1]-(p)-[r2]-(q) where id(n)={en_id} and n<>p<>q and type(r1) in {type} " +
//            "and ALL (x in labels(p) where x in {labels}) and type(r2) in {type} " +
//            "and ALL (x in labels(q) where x in {labels}) RETURN  nodes(path) as nodes,relationships(path) as relationships ")
@Query("MATCH path = (n)-[*1..2]-(p) where id(n)  in {en_id} and n<>p and ALL ( r in relationships(path) " +
        "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
        "RETURN  nodes(path) as nodes,relationships(path) as relationships LIMIT {pageSize};")
    List<ResultheadData> findGraphArrangement2(@Param("en_id") long[] en_id, @Param("type") String[] type, @Param("labels") String[] labels,@Param("pageSize")Integer pageSize);

//    @Query("MATCH path = (n)-[r]-(p) where id(n)={en_id} and n<>p and type(r) in {type} " +
//            "and ALL (x in labels(p) where x in {labels}) RETURN  nodes(path) as nodes,relationships(path) as relationships " +
//            "union " +
//            "MATCH path = (n)-[r1]-(p)-[r2]-(q) where id(n)={en_id} and n<>p<>q and type(r1) in {type} " +
//            "and ALL (x in labels(p) where x in {labels}) and type(r2) in {type} and ALL (x in labels(q) where x in {labels}) " +
//            "RETURN  nodes(path) as nodes,relationships(path) as relationships " +
//            "union " +
//            "MATCH path = (n)-[r1]-(p)-[r2]-(q)-[r3]-(s) where id(n)={en_id} and n<>p<>q<>s and type(r1) in {type} " +
//            "and ALL (x in labels(p) where x in {labels}) and type(r2) in {type} and ALL (x in labels(q) where x in {labels}) and type(r3) in {type} " +
//            "and ALL (x in labels(s) where x in {labels}) RETURN  nodes(path) as nodes,relationships(path) as relationships")
    @Query("MATCH path = (n)-[*1..3]-(p) where id(n)  in {en_id} and n<>p and ALL ( r in relationships(path) " +
        "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
        "RETURN  nodes(path) as nodes,relationships(path) as relationships LIMIT {pageSize};")
    List<ResultheadData> findGraphArrangement3(@Param("en_id") long[] en_id, @Param("type") String[] type, @Param("labels") String[] labels,@Param("pageSize")Integer pageSize);


    @Query("MATCH path = (n)-[*1..3]-(p) where id(n)={start_id} and id(p)={end_id} and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findPathArrangement3(@Param("start_id") long start_id, @Param("end_id") long end_id, @Param("type") String[] type, @Param("labels") String[] labels);

    @Query("MATCH path = (n)-[*1..4]-(p) where id(n)={start_id} and id(p)={end_id} and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findPathArrangement4(@Param("start_id") long start_id, @Param("end_id") long end_id,@Param("type") String[] type, @Param("labels") String[] labels);


    @Query("MATCH path = (n)-[*1..5]-(p) where id(n)={start_id} and id(p)={end_id} and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findPathArrangement5(@Param("start_id") long start_id, @Param("end_id") long end_id, @Param("type") String[] type, @Param("labels") String[] labels);

    @Query("MATCH path = (n)-[*1..6]-(p) where id(n)={start_id} and id(p)={end_id} and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findPathArrangement6(@Param("start_id") long start_id, @Param("end_id") long end_id, @Param("type") String[] type, @Param("labels") String[] labels);
    /**
     * 通过ID 。查询实体信息
     * @param entityId
     * @return
     */
    @Query("Match path=(n) where id(n)={id} return nodes(path) as nodes")
    List<ResultheadData> findById(@Param("id") long entityId);


    /**
     * 模糊查询
     * @param name
     * @return
     */
    @Query("Match path=(n:level3:实体) where n.name=~{name}  return nodes(path) as nodes")
    List<ResultheadData> findVagueGraphDataByName(@Param("name") String name);


    /**
     * 通过ID 。查询实体Labels
     * @param entityId
     * @return
     */
    @Query("match (n) where id(n)={id} return labels(n) as labels")
    List<LinkedHashMap> findLabelsById(@Param("id") long entityId);



    @Query("MATCH path = (n)-[*1..3]-(p) where id(n) in {ids} and n<>p and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findRelationArrangement3(@Param("ids") long[] ids,  @Param("type") String[] type, @Param("labels") String[] labels);

    @Query("MATCH path = (n)-[*1..4]-(p) where id(n) in {ids} and n<>p and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findRelationArrangement4(@Param("ids") long[] ids,@Param("type") String[] type, @Param("labels") String[] labels);


    @Query("MATCH path = (n)-[*1..5]-(p) where id(n) in {ids} and n<>p and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findRelationArrangement5(@Param("ids") long[] ids, @Param("type") String[] type, @Param("labels") String[] labels);

    @Query("MATCH path = (n)-[*1..6]-(p) where id(n) in {ids} and n<>p and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships")
    List<ResultheadData> findRelationArrangement6(@Param("ids") long[] ids, @Param("type") String[] type, @Param("labels") String[] labels);



    @Query("MATCH path = (n)-[*1]-(p) where id(n)={en_id} and n<>p and ALL ( r in relationships(path) " +
            "where type(r) in {type}) and ALL (x in labels(p) where x in {labels}) " +
            "RETURN  nodes(path) as nodes,relationships(path) as relationships limit {limit}")
    List<ResultheadData> findGraphNext(@Param("en_id") long en_id, @Param("type") String[] type, @Param("labels") String[] labels,@Param("limit") Integer limit,@Param("toTime")  String toTime);
}
