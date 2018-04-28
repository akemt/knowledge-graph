package com.hiekn.plantdata.infra;

/**
 * neo4j数据库驱动
 */
public interface Neo4jDriverService {


    /**
     * 保存，并返回ID
     * @param cql
     * @return
     */
    public long saveLabelsReturnID(String cql);

    /**
     * 保存节点或者关系
     * @param cql
     */
    public void saveLabels(String cql);
}
