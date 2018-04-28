package com.hiekn.plantdata.controller;

import com.google.gson.reflect.TypeToken;
import com.hiekn.plantdata.bean.EntityProfileBean;
import com.hiekn.plantdata.bean.graph.EntityBean;
import com.hiekn.plantdata.bean.graph.GraphBean;
import com.hiekn.plantdata.bean.graph.GraphStatBean;
import com.hiekn.plantdata.bean.graph.SchemaBean;
import com.hiekn.plantdata.bean.rest.RestData;
import com.hiekn.plantdata.bean.rest.RestResp;
import com.hiekn.plantdata.infra.GraphService;
import com.hiekn.plantdata.infra.IGraphComputeService;
import com.hiekn.plantdata.util.FormatJson;
import com.hiekn.plantdata.util.JSONUtils;
import com.hiekn.plantdata.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 知识图谱展示
 */
@RestController
@Slf4j
@RequestMapping(value = "sdk")
public class GraphController {


    @Autowired
    private GraphService graphService;

    @Autowired
    private IGraphComputeService iGraphComputeService;

    /**
     * 图探索的初始化
     *
     * @param userId
     * @param kgName   kg_ct
     * @param isTiming 是否是时序图 DefaultValue("false")
     * @param token
     * @param tt
     * @return
     */
    @RequestMapping(value = "/graph/init")
    public Response initGraph(@QueryParam("userId") Integer userId, @FormParam("kgName") String kgName, @FormParam("isTiming") Boolean isTiming, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
//        GraphBean graphBean = this.generalSSEService.initGraph(kgName, isTiming);
        GraphBean graphBean = null;
        RestResp<GraphBean> rs = new RestResp(graphBean, tt);
        return Response.ok().entity(rs).build();
    }


