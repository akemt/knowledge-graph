package com.beyond.algm.algmdataboot.impl;

import com.beyond.algm.algmdataboot.AlgmDataBootApplication;
import com.beyond.algm.algmdataboot.infra.DataSetService;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ：
 * @Description:
 * @date ：10:16 2017/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmDataBootApplication.class)
public class DataSetServiceTests {
    @Autowired
    private DataSetService dataSetService;


    //我的数据集tree
    @Test
    public void getDataSetTest() throws Exception {
        dataSetService.getDataSet("37bf2269ee4845da8e86861bbde2438a");
    }

    //我的数据集tree
    @Test
    public void getDataTest() throws Exception {
        dataSetService.getData("37bf2269ee4845da8e86861bbde2438a");
    }

    //添加数据集
    @Test
    public void addDataSetTest() throws Exception {
        AlgDataSet dataSet = new AlgDataSet();
        dataSet.setDataSetName("数据集XaaXX");
        dataSet.setUsrSn("dddddddddddddddddd");
        dataSetService.addDataSet(dataSet);
    }

    //删除数据
    @Test
    public void deleteDataTest() throws Exception {
        dataSetService.deleteData("aa");
    }

    //删除当前数据集
    @Test
    public void deleteDataSetTest() throws Exception {
        dataSetService.deleteDataSet("999");
    }

    //点击数据集关联查询数据
    @Test
    public void queryAlgDatabySetTest() throws Exception {
        dataSetService.queryAlgDatabySet("777");
    }

    //新增数据
    @Test
    public void addDataTest() throws Exception {
        AlgData algData = new AlgData();
        AlgUser algUser = new AlgUser();
        algData.setDataName("测试1");
        algData.setDataEnName("test1");
        algData.setDataSetSn("777");
        algUser.setUsrSn("55asasa");
        algUser.setUsrCode("ldw");
        dataSetService.addData(algData,algUser);
    }
}
