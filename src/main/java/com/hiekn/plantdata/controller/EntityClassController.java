package com.hiekn.plantdata.controller;

import com.hiekn.plantdata.common.Result;
import com.hiekn.plantdata.infra.EntityClassService;
import com.hiekn.plantdata.infra.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实体类控制层
 */
@RestController
@RequestMapping(value = "models")
public class EntityClassController {

    @Autowired
    private EntityClassService entityClassService;


    /**
     * 新建实体类json
     *
     * @param session
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public Result saveEntitysClassInfo(@RequestParam(value = "name") String eClassname, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        Map<String, Object> map = entityClassService.saveEntitysClassInfo(userId,eClassname);

        if (map.get("id") !=null) {
            return Result.success(map, 200, "保存成功!");
        } else {
            return Result.success("", 303, "用户名冲突!");
        }
    }

    /**
     * 获得实体类列表
     *
     * @param searchStr
     * @param session
     * @return
     */
    @GetMapping(value = "")
    @ResponseBody
    public Result getEntitysList(@RequestParam(value = "searchStr") String searchStr, HttpSession session) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        if (searchStr != null) {
            String userId = (String) session.getAttribute("userId");
            mapList = entityClassService.getEntitysClassList(userId,searchStr, 25);
            return Result.success(mapList, 200, "请求成功!");
        } else {
            return Result.success(mapList, 200, "无数据!");
        }
    }


    /**
     * 获得实体类信息
     *
     * @param id
     * @param session
     * @return
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public Result getEntitysInfo(@PathVariable(value = "id") long id, HttpSession session) {

        String usrID = (String) session.getAttribute("userId");
        Map<String, Object> mapList = entityClassService.getEntitysClassInfo(id,usrID);
        return Result.success(mapList, 200, "请求成功!");
    }


    /**
     * 更新实体类json
     *
     * @param session
     * @return
     */
    @PostMapping(value = "/{id}")
    @ResponseBody
    public Result updateEntityClassInfo(@PathVariable(value = "id") long id, @RequestParam(value = "json") String json, HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        boolean flag = entityClassService.updateEntityClassInfo(userId, id, json);

        if (flag) {
            return Result.success("", 200, "保存成功!");
        } else {
            return Result.success("", 200, "保存失败!");
        }
    }


    /**
     *  获取属性列表
     * @param searchStr
     * @param session
     * @return
     */
    @GetMapping(value = "/{mid}/attributes/keys")
    @ResponseBody
    public Result getAttributesList(@PathVariable(value = "mid") String mid,@RequestParam(value = "searchStr") String searchStr, HttpSession session) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        if(searchStr != null){
            mapList =  entityClassService.getAttributesList(mid,searchStr, 25);
            return Result.success(mapList,200,"请求成功!");
        }else{
            return Result.success(mapList,200,"无数据!");
        }
    }

    /**
     *  获取关系列表
     * @param searchStr
     * @param session
     * @return
     */
    @GetMapping(value = "/{mid}/relations/keys")
    @ResponseBody
    public Result getRelbutesList(@PathVariable(value = "mid") String mid,@RequestParam(value = "searchStr") String searchStr, HttpSession session) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        if(searchStr != null){
            mapList =  entityClassService.getRelbutesList(mid,searchStr, 25);
            return Result.success(mapList,200,"请求成功!");
        }else{
            return Result.success(mapList,200,"无数据!");
        }
    }
}
