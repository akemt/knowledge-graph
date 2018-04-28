package com.hiekn.plantdata.Entity;

import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

@QueryResult
public class ResultheadData {

    public List<NodeModel> nodes;

    public List<RelationshipModel> relationships;

    public List<NodeModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeModel> nodes) {
        this.nodes = nodes;
    }

    public List<RelationshipModel> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipModel> relationships) {
        this.relationships = relationships;
    }
}
