package com.hwua.ssm.dao;

import com.hwua.ssm.po.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public List<User>query(Map<String,Object>map);
    public int getCount(Map<String,Object>map);
    public int doInsert(User user);
    public int doUpdate(User user);
    public List<Map<String, Object>> getAllrole(Integer userId);
    public List<Integer>queryByUserId(Integer id);
    public int doDeleteUser(Integer userId);
    public int InsertUser(List<Map<String,Integer>>params);
    public User login(String userName,String password);
}
