package com.hiekn.plantdata.infra.impl;

import com.hiekn.plantdata.Entity.*;
import com.hiekn.plantdata.common.TreeBuilder;
import com.hiekn.plantdata.common.TreeNode;
import com.hiekn.plantdata.dao.AttributesRepository;
import com.hiekn.plantdata.dao.EntitysRepository;
import com.hiekn.plantdata.infra.EntityService;
import com.hiekn.plantdata.infra.Neo4jDriverService;
import com.hiekn.plantdata.mapper.EntityMapper;
import com.hiekn.plantdata.util.ArrayUtils;
import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 实体业务逻辑层
 */
@Service
@Transactional
public class EntityServiceImpl implements EntityService {


    @Autowired
    private EntitysRepository entitysRepository;

    @Autowired
    private Neo4jDriverService neo4jDriverService;

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private EntityService entityService;

    @Override
    public List<Map<String, Object>> getEntitysList(String userId,String searchStr, Integer iPage) {

        //查询当前用户下的所有节点
        List<Long>  longList = entityService.getUsrGraphNodesList(userId);

        String kws = "." + "*" + searchStr + "." + "*";
        List<ResultheadData> infoList = entitysRepository.findEntitysList(ArrayUtils.listDataToLong(longList),kws,iPage);
        List<Map<String, Object>> list = this.getResultheadDataList(infoList);
        return list;
    }

    @Override
    public boolean updateEntitysInfo(String userId, long id, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        String strId = "";
        if(jsonObject.get("id") !=null){
            strId = jsonObject.getString("id");
        } else{
            strId = String.valueOf(id);
        }
        String mId = "";
        if(jsonObject.get("mid") !=null){
            mId = jsonObject.getString("mid");
        }

        String name = jsonObject.getString("name");

        //id ！=null,实体功能新建属性
        if(strId !=null){

            //获取当前用户下，此实体类下的所有属性
            List<Map<String,String>>  mapListAttr = entityMapper.getUsrGraphEntityAttrList(userId,String.valueOf(id));
            List<Long> longListAttr = ArrayUtils.getMapListToListLong(mapListAttr);
            long[] longsAttr = ArrayUtils.listDataToLong(longListAttr);
            //获取当前用户下，此实体类下的所有关系
            List<Map<String,String>>  mapListRel = entityMapper.getUsrGraphRelationListByUsrIDAndEntID(userId,"01",String.valueOf(id));
            List<Long> longListRel = ArrayUtils.getMapListToListLong(mapListRel);
            long[] longsRel = ArrayUtils.listDataToLong(longListRel);

            //关系型数据库-删除该实体类 以下的节点和关系
            entityMapper.deleteEntAttrInfoByEntID(userId, strId);

            entityMapper.deleteEntRelInfoByEntID(strId,"01",userId);
            //给节点加parentId 赋值
//            String strdeleteNode = "MATCH (m)-[r]->(n) where id(m) = "+strId+" DETACH DELETE r,n";
//            neo4jDriverService.saveLabels(strdeleteNode);
            entitysRepository.deleteEntitysTypeRelation(Long.valueOf(strId),longsAttr,longsRel);
        }
        String jaStrDefine = jsonObject.getString("define");
        //将jsonArray字符串转化为JSONArray
        JSONArray jsonArrayDefine = JSONArray.fromObject(jaStrDefine);

        String relationName = "实体类_包含_属性";
        this.treeMenuList(userId,jsonArrayDefine,Long.valueOf(strId),Long.valueOf(strId),name,relationName);
        return true;
    }

