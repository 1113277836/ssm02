package com.hwua.ssm.service;

import com.hwua.ssm.po.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    public Map<String, Object> queryRole(Map<String, Object> map);

    public int doInsert(Role role);

    public int doUpdate(Role role);

    public List<Map<String, Object>> getAllAuth(Integer roleId);

    public void parseAuth(List<Map<String, Object>> validAuth, List<Integer> auths);

    public int grantAuth(Integer roleId,Integer[]authIds);
}