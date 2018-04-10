package com.hwua.ssm.service;

import com.hwua.ssm.po.Auth;
import com.hwua.ssm.po.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String,Object>queryUser(Map<String,Object>map);
    public int doInsert(User user);
    public int doUpdate(User user);
    public List<Map<String, Object>> getAllRole(Integer roleId);
    public int grantRole(Integer userId,Integer[]roleIds);
    public User login(String userName,String password);
    public List<Auth>getAuths(Integer userId);
}
