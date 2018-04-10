package com.hwua.ssm.dao;

import com.hwua.ssm.po.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    public List<Role>query(Map<String,Object>map);
    public int getCount(Map<String,Object>map);
    public int doInsert(Role role);
    public int doUpdate(Role role);
    public List<Map<String, Object>> getAllAuth(Integer roleId);
    public List<Integer>queryByRoleId(Integer id);
    public int doDeleteRole(Integer roleId);
    public int InsertRole(List<Map<String,Integer>>params);
}





