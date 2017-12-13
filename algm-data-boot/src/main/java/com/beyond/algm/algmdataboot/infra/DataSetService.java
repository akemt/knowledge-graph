package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDataDownLoadVo;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ZhangJiayue
 * @Description 数据管理数据模块service接口声明
 * @data 2017-10-19 10:29:44
 */
public interface DataSetService {
    /**
     * @author ：lindewei
     * @Description: 我的数据集tree
     */
    PageInfo<AlgDataSet> getDataSet(String usrSn, Pageable pageable) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 我的数据List
     */
    PageInfo<AlgData> getData(String usrSn, Pageable pageable) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 添加数据集
     */
    Result addDataSet(AlgDataSet dataSet) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 删除数据
     */
    Result deleteData(String dataSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 删除当前数据集
     */
    Result deleteDataSet(String dataSetSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 点击数据集关联查询数据
     */
    Result queryAlgDatabySet(String dataSetSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 新增数据
     */
    Result addData(AlgData algData,AlgUser algUser)throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 数据商城
     */
    Page<AlgDataDownLoadVo> algDataMall(String dataContent, Integer numPage, Integer numRows) throws AlgException;

    /**
     * @author ：ZhangJiayue
     * @Description: 修改数据
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:06
     */
    //Result modifyData(AlgData algData) throws Exception;

    /**
     * @author ：zhangchuanzhi
     * @Description:查找数据集串号
     * @param： String usrSn，dataSetName
     * @date ： 2017-12-06 21:54:06
     */
    List<AlgDataSet>  dataSetId(String usrSn,String dataSetName )throws AlgException;

}