    /**
     * 获取普通图探索数据
     *
     * @param userId
     * @param kgName
     * @param id
     * @param distance
     * @param direction
     * @param isRelationMerge
     * @param allowAtts
     * @param allowTypes
     * @param token
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/graph", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<GraphBean> graph(@FormParam("pageSize")Integer pageSize,HttpSession session, @QueryParam("userId") Integer userId, @FormParam("kgName") String kgName, @FormParam("id") Long id, @FormParam("distance") @DefaultValue("1") Integer distance, @FormParam("direction") @DefaultValue("0") Integer direction, @FormParam("isRelationMerge") @DefaultValue("false") Boolean isRelationMerge, @FormParam("allowAtts") String allowAtts, @FormParam("allowTypes") String allowTypes, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
        List<String> allowAttList = (List) JSONUtils.fromJson(allowAtts, (new TypeToken<List<String>>() {
        }).getType());
        List<String> allowTypeList = (List) JSONUtils.fromJson(allowTypes, (new TypeToken<List<String>>() {
        }).getType());
        if(allowTypeList.size()>0){
            allowTypeList.add("level3");
            allowTypeList.add("实体");
        }

        String usrId = (String) session.getAttribute("userId");
        GraphBean graphBean = this.graphService.kg_graph_full_hasatts(usrId, id, distance, direction, allowAttList, allowTypeList, isRelationMerge.booleanValue(),pageSize);
        RestResp<GraphBean> rs = new RestResp(graphBean, tt);
        return rs;
    }

    /**
     * 获取当前图实体类型及属性类型的schema
     *
     * @param kgName
     * @param token
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/schema", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<SchemaBean> schema(HttpSession session, @FormParam("kgName") String kgName, @QueryParam("token") String token, @QueryParam("tt") Long tt) {

        String userId = (String) session.getAttribute("userId");
        SchemaBean sb = null;
        sb = this.graphService.getAllAtts(userId);
        sb.setTypes(this.graphService.getAllTypes(userId));
        RestResp<SchemaBean> rs = new RestResp(sb, tt);
        return rs;
    }

    /**
     * 实体的下拉提示
     *
     * @param kgName
     * @param kw
     * @param pageSize
     * @param allowTypes
     * @param isCaseSensitive
     * @param token
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/prompt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<EntityBean> prompt(HttpSession session, @FormParam("kgName") String kgName, @FormParam("kw") String kw, @FormParam("pageSize")Integer pageSize, @FormParam("allowTypes") String allowTypes, @FormParam("isCaseSensitive") @DefaultValue("false") Boolean isCaseSensitive, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
         List<Long> allowTypesList = (List) JSONUtils.fromJson(allowTypes, (new TypeToken<List<Long>>() {}).getType());
        String userId = (String) session.getAttribute("userId");
        List<EntityBean> rsList = this.graphService.getPrompt(userId, kw, allowTypesList);

        RestResp<EntityBean> rs = new RestResp(rsList, tt);
        return rs;
    }

    /**
     * 读取卡片信息
     *
     * @param userId
     * @param kgName
     * @param id
     * @param isRelationAtts
     * @param allowAtts
     * @param token
     * @param tt
     * @return
     */
    @RequestMapping(value = "/infobox", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<EntityProfileBean> infobox(@QueryParam("userId") Integer userId,@FormParam("kgName") String kgName, @FormParam("id") Long id, @FormParam("isRelationAtts") @DefaultValue("false") Boolean isRelationAtts, @FormParam("allowAtts") String allowAtts, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
        List<Long> allowAttsList = (List)JSONUtils.fromJson(allowAtts, (new TypeToken<List<Long>>() {
        }).getType());
        EntityProfileBean entityProfileBean = null;
        entityProfileBean = this.graphService.kg_service_entity(kgName, id, isRelationAtts, allowAttsList);
        RestResp<EntityProfileBean> rs = new RestResp(entityProfileBean, tt);
        return rs;
    }


    /**
     * 路径分析
     *
     * @param userId
     * @param kgName
     * @param start
     * @param end
     * @param distance
     * @param direction
     * @param isShortest
     * @param allowAtts
     * @param allowTypes
     * @param connectsCompute  为true就计算
     * @param statsCompute  为true且statConfig配置了才计算
     * @param statsConfig  [{'key':'VC invest','type':13,'atts':[1301]}]
     * @param token
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/path", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<GraphBean> path(@QueryParam("userId") Integer userId, @FormParam("kgName") String kgName,  @FormParam("start") Long start, @FormParam("end") Long end, @FormParam("distance") @DefaultValue("1") Integer distance, @FormParam("direction") @DefaultValue("0") Integer direction, @FormParam("isShortest") @DefaultValue("true") Boolean isShortest, @FormParam("allowAtts") String allowAtts, @FormParam("allowTypes") String allowTypes, @FormParam("connectsCompute") @DefaultValue("false") Boolean connectsCompute,  @FormParam("statsCompute") @DefaultValue("false") Boolean statsCompute, @FormParam("statsConfig") String statsConfig, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
        List<GraphStatBean> statsConfigList = null;
        List<String> allowAttList = (List)JSONUtils.fromJson(allowAtts, (new TypeToken<List<String>>() {
        }).getType());
        List<String> allowTypeList = (List)JSONUtils.fromJson(allowTypes, (new TypeToken<List<String>>() {
        }).getType());
        if (statsCompute.booleanValue()) {
            statsConfigList = (List)JSONUtils.fromJson(statsConfig, (new TypeToken<List<GraphStatBean>>() {
            }).getType());
        }

        GraphBean graphBean = this.graphService.kg_path(kgName, start, end, distance, direction, isShortest.booleanValue(), allowAttList, (List)null, allowTypeList, (List)null);
        if (connectsCompute.booleanValue()) {
            List<Long> ids = Arrays.asList(start, end);
            this.iGraphComputeService.computeConnects(graphBean, ids, distance);
        }

        if (statsCompute.booleanValue()) {
            this.iGraphComputeService.computeStats(graphBean, statsConfigList);
        }

        RestResp<GraphBean> rs = new RestResp(graphBean, tt);
        return rs;
    }

    /**
     * 关联分析
     * @param userId
     * @param kgName
     * @param ids
     * @param distance
     * @param direction
     * @param allowAtts
     * @param allowTypes
     * @param connectsCompute 为true就计算
     * @param statsCompute  为true且statConfig配置了才计算
     * @param statsConfig  [{"key":"VC invest","type":"应用视图","atts":["使用指标"]}]
     * @param token
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/relation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<GraphBean> relation(@QueryParam("userId") Integer userId, @FormParam("kgName") String kgName,  @FormParam("ids") String ids, @FormParam("distance") @DefaultValue("1") Integer distance, @FormParam("direction") @DefaultValue("0") Integer direction, @FormParam("allowAtts") String allowAtts, @FormParam("allowTypes") String allowTypes, @FormParam("connectsCompute") @DefaultValue("false") Boolean connectsCompute, @FormParam("statsCompute") @DefaultValue("false") Boolean statsCompute,  @FormParam("statsConfig") String statsConfig, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
        List<GraphStatBean> statsConfigList = null;
        List<Long> idList = (List)JSONUtils.fromJson(ids, (new TypeToken<List<Long>>() {
        }).getType());
        List<String> allowAttList = (List)JSONUtils.fromJson(allowAtts, (new TypeToken<List<String>>() {
        }).getType());
        List<String> allowTypeList = (List)JSONUtils.fromJson(allowTypes, (new TypeToken<List<String>>() {
        }).getType());
        if (statsCompute.booleanValue()) {
            statsConfigList = (List)JSONUtils.fromJson(statsConfig, (new TypeToken<List<GraphStatBean>>() {
            }).getType());
        }

        GraphBean graphBean = this.graphService.kg_relation_full(kgName, idList, distance, direction, allowAttList, (List)null, allowTypeList, (List)null);
        if (connectsCompute.booleanValue()) {
            this.iGraphComputeService.computeConnects(graphBean, idList, distance);
        }

        if (statsCompute.booleanValue()) {
            this.iGraphComputeService.computeStats(graphBean, statsConfigList);
        }

        RestResp<GraphBean> rs = new RestResp(graphBean, tt);
        return rs;
    }

    /**
     * 时序图探索
     * @param userId
     * @param kgName
     * @param id
     * @param direction
     * @param allowAtts
     * @param allowTypes
     * @param fromTime
     * @param toTime
     * @param sort
     * @param pageSize
     * @param isRelationMerge
     * @param token
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/graph/timing", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<GraphBean> graphTiming(@QueryParam("userId") Integer userId,  @FormParam("kgName") String kgName, @FormParam("id") Long id, @FormParam("direction") @DefaultValue("0") Integer direction, @FormParam("allowAtts") String allowAtts, @FormParam("allowTypes") String allowTypes, @FormParam("fromTime") String fromTime, @FormParam("toTime") String toTime,  @FormParam("sort") @DefaultValue("-1") Integer sort, @FormParam("pageSize") @DefaultValue("30") Integer pageSize, @FormParam("isRelationMerge") @DefaultValue("false") Boolean isRelationMerge, @QueryParam("token") String token, @QueryParam("tt") Long tt) {
        List<String> allowAttList = (List)JSONUtils.fromJson(allowAtts, (new TypeToken<List<String>>() {
        }).getType());
        List<String> allowTypeList = (List)JSONUtils.fromJson(allowTypes, (new TypeToken<List<String>>() {
        }).getType());
        GraphBean graphBean = this.graphService.kg_graph_next_step(kgName, id, direction, sort, allowAttList, allowTypeList, fromTime, toTime, pageSize, isRelationMerge.booleanValue());
        RestResp<GraphBean> rs = new RestResp(graphBean, tt);
        return rs;
    }


    /**
     * 根据实体id获取graph
     * @param userId
     * @param token
     * @param kgName
     * @param entityId
     * @param type
     * @param direction
     * @param subTreeLevel
     * @param pageNo
     * @param pageSize
     * @param tt
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/graph/knowlegde", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<GraphBean> getGraph(@QueryParam("userId") Integer userId, @QueryParam("token") String token, @QueryParam("kgName") String kgName, @DefaultValue("0") @QueryParam("entityId") Long entityId,  @QueryParam("type") Long type, @DefaultValue("0") @QueryParam("direction") Integer direction, @DefaultValue("1") @QueryParam("subTreeLevel") Integer subTreeLevel, @DefaultValue("1") @QueryParam("pageNo") Integer pageNo, @DefaultValue("10") @QueryParam("pageSize") Integer pageSize, @QueryParam("tt") Long tt) {
        RestData<GraphBean> rsData = null;
        rsData = this.graphService.getGraphByEntityId(userId, kgName, entityId, type, direction, subTreeLevel, pageNo, pageSize);
        RestResp<GraphBean> rs = new RestResp(rsData, tt);
        return rs;
    }
}