    /**
     * 递归----获取某个父节点下面的所有子节点
     *
     * @param jsonArrayDefine
     */
    public void treeMenuList(String userId, JSONArray jsonArrayDefine,long nodeID,long entID,String strRelationName,String relationName) {
        if (jsonArrayDefine.size() > 0) {
            for (int i = 0; i < jsonArrayDefine.size(); i++) {

                String jAttrName = jsonArrayDefine.getJSONObject(i).getString("name");

                String jAttrValue = jsonArrayDefine.getJSONObject(i).getString("value");

                System.out.println("Name: " + jAttrName + ",Value: " + JSONArray.fromObject(jAttrValue).toString());
                JSONArray jValue = JSONArray.fromObject(jAttrValue);
                String value = "";
                for (int k =0;k<jValue.size();k++) {
                    if(!"".equals(jValue.getString(k))){
                        value += jValue.getString(k)+",";
                    }
                }
                String strGlobalName = userId+":属性##实体类#"+strRelationName+"$"+jAttrName;
                //建立节点
                String strAttrLabels = "MERGE (a:属性:level3 {global_name:'"+strGlobalName+"',name: '"+jAttrName+"',value:'"+value+"',parentId: "+nodeID+"})   RETURN id(a)  as id";

                long ids = neo4jDriverService.saveLabelsReturnID(strAttrLabels);
                //把属性---保存到关系型数据库
                UsrEntAttrib entAttribType= new UsrEntAttrib();
                entAttribType.setAtbSID(String.valueOf(ids));
                entAttribType.setAtbName(jAttrName);
                entAttribType.setDtpSID("");
                entAttribType.setObjSID("");
                entAttribType.setObjType("");
                entAttribType.setUntName("");
                entAttribType.setUsrSID(userId);
                entAttribType.setEntSID(String.valueOf(entID));
                entityMapper.saveEntAttrInfo(entAttribType);


                //MATCH (n)DETACH DELETE n
                //建立关系
                String strAttrRelationships = "match (m) where id(m)="+nodeID+" match (n) where id(n)="+ids+" merge(m)-[r:"+relationName+" {name:'"+relationName+"',from:id(m),to:id(n)}]->(n)  RETURN id(r)  as id";
                long idRel = neo4jDriverService.saveLabelsReturnID(strAttrRelationships);

                //把关系---保存到关系型数据库
                UsrEntRel entRelType= new UsrEntRel();
                entRelType.setRtpSID(String.valueOf(idRel));
                entRelType.setRtpName(relationName);
                entRelType.setUsrSID(userId);
                entRelType.setEntSID(String.valueOf(entID));
                entRelType.setType("01");
                entityMapper.saveEntRelInfo(entRelType);
                //给关系赋值
//                String strRelationshipsValue = "match (s)-[r:"+relationName+"]->(e) set r.from = id(s),r.to = id(e)";
//                neo4jDriverService.saveLabels(strRelationshipsValue);
                if(jsonArrayDefine.getJSONObject(i).get("children").equals("null")){
                    System.out.println("Children is null ");
                }
                else{
                    String jAttrChildren = jsonArrayDefine.getJSONObject(i).getString("children");
                    System.out.println("Children: " + jAttrChildren);
                    JSONArray jAttrChildrenObject = JSONArray.fromObject(jAttrChildren);
                    if (jAttrChildrenObject.size() > 0) {
                        String relationNameAndSx = "属性_包含_属性";
                        this.treeMenuList(userId,jAttrChildrenObject,ids,entID,strRelationName,relationNameAndSx);
                    }else{
                        break;
                    }
                }
            }
            //给节点加parentId 赋值
//            String strRelationshipsValue = "MATCH (m)-[r]->(n) set n.parentId = id(m)";
//            neo4jDriverService.saveLabels(strRelationshipsValue);
        }
    }

    @Override
    public Map<String, Object> getEntitysInfo(long id,String usrID) {

        List<ResultheadData> infoList = entitysRepository.findById(id);

        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = this.getResultheadDataList(infoList);
        map.put("id", list.get(0).get("id"));
        map.put("name", list.get(0).get("name"));
        long parId = (Integer)list.get(0).get("parentId");

        ResultheadData parentNodeinfo = entitysRepository.findEntitysClassInfo(parId);
        Map<String, Object> maplabels = getResultheadData(parentNodeinfo);
        if(maplabels !=null && maplabels.size()>0){
            String labelsName = (String) maplabels.get("name");
            map.put("mid", maplabels.get("id"));
            map.put("model", labelsName);
        }else{
            map.put("mid", 00000);
            map.put("model", "无实体类");
        }

        List<List<TreeNode>> defineList = new ArrayList<>();
        //根据当前用户，查询起下的属性节点
        List<Map<String,String>>  mapList = entityMapper.getUsrGraphEntityAttrList(usrID,String.valueOf(id));
        List<Long> longList = ArrayUtils.getMapListToListLong(mapList);

        List<ResultheadData> treeList = entitysRepository.findTreeDataById(id,ArrayUtils.listDataToLong(longList));
        Map<String, Object> childrenMap = new HashMap<>();
        List<TreeNode> listNode = new ArrayList<TreeNode>();
        Integer newParentId = 9999999;
        for (int i = 0; i < treeList.size(); i++) {
            ResultheadData resultheadData1 = treeList.get(i);
            for (NodeModel nodesEntity : resultheadData1.getNodes()) {
                if (nodesEntity.getPropertyList().size() > 0) {
                    Object name = nodesEntity.property("name");
                    Object value = nodesEntity.property("value");
                    List<String> listValue = null;
                    if(value !=null){
                        listValue = new ArrayList<>();
                        String str[] = value.toString().split(",");
                        for(String st:str){
                            listValue.add(st);
                        }

                    }
//                    String strValue[]  = ArrayUtils.listDataToString(listValue);
                    Long ids = nodesEntity.getId();

                    Integer parentId = (Integer)nodesEntity.property("parentId");
                    if(parentId == null || id == ids){
                        parentId = 9999999;
                        newParentId = 9999999;
                    }
                    TreeNode treeNode = new TreeNode(ids, (String)name, parentId.longValue() ,listValue);
                    listNode.add(treeNode);
                }
            }
        }
        List<TreeNode> trees_ = TreeBuilder.buildByRecursive(ArrayUtils.removeDuplicateTreeNode(listNode),newParentId);

        if(trees_ !=null && trees_.size()>0){
            TreeNode treeNode = trees_.get(0);
            map.put("id", treeNode.getId());
            map.put("name", treeNode.getName());
            map.put("define", treeNode.getChildren());
        }else{
            map.put("define", defineList);
        }
        return map;
    }

