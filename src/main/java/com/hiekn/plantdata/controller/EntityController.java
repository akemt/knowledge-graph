package com.hiekn.plantdata.controller;

import com.hiekn.plantdata.common.Result;
import com.hiekn.plantdata.common.WebSecurityConfig;
import com.hiekn.plantdata.infra.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体控制层
 */
@RestController
@RequestMapping("")
public class EntityController {


    @Autowired
    private EntityService entityService;

    /**
     * 获得实体列表
     *
     * @param searchStr
     * @param session
     * @return
     */
    @GetMapping(value = "entitys")
    @ResponseBody
    public Result getEntitysList(@RequestParam(value = "searchStr") String searchStr, HttpSession session) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        if (searchStr != null) {
            String userId = (String) session.getAttribute("userId");
            mapList = entityService.getEntitysList(userId,searchStr, 25);
            return Result.success(mapList, 200, "请求成功!");
        } else {
            return Result.success(mapList, 200, "无数据!");
        }
    }


    /**
     * 获得实体信息
     *
     * @param id
     * @param session
     * @return
     */
    @GetMapping(value = "entitys/{id}")
    @ResponseBody
    public Result getEntitysInfo(@PathVariable(value = "id") long id, HttpSession session) {

        String usrID = (String) session.getAttribute("userId");
        Map<String, Object> mapList = entityService.getEntitysInfo(id,usrID);
        return Result.success(mapList, 200, "请求成功!");
    }

    /**
     * 更新实体json
     *
     * @param session
     * @return
     */
    @PostMapping(value = "entitys/{id}")
    @ResponseBody
    public Result updateEntitysInfo(@PathVariable(value = "id") long id, @RequestParam(value = "json") String json, HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        boolean flag = entityService.updateEntitysInfo(userId, id, json);

        if (flag) {
            return Result.success("", 200, "保存成功!");
        } else {
            return Result.success("", 200, "保存失败!");
        }
    }


    /**
     * 新建实体关系
     *
     * @param session
     * @return
     */
    @PostMapping(value = "entitys/{id}/relations")
    @ResponseBody
    public Result addEntitysRelation(@PathVariable(value = "id") long id, @RequestParam(value = "json") String json, HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        Map<String, Object> map = entityService.addEntitysRelation(userId, id, json);

        return Result.success(map, 200, "新建实体关系成功!");
    }


    /**
     * 获取实体关系列表
     * @param id
     * @param session
     * @return
     */
    @GetMapping(value = "entitys/{id}/relations")
    @ResponseBody
    public Result getEntitysRelation(@PathVariable(value = "id") long id,HttpSession session) {

        String userId = (String) session.getAttribute("userId");

        List<Map<String,Object>> mapList = entityService.getEntitysRelation(id, userId);
        return Result.success(mapList, 200, "请求成功!");
    }

    /**
     * 删除实体关系
     *
     * @param session
     * @return
     */
    @DeleteMapping(value = "relations/{id}")
    @ResponseBody
    public Result deleteEntitysRelation(@PathVariable(value = "id") long id,HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        boolean flag = entityService.deleteEntitysRelation(userId, id);

        if (flag) {
            return Result.success("", 200, "新建实体关系成功!");
        } else {
            return Result.success("", 200, "新建实体关系失败!");
        }
    }

    /**
     * 以此实体类新建实体
     * @param eClassname
     * @param mID
     * @param session
     * @return
     */
    @PostMapping(value = "entitys")
    @ResponseBody
    public Result saveEntitysInfo(@RequestParam(value = "name") String eClassname,@RequestParam(value = "id") long mID,HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        Map<String, Object> map = entityService.saveEntitysInfo(userId,eClassname,mID);

        if (map.get("id") !=null) {
            return Result.success(map, 200, "保存成功!");
        } else {
            return Result.success("", 303, "用户名冲突!");
        }
    }


    /**
     * 获取当前用户下的实体ID
     * @param session
     * @return
     */
    @GetMapping(value = "entitys/nodeID")
    @ResponseBody
    public Result getEntitysID(HttpSession session) {

        String userId = (String) session.getAttribute("userId");
//        String userId = "2";
        List<Long> mapList = entityService.getUsrGraphNodesList(userId);
        Map<String, Object> map = new HashMap<>();
        if(mapList.size()>0){
            map.put("nodeID",mapList.get(0));
        }else{
            map.put("nodeID","");
        }
        return Result.success(map, 200, "请求成功!");
    }
}
