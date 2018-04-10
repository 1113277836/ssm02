package com.hwua.ssm.test;

import com.alibaba.fastjson.JSON;
import com.hwua.ssm.dao.AuthMapper;
import com.hwua.ssm.dao.RoleMapper;
import com.hwua.ssm.dao.UserMapper;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.Role;
import com.hwua.ssm.po.User;
import com.hwua.ssm.service.RoleService;
import com.hwua.ssm.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSSM {
    @Test
    public void testauth(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        RoleMapper mapper = ctx.getBean(RoleMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("roleName","管");
        //map.put("roleCode","admin");
       // map.put("page",(2-1)*2);
        //map.put("row",2);
        List<Role> query = mapper.query(map);
        System.out.println(query);
    }
    @Test
    public void TestMD5(){
        String str = "123456";
        String s = DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println("s = " + s);
    }
    @Test
    public void Test(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        RoleService bean = ctx.getBean(RoleService.class);
        Integer[]  a= {6,7,8};
        int i = bean.grantAuth(30, a);
        System.out.println(i);
    }
    @Test
    public void test(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService bean = ctx.getBean(UserService.class);
        List<Auth> auths = bean.getAuths(29);
        System.out.println("auths="+ JSON.toJSONString(auths));


    }

}
