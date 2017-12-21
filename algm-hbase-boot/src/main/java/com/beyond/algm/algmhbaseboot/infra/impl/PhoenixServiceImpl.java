package com.beyond.algm.algmhbaseboot.infra.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmhbaseboot.infra.PhoenixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 * Phoenix Service
 * create by Jr.Elephant on 2017/12/18
 * */
@Slf4j
@Service
public class PhoenixServiceImpl implements PhoenixService {

    @Autowired
    private Connection connection;

    private Statement statement;

    @Override
    public void initStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * close all connection
     * */
    @Override
    public void closeAll() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * insert table by Json
     * */
    @Override
    public void insertByJson(JSONObject obj) throws SQLException {
        //获取statement
        if (statement == null) {
            initStatement();
        }

        // 各种串号
        String alg_sn = "-1";
        String umc_sn = "-1";
        String mod_sn = "-1";
        String ver_sn = "-1";
        String call_usr_sn = "-1";
        String owner_usr_sn = "-1";
        // 各种使用信息
        long duration = 0;
        float call_pay_amount = 0;
        int is_charging = 0;
        long start_time = 0;
        long end_time = 0;

        // 将json解析赋值给变量
        Set<String> keySet = obj.keySet();
        for (String key:keySet) {
            switch(key)
            {
                // 各种串号
                case "algSn":
                    alg_sn = obj.getString(key);
                    break;
                case "umcSn":
                    umc_sn = obj.getString(key);
                    break;
                case "modSn":
                    mod_sn = obj.getString(key);
                    break;
                case "verSn":
                    ver_sn = obj.getString(key);
                    break;
                case "callUsrSn":
                    call_usr_sn = obj.getString(key);
                    break;
                case "ownerUsrSn":
                    owner_usr_sn = obj.getString(key);
                    break;
                // 各种使用信息
                case "duration":
                    duration = Long.parseLong(obj.getString(key));
                    break;
                case "callPayAmount":
                    call_pay_amount = Float.parseFloat(obj.getString(key));
                    break;
                case "isCharging":
                    is_charging = Integer.parseInt(obj.getString(key));
                    break;
                case "startTime":
                    start_time = Long.parseLong(obj.getString(key));
                    break;
                case "endTime":
                    end_time = Long.parseLong(obj.getString(key));
                    break;
            }
        }
        // sql语句
        String sql = "UPSERT INTO ALG_R_USER_MODULE_CALL_TRANS("
                + "ALG_SN,UMC_SN,MOD_SN,VER_SN,CALL_USR_SN,OWNER_USR_SN,DURATION,CALL_PAY_AMOUNT,IS_CHARGING,START_TIME,END_TIME"
                + ") VALUES ("
                + "'" + alg_sn + "',"
                + "'" + umc_sn + "',"
                + "'" + mod_sn + "',"
                + "'" + ver_sn + "',"
                + "'" + call_usr_sn + "',"
                + "'" + owner_usr_sn + "',"
                + duration + ","
                + call_pay_amount + ","
                + is_charging + ","
                + start_time + ","
                + end_time + ")"
                ;
        // 提交sql语句
        statement.executeUpdate(sql);
        connection.commit();
    }
    /**
     * query
     * */
    @Override
    public void queryAndSendMySQL() {
        // 获取系统时间(缓冲时间2分钟)
        long endTimeStamp = System.currentTimeMillis()-120000;
        // 5分钟
        long startTimeStamp = endTimeStamp-300000;
        // 指定sql语句
        String sql = "SELECT " +
                "TA.UMC_SN as UMC_SN, " +
                "TA.MOD_SN as MOD_SN, " +
                "TA.VER_SN as VER_SN, " +
                "TA.CALL_USR_SN as CALL_USR_SN, " +
                "TA.OWNER_USR_SN as OWNER_USR_SN, " +
                "TA.SUM_DURATION as DURATION, " +
                "TA.SUM_CALL_PAY_AMOUNT as CALL_PAY_AMOUNT, " +
                "TA.SUM_ALL_CALL_AMOUNT as ALL_CALL_AMOUNT, " +
                "TB.SUM_BILLED_CALL_CNT as BILLED_CALL_CNT " +
                "FROM ( " +
                "  SELECT " +
                "  A.UMC_SN, " +
                "  A.MOD_SN, " +
                "  A.VER_SN, " +
                "  A.CALL_USR_SN, " +
                "  A.OWNER_USR_SN, " +
                "  B.SUM_DURATION, " +
                "  ROUND(B.SUM_CALL_PAY_AMOUNT,2) AS SUM_CALL_PAY_AMOUNT, " +
                "  B.SUM_ALL_CALL_AMOUNT " +
                "  FROM ( " +
                "    SELECT " +
                "    DISTINCT UMC_SN,MOD_SN,VER_SN,CALL_USR_SN,OWNER_USR_SN " +
                "    FROM ALG_R_USER_MODULE_CALL_TRANS " +
                "    WHERE TIMEST > " + startTimeStamp +
                "    AND TIMEST < " + endTimeStamp +
                "  ) A LEFT JOIN ( " +
                "    SELECT " +
                "    MOD_SN, " +
                "    VER_SN, " +
                "    CALL_USR_SN, " +
                "    OWNER_USR_SN, " +
                "    SUM(DURATION) AS SUM_DURATION, " +
                "    SUM(CALL_PAY_AMOUNT) AS SUM_CALL_PAY_AMOUNT, " +
                "    COUNT(1) AS SUM_ALL_CALL_AMOUNT " +
                "    FROM ALG_R_USER_MODULE_CALL_TRANS " +
                "    WHERE TIMEST > " + startTimeStamp +
                "    AND TIMEST < " + endTimeStamp +
                "    GROUP BY " +
                "    CALL_USR_SN, " +
                "    MOD_SN, " +
                "    VER_SN, " +
                "    OWNER_USR_SN " +
                "  ) B ON A.OWNER_USR_SN = B.OWNER_USR_SN " +
                "  AND A.MOD_SN = B.MOD_SN " +
                "  AND A.VER_SN = B.VER_SN " +
                "  AND A.CALL_USR_SN = B.CALL_USR_SN " +
                ") TA LEFT JOIN ( " +
                "  SELECT  " +
                "    MOD_SN, " +
                "    VER_SN, " +
                "    CALL_USR_SN, " +
                "    OWNER_USR_SN, " +
                "    COUNT(1) AS SUM_BILLED_CALL_CNT " +
                "  FROM ALG_R_USER_MODULE_CALL_TRANS " +
                "  WHERE IS_CHARGING = 1 " +
                "  AND TIMEST > " + startTimeStamp +
                "  AND TIMEST < " + endTimeStamp +
                "  GROUP BY " +
                "    CALL_USR_SN, " +
                "    MOD_SN, " +
                "    VER_SN, " +
                "    OWNER_USR_SN " +
                ") TB ON TA.OWNER_USR_SN = TB.OWNER_USR_SN " +
                "AND TA.MOD_SN = TB.MOD_SN " +
                "AND TA.VER_SN = TB.VER_SN " +
                "AND TA.CALL_USR_SN = TB.CALL_USR_SN "
                ;

        // 获取结果集 -------------------------
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 将数据写入json array ----------------------
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        // 将结果遍历写入json --------------------
        try {
            while (resultSet.next()) {
                // 生成json串
                for (int i = 0; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    jsonObject.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
                }

                // 将生成的json串添加到json array
                jsonArray.add(jsonObject);

                // 调用mysql接口，将jsonArray写出
//----------------这里写调用语句----------------

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
