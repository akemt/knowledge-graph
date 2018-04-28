package com.hiekn.plantdata.infra;

import com.hiekn.plantdata.bean.graph.GraphBean;
import com.hiekn.plantdata.bean.graph.GraphStatBean;

import java.util.List;

public interface IGraphComputeService {

    public GraphBean computeStats(GraphBean paramGraphBean, List<GraphStatBean> paramList);

    public GraphBean computeConnects(GraphBean paramGraphBean, List<Long> paramList, Integer paramInteger);
}
