package com.hwua.ssm.Controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.User;
import com.hwua.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/toMain")
    public String main(){
        return "user";
    }
    @ResponseBody
    @RequestMapping(value = "/queryUser",produces = "application/json;charset=UTF-8")
    private String queryUser(String userName,String realName,String valid,Integer page,Integer rows){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("userName", StringUtils.isEmpty(userName) ? null:userName);
        hashMap.put("realName",StringUtils.isEmpty(realName) ? null:realName);
        hashMap.put("valid",valid);
        hashMap.put("start",(page-1)*rows);
        hashMap.put("rows",rows);
        Map<String, Object> map = userService.queryUser(hashMap);
        System.out.println("map = " + map);
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8")
    private String update(User user){
        String s = user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(s.getBytes()));
        JSONObject jsonObject = new JSONObject();
        if (user.getDbid()==null){
            int i = userService.doInsert(user);
            if (i==1){
                jsonObject.put("msg",true);
            }else{
                jsonObject.put("msg",false);
            }
        }else {
            int i = userService.doUpdate(user);
            if (i==1){
                jsonObject.put("msg",true);
            }else {
                jsonObject.put("msg",false);
            }
        }
        return jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public String login(String userName, String password, HttpSession session){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userService.login(userName, password);
        if (user!=null){
            session.setAttribute("user",user);
            List<Auth> auths = userService.getAuths(user.getDbid());
            session.setAttribute("auths",auths);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",user!=null);
        return jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/getAllRole",produces = "application/json;charset=UTF-8")
    public String getAllRole(Integer userId){
        List<Map<String,Object>> list = userService.getAllRole(userId);
        System.out.println(JSON.toJSONString(list,true));
        System.out.println("---------------==============-=-=-=-=-");
        return JSON.toJSONString(list);
    }
    @ResponseBody
    @RequestMapping(value = "/grantRole",produces = "application/json;charset=UTF-8")
    public String grantRole(Integer userId,Integer[] roleIds){
        System.out.println("userId = [" + userId + "], roleIds = [" + Arrays.toString(roleIds) + "]");
        int i = userService.grantRole(userId, roleIds);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/exit",produces = "application/json;charset=UTF-8")
    public String exit(HttpSession session){
            session.invalidate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",true);
        return jsonObject.toJSONString();
    }
}