    @Override
    public Map<String, Object> addEntitysRelation(String userId, long id, String json) {

//        List<ResultheadData> infoList = entitysRepository.findById(id);
//
//        List<Map<String, Object>> list = this.getResultheadDataList(infoList);
//        String fName = (String) list.get(0).get("name");

        JSONObject jsonObject = JSONObject.fromObject(json);

        String rid = jsonObject.getString("rid");

        String relationName = jsonObject.getString("relation");

        String eid = jsonObject.getString("eid");

        String ename = jsonObject.getString("ename");

        //建立关系
        String strAttrRelationships = "match (m) where id(m)="+rid+" match (n) where id(n)="+eid+"  merge(m)-[r:"+relationName+" {name:'"+relationName+"'}]->(n)   RETURN id(r)  as id";
        long ids = neo4jDriverService.saveLabelsReturnID(strAttrRelationships);
        //给关系赋值
        String strRelationshipsValue = "match (s)-[r:"+relationName+"]->(e) set r.from = id(s),r.to = id(e)";
        neo4jDriverService.saveLabels(strRelationshipsValue);

        Map<String, Object> map = new HashMap<>();
        map.put("rid",ids);


        //把关系---保存到关系型数据库
        UsrEntRel entRelType= new UsrEntRel();
        entRelType.setRtpSID(String.valueOf(ids));
        entRelType.setRtpName(relationName);
        entRelType.setUsrSID(userId);
        entRelType.setEntSID(String.valueOf(id));
        entRelType.setType("02");
        entityMapper.saveEntRelInfo(entRelType);
        return map;
    }

    @Override
    public List<Map<String, Object>> getEntitysRelation(long id, String userID) {

        List<ResultheadData> infoList = entitysRepository.getEntitysRelation(id);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (infoList.size() > 0) {
            for (int i = 0; i < infoList.size(); i++) {
                Map<String, Object> relationMap = new HashMap<>();
                ResultheadData resultheadData = infoList.get(i);
                //实体List
                List<NodeModel> nodesModelList = resultheadData.getNodes();
                for (NodeModel nodesEntity : nodesModelList) {
                    String name = "";
                    if (nodesEntity.getPropertyList().size() > 0) {
                        name = (String) nodesEntity.property("name");
                    }
                    long eID = Long.valueOf(String.valueOf(nodesEntity.getId()));
                    if(eID == id){
                    }else{
                        relationMap.put("eid",eID);
                        relationMap.put("ename",name);
                    }
                }

                //关系List
                List<RelationshipModel> relationshipsModelList = resultheadData.getRelationships();
                for (RelationshipModel relationshipModel : relationshipsModelList) {
                    String relation = (String) ArrayUtils.property(relationshipModel.getPropertyList(), "name");
                    relationMap.put("rid",String.valueOf(relationshipModel.getId()));
                    relationMap.put("relation",relation);
                }
                mapList.add(relationMap);
            }

        }
        return mapList;
    }

    @Override
    public boolean deleteEntitysRelation(String userId, long id) {

        entitysRepository.deleteRelationById(id);

        entityMapper.deleteEntRelInfoByID(String.valueOf(id));

        return true;
    }

