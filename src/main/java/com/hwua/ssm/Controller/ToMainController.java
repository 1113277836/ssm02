package com.hwua.ssm.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.ssm.dao.AuthMapper;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class ToMainController {
    @Autowired
    private AuthService authService;

    @RequestMapping("/toMain")
    public String toMain(){
        return "auth";
    }

    @RequestMapping(value = "/toAuth",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String toAuth(){
        List<Auth> auths = authService.queryByDbid(-1);
        System.out.println("auths = " + auths);
        return JSON.toJSONString(auths);
    }
    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String update(Auth auth){
        JSONObject jsonObject = new JSONObject();
        System.out.println("auth = " + auth);
        if (auth.getDbid()==null){
            int i = authService.doInsert(auth);
            if (i==1){
                jsonObject.put("msg",true);
            }else {
                jsonObject.put("msg",false);
            }
        }else {
            int i = authService.doUpdate(auth);
            if (i==1){
                jsonObject.put("msg",true);
            }else {
                jsonObject.put("msg",false);
            }
        }
        return jsonObject.toJSONString();
    }
}
