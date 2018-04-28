package com.hiekn.plantdata.dao;

import org.neo4j.driver.v1.*;

import java.util.Map;

import static org.neo4j.driver.v1.Values.parameters;


public class Neo4jRepository {
    Driver driver;

    public Neo4jRepository(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }
    //定义添加数据的方法。这里可以选择多种方式添加数据，可以通过字符串或者map集合添加数据，
    // 只需要修改tx.run()里面的cypher语句和parameters里面的参数即可，这里要说明一下，
    // parameters里面的参数的个数需要与cypher语句里面的变量的个数相同，否则报异常，这点与sql语句类似。下面分别举例说明定义方式。

    /**
     * 字符串添加。
     * 通过传入参数的方式添加数据，这里可以传入多个参数， 下面代码的cypher语句部分{x}和{y}可以用$x和$y代替，形式不同，结果一样。
     *
     * @param name
     * @param age
     */
    private void addPersonByString(String name, int age)
    {
        //会话是轻量级和一次性连接包装器。
        try (Session session = driver.session())
        {
            //在显式事务中包装Cypher提供原子性
            try (Transaction tx = session.beginTransaction())
            {
                tx.run("MERGE (a:Person {name: {x},age:{y}})", parameters("x", name, "y", age));
                tx.success();  // Mark this write as successful.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * map集合
     * 为了批量添加数据，而且不用频繁调用方法，使用map等集合的方式添加数据，相比之下更加简洁便利。下面是代码。
     *
     * @param map
     */
    private void addPersonByMap(Map<String, Integer> map)
    {
        try (Session session = driver.session())
        {
            try (Transaction tx = session.beginTransaction())
            {
                for (Map.Entry entry : map.entrySet()) {
                    tx.run("MERGE (a:Person {name: {x},age:{y}})", parameters("x", entry.getKey(), "y", entry.getValue()));
                }
                tx.success();  // Mark this write as successful.
            }
        }
    }

    /**
     * 由于cypher语句中的变量，不能把label设置为变量若要删除某标签下的所有节点，可以选择拼接字符串的方式，例如下面代码所示。
     *
     * @param label
     */
    private void deleteNodeByLabel(String label) {//删除某一标签下的所有节点
        try (Session session = driver.session())
        {
            String cypher = "match (a:" + label + ") delete a";
            System.out.println(cypher);
            try (Transaction tx = session.beginTransaction())
            {
                tx.run(cypher);
                tx.success();  // Mark this write as successful.
                System.out.println("删除节点成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取数据，逻辑在于cypher语句。
     *
     * @param initial
     */
    private void printPeople(String initial)
    {
        try (Session session = driver.session())
        {
            //自动提交事务是一个快速而简单的方法来完成读取。
            StatementResult result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH $x RETURN a.name AS name ,a.age as age", parameters("x", initial));
            //每一个CYPHER执行都返回一个记录流。
            while (result.hasNext())
            {
                Record record = result.next();
                //值可以通过索引或名称从记录中提取。
                System.out.println("姓名：" + record.get("name").asString() + "年龄：" + record.get("age").asInt());
            }
        }
    }

    /**
     * 保存节点或者关系返回相应 ID 。
     *
     * @param cypher
     */
    public StatementResult saveLabels(String cypher) {
        StatementResult statementResult = null;
        try (Session session = driver.session())
        {
//            String cypher = "match (a:" + label + ") delete a";
            System.out.println(cypher);
            try (Transaction tx = session.beginTransaction())
            {
                statementResult = tx.run(cypher);
                tx.success();  // Mark this write as successful.
                System.out.println("创建节点成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statementResult;
    }

    /**
     * 关闭驱动
     */
    public void close() {
        driver.session().close();
        driver.close();
    }

    public static void main(String... args)
    {
        Neo4jRepository example = new Neo4jRepository("bolt://localhost:7687/",
                "neo4j", "123456");
        // example.deleteNodeByLabel("Person");
//        Map info = new HashMap();
//        info.put("Ada", 18);
//        example.addPersonByMap(info);
//        example.addPersonByString("Jack", 23);
//        example.addPersonByString("Alice", 24);
//        example.addPersonByString("Bob", 32);
//        example.printPeople("A");
        String jAttrName = "波音777";
        String jAttrNamesss= "实体##波音777";
        String strAttr = "MERGE (a:`"+jAttrName+"` {global_name:'"+jAttrNamesss+"',name: '"+jAttrName+"'})";
//        String strAttr = "MERGE (a:"+jAttrName+" {name: '"+jAttrName+"',value:'1111'})";
        example.saveLabels(strAttr);

        example.close();

    }

}
