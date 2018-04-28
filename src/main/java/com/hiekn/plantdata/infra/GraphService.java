package com.hiekn.plantdata.infra;


import com.hiekn.plantdata.bean.EntityProfileBean;
import com.hiekn.plantdata.bean.graph.EntityBean;
import com.hiekn.plantdata.bean.graph.GraphBean;
import com.hiekn.plantdata.bean.graph.KVBean;
import com.hiekn.plantdata.bean.graph.SchemaBean;
import com.hiekn.plantdata.bean.rest.RestData;

import java.util.List;

public interface GraphService {


    /**
     * 设定分析关系
     *
     * @param paramString
     * @return
     */
    public abstract SchemaBean getAllAtts(String paramString);

    /**
     * 设定分析主体
     *
     * @param paramString
     * @return
     */
    public abstract List<KVBean<String, String>> getAllTypes(String paramString);

    public abstract GraphBean kg_graph_full_hasatts(String paramString, Long paramLong, Integer paramInteger1, Integer paramInteger2, List<String> paramList1, List<String> paramList2, boolean paramBoolean,Integer pageSize);

    /**
     * 搜索信息
     *
     * @param paramString1
     * @param paramString2
     * @param paramList
     * @return
     */
    public abstract List<EntityBean> getPrompt(String paramString1, String paramString2, List<Long> paramList);

    /**
     * 读取卡片信息
     *
     * @param paramString
     * @param paramLong
     * @param paramBoolean
     * @param paramList
     * @return
     */
    public abstract EntityProfileBean kg_service_entity(String paramString, Long paramLong, Boolean paramBoolean, List<Long> paramList);

    /**
     * 路径分析
     *
     * @param paramString
     * @param paramLong1
     * @param paramLong2
     * @param paramInteger1
     * @param paramInteger2
     * @param paramBoolean
     * @param paramList1
     * @param paramList2
     * @param paramList3
     * @param paramList4
     * @return
     */
    public abstract GraphBean kg_path(String paramString, Long paramLong1, Long paramLong2, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean, List<String> paramList1, List<Long> paramList2, List<String> paramList3, List<Long> paramList4);


    /**
     * 关联分析
     * @param paramString
     * @param paramList1
     * @param paramInteger1
     * @param paramInteger2
     * @param paramList2
     * @param paramList3
     * @param paramList4
     * @param paramList5
     * @return
     */
    public abstract GraphBean kg_relation_full(String paramString, List<Long> paramList1, Integer paramInteger1, Integer paramInteger2, List<String> paramList2, List<Long> paramList3, List<String> paramList4, List<Long> paramList5);

    /**
     * 时序图探索
     * @param paramString1
     * @param paramLong
     * @param paramInteger1
     * @param paramInteger2
     * @param paramList1
     * @param paramList2
     * @param paramString2
     * @param paramString3
     * @param paramInteger3
     * @param paramBoolean
     * @return
     */
    public abstract GraphBean kg_graph_next_step(String paramString1, Long paramLong, Integer paramInteger1, Integer paramInteger2, List<String> paramList1, List<String> paramList2, String paramString2, String paramString3, Integer paramInteger3, boolean paramBoolean);

    /**
     * 概念图谱
     * @param paramInteger1
     * @param paramString
     * @param paramLong1
     * @param paramLong2
     * @param paramInteger2
     * @param paramInteger3
     * @param paramInteger4
     * @param paramInteger5
     * @return
     */
    public abstract RestData<GraphBean> getGraphByEntityId(Integer paramInteger1, String paramString, Long paramLong1, Long paramLong2, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5);
}