    @Override
    public Map<String, Object> saveEntitysInfo(String userId, String eClassname,long mID) {

        //验证当前用户下，是否存在该名称
        List<Map<String,String>> mapList = entityMapper.getUsrGraphEntityListByName(userId,eClassname);

        //查看实体类label名是否存在
//        List<ResultheadData> resultheadDataList = entitysRepository.findEntitysList(eClassname);
        Map<String, Object> map = new HashMap<>();
        if(mapList!=null && mapList.size()>0){//该名称已经存在，不可以新建
            map.put("id",null);
        }else{
            //通过ID 查询实体类 level2 labels名称
            ResultheadData resultheadData = entitysRepository.findEntitysClassInfo(mID);
            Map<String, Object> maplabels = getResultheadData(resultheadData);
            String labelsName = (String) maplabels.get("name");
            //保存实体类
            String jAttrNamesss= userId+":"+labelsName+"##"+eClassname;
            String strAttr = "MERGE (a:level3:实体:`"+labelsName+"` {global_name:'"+jAttrNamesss+"',name: '"+eClassname+"',parentId:"+mID+"})  RETURN id(a)  as id";

            long ids = neo4jDriverService.saveLabelsReturnID(strAttr);
            map.put("id",ids);
            //保存实体类到关系数据库
            UsrEntity entityType= new UsrEntity();
            entityType.setEntSID(String.valueOf(ids));
            entityType.setEntName(eClassname);
            entityType.setUsrSID(userId);
            entityType.setEntTmpl("");
            entityMapper.saveEntityInfo(entityType);
        }
        return map;
    }

    /**
     * 公共方法-遍历ResultheadData数据-节点返回相应MAP
     * @param resultheadData
     * @return
     */
    public Map<String, Object> getResultheadData(ResultheadData resultheadData){
        Map<String, Object> map = new HashMap<>();
        for (NodeModel nodesEntity : resultheadData.getNodes()) {
            if (nodesEntity.getPropertyList().size() > 0) {
                map = new HashMap<>();
                Object name = nodesEntity.property("name");
                map.put("name", (String) name);
                Long ids = nodesEntity.getId();
                map.put("id", ids);
            }
        }
        return map;
    }

    /**
     * 公共方法-遍历ResultheadData数据-节点返回相应list
     * @param infoList
     * @return
     */
    public List<Map<String, Object>> getResultheadDataList(List<ResultheadData> infoList){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < infoList.size(); i++) {

            ResultheadData resultheadData1 = infoList.get(i);
            for (NodeModel nodesEntity : resultheadData1.getNodes()) {
                if (nodesEntity.getPropertyList().size() > 0) {
                    map = new HashMap<>();
                    Object name = nodesEntity.property("name");
                    map.put("name", (String) name);
                    Long ids = nodesEntity.getId();
                    map.put("id", ids);
                    Object parentId = nodesEntity.property("parentId");
                    map.put("parentId", parentId);
                    list.add(map);
                }
            }
        }
        return list;
    }


    /**
     * 公共方法-遍历ResultheadData数据-关系
     * @param infoList
     * @return
     */
    public List<Map<String, Object>> getResultheadDataRelationList(List<ResultheadData> infoList,Integer fromID){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < infoList.size(); i++) {

            ResultheadData resultheadData1 = infoList.get(i);
            List<RelationshipModel> relationshipsModelList = resultheadData1.getRelationships();
            for (RelationshipModel relationshipModel : relationshipsModelList) {
                Integer from = (Integer) ArrayUtils.property(relationshipModel.getPropertyList(), "from");
                Integer to = (Integer) ArrayUtils.property(relationshipModel.getPropertyList(), "to");
                map.put("from",from);
                map.put("to",to);
                list.add(map);
            }
        }
        return list;
    }



    //1.根据当前用户，查询该用户下的实体关系
    public List<Long>  getUsrGraphRelationList(String usrID){

        List<Map<String,String>>  mapList = entityMapper.getUsrGraphRelationList(usrID,"02");
        List<Long> longList = ArrayUtils.getMapListToListLong(mapList);
        return longList;
    }

    //2.根据当前用户，查询该用户下的实体
    public List<Long>  getUsrGraphNodesList(String usrID){

        List<Map<String,String>>  mapList = entityMapper.getUsrGraphNodesList(usrID);
        List<Long> longList = ArrayUtils.getMapListToListLong(mapList);
        return longList;
    }
}
