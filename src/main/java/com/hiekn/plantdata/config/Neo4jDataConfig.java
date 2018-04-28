package com.hiekn.plantdata.config;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Driver;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Neo4jDataConfig {

    /**
     * 查询
     */
    public void selectValue() {

        DbConnect db = new DbConnect();

        Statement stmt = null;

        String cypher="match (n:diseaseName)-[r]-(m) where n.name='月经不调' return n.name as disease,r,m.desc as patient";

        try {

            stmt = db.getConnetion().createStatement();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        try {

            ResultSet rs = stmt.executeQuery(cypher);

            while(rs.next()){

                System.out.println("描述："+rs.getString("patient"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void createValue() {

        DbConnect db = new DbConnect();



        Statement stmt = null;

        String cypher = "CREATE (emp:Employee{id:123,name:'Lokesh',sal:35000,deptno:10})";



        try {

            stmt = db.getConnetion().createStatement();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        try {

            int rs = stmt.executeUpdate(cypher);

            if(rs>0){

                System.out.println("插入数据成功！");

            }

        } catch (SQLException e) {



            e.printStackTrace();

        }

    }

}
