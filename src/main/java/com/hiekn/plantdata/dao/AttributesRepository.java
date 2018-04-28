package com.hiekn.plantdata.dao;

import com.hiekn.plantdata.Entity.Attributes;
import com.hiekn.plantdata.Entity.ResultheadData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 实体DAO层
 */
public interface AttributesRepository extends GraphRepository<Attributes> {


}
