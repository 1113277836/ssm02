package com.hwua.ssm.service.impl;

import com.hwua.ssm.dao.RoleMapper;
import com.hwua.ssm.po.Role;
import com.hwua.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Map<String,Object> queryRole(Map<String, Object> map) {
        List<Role> roles = roleMapper.query(map);
        int count = roleMapper.getCount(map);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("total",count);
        hashMap.put("rows",roles);
        return hashMap;
    }

    @Override
    public int doInsert(Role role) {
        return roleMapper.doInsert(role);
    }

    @Override
    public int doUpdate(Role role) {
        return roleMapper.doUpdate(role);
    }
    public List<Map<String, Object>> getAllAuth(Integer roleId) {
        //查询有效的权限
        List<Map<String, Object>> allAuth = roleMapper.getAllAuth(-1);
        List<Integer> auth = roleMapper.queryByRoleId(roleId);
        parseAuth(allAuth,auth);
        return allAuth;
    }

    /**
     * 递归设置checked属性
     * @param validAuth
     * @param auths
     */
    public void parseAuth(List<Map<String,Object>> validAuth,List<Integer>auths){
        for (Map<String,Object>auth:validAuth) {
            if (auths.contains(auth.get("id"))){
                auth.put("checked",true);
            }
            List<Map<String,Object>> children = (List<Map<String, Object>>) auth.get("children");
            parseAuth(children,auths);
        }
    }
/*
当一个业务操作增删改的数据操作时，
 */
    @Transactional
    @Override
    public int grantAuth(Integer roleId, Integer[] authIds) {
        int i = roleMapper.doDeleteRole(roleId);
        List<Map<String,Integer>> param = new ArrayList<>();
        for (Integer authId:authIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("roleId",roleId);
            map.put("authId",authId);
            param.add(map);
        }
        i += roleMapper.InsertRole(param);
        return i;
    }
}
