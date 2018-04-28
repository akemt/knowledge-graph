package com.hiekn.plantdata.infra.impl;

import com.google.gson.reflect.TypeToken;
import com.hiekn.plantdata.Entity.*;
import com.hiekn.plantdata.Entity.Entity;
import com.hiekn.plantdata.bean.EntityProfileBean;
import com.hiekn.plantdata.bean.graph.EntityBean;
import com.hiekn.plantdata.bean.graph.GraphBean;
import com.hiekn.plantdata.bean.graph.KVBean;
import com.hiekn.plantdata.bean.graph.SchemaBean;
import com.hiekn.plantdata.bean.item.*;
import com.hiekn.plantdata.bean.rest.RestData;
import com.hiekn.plantdata.dao.*;
import com.hiekn.plantdata.exception.ServiceException;
import com.hiekn.plantdata.infra.EntityClassService;
import com.hiekn.plantdata.infra.EntityService;
import com.hiekn.plantdata.infra.GraphService;
import com.hiekn.plantdata.parser.EntityParser;
import com.hiekn.plantdata.parser.GraphParser;
import com.hiekn.plantdata.util.ArrayUtils;
import com.hiekn.plantdata.util.JSONUtils;
import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;

/**
 * 实现类
 */
@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    private KGraphRepository kGraphRepository;

    @Autowired
    private EntityService entityService;

    @Autowired
    private EntityClassService entityClassService;

    @Autowired
    private EntitysRepository entitysRepository;


    public SchemaBean getAllAtts(String userId) {

        //根据当前用户，获取其下的实体关系
        List<Long>   longList = entityService.getUsrGraphRelationList(userId);

        SchemaBean schemaBean = new SchemaBean();

        List<LinkedHashMap> relationList = kGraphRepository.findAllRelationTypes(ArrayUtils.listDataToLong(longList));

        for (int i = 0; i < relationList.size(); i++) {
            LinkedHashMap nodeList = relationList.get(i);
            String v = String.valueOf(nodeList.get("relationType"));
            Integer type = 1;
            schemaBean.addAtt(v, v, type);
        }
        schemaBean.addAtt("实体类_包含_属性", "实体类_包含_属性", 1);
        schemaBean.addAtt("属性_包含_属性", "属性_包含_属性", 1);
        return schemaBean;
    }

    public List<KVBean<String, String>> getAllTypes(String usrID) {


        List<Long>  longList = entityClassService.getUsrGraphEntityTypeList(usrID);
        List<KVBean<String, String>> rsList = new ArrayList();

        List<ResultheadData> infoList = kGraphRepository.findAllLabels(ArrayUtils.listDataToLong(longList));
        for (int i = 0; i < infoList.size(); i++) {
            ResultheadData resultheadData1 = infoList.get(i);
            for (NodeModel nodesEntity : resultheadData1.getNodes()) {
                if (nodesEntity.getPropertyList().size() > 0) {
                    Object name = nodesEntity.property("name");
                    Long ids = nodesEntity.getId();
                    KVBean<String, String> sb = new KVBean((String) name, (String) name);
                    rsList.add(sb);
                }
            }
        }
        KVBean<String, String> sb = new KVBean("属性", "属性");
        rsList.add(sb);
        return rsList;
    }

    public GraphBean kg_graph_full_hasatts(String usrID, Long entityId, Integer distance, Integer direction, List<String> allowAtts, List<String> allowTypes, boolean isMergeRelation,Integer pageSize) {

        //查询当前用户下的所有节点
//        List<Long>  longList = entityService.getUsrGraphNodesList(usrID);

        List<Long>  longList = new ArrayList<>();
        longList.add(entityId);
        //分析层次
        List<ResultheadData> listResultheadData = null;
        if (distance == 1) {
            listResultheadData = kGraphRepository.findGraphArrangement1(ArrayUtils.listDataToLong(longList), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes),pageSize);
        } else if (distance == 2) {
            listResultheadData = kGraphRepository.findGraphArrangement2(ArrayUtils.listDataToLong(longList), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes),pageSize);
        } else {
            listResultheadData = kGraphRepository.findGraphArrangement3(ArrayUtils.listDataToLong(longList), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes),pageSize);
        }
        GraphBean graphBean = this.resultheadData(entityId,listResultheadData, isMergeRelation,null);
        return graphBean;
    }

    public String queryLablesByID(long id){
        List<LinkedHashMap>  labelsList = kGraphRepository.findLabelsById(id);
        String[] str = new String[labelsList.size()];
        String strLabels = "";
        for (int i = 0; i < labelsList.size(); i++) {
            LinkedHashMap nodeList = labelsList.get(i);
            str = (String[]) nodeList.get("labels");
            for(int j=0;j< str.length;j++){
                if(!str[j].equals("实体") && !str[j].equals("level3")){
                    strLabels = str[j];
                }
            }
        }
        return strLabels;
    }

    private String data2MapList(List<Long> list, List<Long> keyList) {
        Map<String, List<Long>> map = new HashMap();

        for(int i = 0; i < keyList.size(); ++i) {
            map.put("" + keyList.get(i), list);
        }

        return JSONUtils.toJson(map);
    }

    public List<EntityBean> getPrompt(String userId, String kw, List<Long> allowTypes) {

        String kws = "."+"*"+kw+"."+"*";
//        List<ResultheadData> listNode = kGraphRepository.findVagueGraphDataByName(kws);
        //查询当前用户下的所有节点
        List<Long>  longListNode = entityService.getUsrGraphNodesList(userId);

        List<ResultheadData> listNode = entitysRepository.findEntitysList(ArrayUtils.listDataToLong(longListNode),kws,20);

        List<EntityBean> entityList = new ArrayList<EntityBean>();
        EntityBean enterprise = new EntityBean();
        List<Long> longList = new ArrayList<Long>();
        for (int i = 0; i < listNode.size(); i++) {

            ResultheadData resultheadData1 = listNode.get(i);
            for (NodeModel nodesEntity : resultheadData1.getNodes()) {
                enterprise = new EntityBean();
                if (nodesEntity.getPropertyList().size() > 0) {
                    Object name = nodesEntity.property("name");
                    enterprise.setName((String) name);
                }
                long eID = Long.valueOf(String.valueOf(nodesEntity.getId()));
                enterprise.setId(eID);
                enterprise.setClassId(this.queryLablesByID(eID));
                enterprise.setImg("");
                enterprise.setKgType(1);
                entityList.add(enterprise);
            }
        }
        return entityList;
    }

    public EntityProfileBean kg_service_entity(String kgName, Long entityId, Boolean isRelationAtts, List<Long> allowAtts) {

        List<ResultheadData> infoList = kGraphRepository.findById(entityId);
//
        ServiceEntityItem entityItem = new ServiceEntityItem();
        EntityProfileBean entityProfileBean = new EntityProfileBean();
        List list = new ArrayList();
//        if(info != null){
//
        for (int i = 0; i < infoList.size(); i++) {

            ResultheadData resultheadData1 = infoList.get(i);
            for (NodeModel nodesEntity : resultheadData1.getNodes()) {
                if (nodesEntity.getPropertyList().size() > 0) {
                    Object name = nodesEntity.property("Name");
                    entityItem.setName((String) name);
                    entityItem.setAbs("");
                    Set<String> syns = new HashSet<String>();
                    syns.add("");
                    entityItem.setSyns(syns);
                    entityItem.setReAtts(list);
                    entityItem.setAtts(list);
                }
            }
        }
        entityItem.setId(entityId);
//
//            Entity entity = entityRepository.findByK(info.getClassId());
            List<SimpleEntityItem> entityItemList = new ArrayList<SimpleEntityItem>();
            SimpleEntityItem simpleEntityItem = new SimpleEntityItem();
            simpleEntityItem.setId(1);
            simpleEntityItem.setName("testss");
//            entityItemList.add(simpleEntityItem);
            entityItem.setPars(entityItemList);
            entityProfileBean = EntityParser.ServiceEntityItem2EntityProfileBean(entityItem,null);
//        }

        return entityProfileBean;
    }

    public GraphBean kg_path(String kgName, Long start, Long end, Integer distance, Integer direction, boolean isShortest, List<String> allowAtts, List<Long> disallowAtts, List<String> allowTypes, List<Long> disallowTypes) {

        //分析层次
        List<ResultheadData> listResultheadData = null;
        if (distance == 3) {
            listResultheadData = kGraphRepository.findPathArrangement3(start, end, ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        } else if (distance == 4) {
            listResultheadData = kGraphRepository.findPathArrangement4(start, end, ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        } else if (distance == 5) {
            listResultheadData = kGraphRepository.findPathArrangement5(start, end, ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        } else {
            listResultheadData = kGraphRepository.findPathArrangement6(start, end, ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        }
        GraphBean gb = this.resultheadData(start,listResultheadData, true,null);
        return gb;
    }

    public GraphBean kg_relation_full(String kgName, List<Long> ids, Integer distance, Integer direction, List<String> allowAtts, List<Long> disallowAtts, List<String> allowTypes, List<Long> disallowTypes) {

        //分析层次
        List<ResultheadData> listResultheadData = null;
        if (distance == 3) {
            listResultheadData = kGraphRepository.findRelationArrangement3(ArrayUtils.listDataToLong(ids), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        } else if (distance == 4) {
            listResultheadData = kGraphRepository.findRelationArrangement4(ArrayUtils.listDataToLong(ids), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        } else if (distance == 5) {
            listResultheadData = kGraphRepository.findRelationArrangement5(ArrayUtils.listDataToLong(ids), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        } else {
            listResultheadData = kGraphRepository.findRelationArrangement6(ArrayUtils.listDataToLong(ids), ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes));
        }
        GraphBean gb = this.resultheadData(ArrayUtils.listDataToLong(ids)[0],listResultheadData, true,null);
        return gb;
    }


    /**
     * 公共方法：组装JSON结构
     *
     * @param listResultheadData
     * @param isMergeRelation
     * @return
     */
    public GraphBean resultheadData(long entityId,List<ResultheadData> listResultheadData,boolean isMergeRelation,String bsTime) {

        GraphHasAttsItem graphHasAttsItem = new GraphHasAttsItem();
        Enterprise enterprise = null;
        if (listResultheadData.size() > 0) {
            List<Enterprise> entityList = new ArrayList<Enterprise>();
            List<RelationItem> relationItemList = new ArrayList<RelationItem>();
            for (int i = 0; i < listResultheadData.size(); i++) {
                ResultheadData resultheadData = listResultheadData.get(i);

                //实体List
                List<NodeModel> nodesModelList = resultheadData.getNodes();
                for (NodeModel nodesEntity : nodesModelList) {

                    enterprise = new Enterprise();
                    if (nodesEntity.getPropertyList().size() > 0) {
                        Object name = nodesEntity.property("name");
                        enterprise.setName((String) name);
                    }
                    long eID = Long.valueOf(String.valueOf(nodesEntity.getId()));
                    enterprise.setId(eID);
                    enterprise.setEn_id(eID);

                    enterprise.setClassId(this.queryLablesByID(eID));
                    enterprise.setImg("");
                    enterprise.setKgType(1);
                    entityList.add(enterprise);
                }
                graphHasAttsItem.setEntityList(ArrayUtils.removeDuplicateEnterprise(entityList));
//                graphHasAttsItem.setEntityList(entityList);

                //关系List
                RelationItem relationItem = null;
                List<RelationshipModel> relationshipsModelList = resultheadData.getRelationships();
                for (RelationshipModel relationshipModel : relationshipsModelList) {
                    relationItem = new RelationItem();
                    relationItem.setId(String.valueOf(relationshipModel.getId()));
                    relationItem.setAttId((String) ArrayUtils.property(relationshipModel.getPropertyList(), "name"));
                    relationItem.setAttName((String) ArrayUtils.property(relationshipModel.getPropertyList(), "name"));
                    Integer from = (Integer) ArrayUtils.property(relationshipModel.getPropertyList(), "from");
                    relationItem.setFromId(Integer.valueOf(from).longValue());
                    Integer to = (Integer) ArrayUtils.property(relationshipModel.getPropertyList(), "to");
                    relationItem.setToId(Integer.valueOf(to).longValue());


                    if(bsTime != null){
                        Map<String, String> infoMap = new HashMap<String, String>();
                        infoMap.put(bsTime,(String) ArrayUtils.property(relationshipModel.getPropertyList(), "bsTime"));
                        relationItem.setInfoMap(infoMap);
                    }
                    relationItemList.add(relationItem);
                }
                graphHasAttsItem.setRelationList(ArrayUtils.removeDuplicateRelationshipModel(relationItemList));
//                graphHasAttsItem.setRelationList(relationItemList);
            }
        } else {

            List<ResultheadData> resultheadDataList = kGraphRepository.findById(entityId);
            List<Enterprise> entityList1 = new ArrayList<Enterprise>();
            for (int i = 0; i < resultheadDataList.size(); i++) {

                ResultheadData resultheadData1 = resultheadDataList.get(i);
                for (NodeModel nodesEntity : resultheadData1.getNodes()) {

                    enterprise = new Enterprise();
                    if (nodesEntity.getPropertyList().size() > 0) {
                        Object name = nodesEntity.property("name");
                        enterprise.setName((String) name);
                    }
                    long eID = Long.valueOf(String.valueOf(nodesEntity.getId()));
                    enterprise.setEn_id(eID);
                    enterprise.setClassId(this.queryLablesByID(eID));
                    enterprise.setImg("");
                    enterprise.setKgType(1);
                    entityList1.add(enterprise);
                    graphHasAttsItem.setEntityList(entityList1);
                }
            }
        }

        GraphBean gb = GraphParser.GraphItem2GraphBean(graphHasAttsItem, isMergeRelation);
        return gb;
    }


    public GraphBean kg_graph_next_step(String kgName, Long entityId, Integer direction, Integer sort, List<String> allowAtts, List<String> allowTypes, String fromTime, String toTime, Integer limit, boolean isRelationMerge) {

        //分析层次
        List<ResultheadData>  listResultheadData = kGraphRepository.findGraphNext(entityId, ArrayUtils.listDataToString(allowAtts), ArrayUtils.listDataToString(allowTypes),limit,toTime);

        GraphBean gb = this.resultheadData(entityId,listResultheadData, isRelationMerge,"attr_time_from");
        return gb;
    }


    public RestData<GraphBean> getGraphByEntityId(Integer userId, String kgName, Long entityId, Long type, Integer direction, Integer subTreeLevel, Integer pageNo, Integer pageSize) {
        if (type.longValue() == 1L) {
            return getInsGraph(userId, kgName, entityId, direction, pageNo, pageSize);
        }

//        RestData entityById = this.editEntityService.getEntityById(userId, kgName, entityId);
//
//        if ((entityById == null) || (entityById.getRsData().size() <= 0)) {
//            return null;
//        }
//        EditEntityBean currentEntity = (EditEntityBean) entityById.getRsData().get(0);
//
//        if (subTreeLevel.intValue() > 1) {
//            pageNo = Integer.valueOf(1);
//            pageSize = Integer.valueOf(256);
//        }
//
//        GraphBean graphBean = null;
//
//        RestData treeRs = getTree(userId, kgName, entityId, Integer.valueOf(1), subTreeLevel, pageNo, pageSize);
//        graphBean = EditParser.treeItems2GraphBean(currentEntity, treeRs.getRsData(), null, graphBean);
//
//        if (subTreeLevel.intValue() <= 1) {
//            RestData restData = this.editEntityService.getInstancesByConceptId(userId, kgName, entityId, pageNo, pageSize, Integer.valueOf(0));
//
//            if (restData != null) {
//                List insList = restData.getRsData();
//                graphBean = EditParser.entityBean2GraphBean(currentEntity, insList, null, graphBean);
//            }
//
//        }

        RestData rsData = new RestData(null);
        return rsData;
    }


    public RestData<GraphBean> getInsGraph(Integer userId, String kgName, Long entityId, Integer direction, Integer pageNo, Integer pageSize)
  {




      GraphBean graphBean = null;
//      graphBean = EditParser.ServiceEntityItem2GraphBean(entityId, direction, (ServiceEntityItem) kgResultItem.getData());
      return new RestData(graphBean);
  }
}
