package com.hwua.ssm.Controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.ssm.po.Role;
import com.hwua.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/toMain")
    public String main(){
        return "role";
    }
    @ResponseBody
    @RequestMapping(value = "/queryRole",produces = "application/json;charset=UTF-8")
    private String queryRole(String roleName,String roleCode,String valid,Integer page,Integer rows){
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("roleName", StringUtils.isEmpty(roleName) ? null : roleName);
        hashMap.put("roleCode",StringUtils.isEmpty(roleCode) ? null : roleCode);
        hashMap.put("valid",valid);
        hashMap.put("start",(page - 1)*rows);
        hashMap.put("rows",rows);
        Map<String, Object> map = roleService.queryRole(hashMap);
        System.out.println("map = " + map);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/doInsertRole",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String doInsert(Role role){
        JSONObject jsonObject = new JSONObject();
        if (role.getDbid()==null){
            int i = roleService.doInsert(role);
            if (i==1){
                jsonObject.put("msg",true);
            }else {
                jsonObject.put("msg",false);
            }
        }else {
            int i = roleService.doUpdate(role);
            if (i==1){
                jsonObject.put("msg",true);
            }else {
                jsonObject.put("msg",false);
            }
        }
        return jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/getAllAuth",produces = "application/json;charset=UTF-8")
    public String getAllAuth(Integer roleId){
        System.out.println("roleId = " + roleId);
        List<Map<String,Object>> list = roleService.getAllAuth(roleId);
        System.out.println(JSON.toJSONString(list,true));
        return JSON.toJSONString(list);
    }
    @ResponseBody
    @RequestMapping(value = "/grantAuth",produces = "application/json;charset=UTF-8")
    public String grantAuth(Integer roleId,Integer[] authIds){
        System.out.println(roleId+"============");
        int i = roleService.grantAuth(roleId, authIds);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }
}
