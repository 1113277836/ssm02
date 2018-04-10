package com.hwua.ssm.service.impl;

import com.hwua.ssm.dao.AuthMapper;
        import com.hwua.ssm.dao.UserMapper;
        import com.hwua.ssm.po.Auth;
        import com.hwua.ssm.po.User;
        import com.hwua.ssm.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthMapper authMapper;
    @Override
    public Map<String,Object> queryUser(Map<String,Object> map) {
        List<User> query = userMapper.query(map);
        int count = userMapper.getCount(map);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("total",count);
        hashMap.put("rows",query);
        return hashMap;
    }

    @Override
    public int doInsert(User user) {
        return userMapper.doInsert(user);
    }

    @Override
    public int doUpdate(User user) {
        return userMapper.doUpdate(user);
    }

    public List<Map<String, Object>> getAllRole(Integer roleId) {
        //查询有效的权限
        List<Map<String, Object>> allRole = userMapper.getAllrole(-1);
        List<Integer> role = userMapper.queryByUserId(roleId);
        parseRole(allRole,role);
        return allRole;
    }

    public void parseRole(List<Map<String,Object>> validRole,List<Integer>roles){
        for (Map<String,Object>role:validRole) {
            if (roles.contains(role.get("dbid"))){
                role.put("checked",true);
            }
        }
    }

    @Transactional
    @Override
    public int grantRole(Integer userId, Integer[] roleIds) {
        int i = userMapper.doDeleteUser(userId);
        System.out.println(i);
        List<Map<String,Integer>> param = new ArrayList<>();
        for (Integer roleId:roleIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("userId",userId);
            map.put("roleId",roleId);
            param.add(map);
        }
        i += userMapper.InsertUser(param);
        System.out.println(i);
        return i;
    }

    @Override
    public User login(String userName, String password) {
        return userMapper.login(userName, password);
    }

    @Override
    public List<Auth> getAuths(Integer userId) {
        System.out.println(userId);
        List<Auth> auths = authMapper.queryByUserId(userId);
        System.out.println("auths = " + auths);
        Auth father = null;
        Auth son = null;
        List<Auth> children = null;
        for (int i=auths.size()-1;i>=0;i--){
            //给每一个权限一次当爹的机会，开始找儿子
            father =auths.get(i);
            children=new ArrayList<>();
            for (int j=auths.size()-1;j>=0;j--){
                son=auths.get(j);
                if (son.getParentId().equals(father.getDbid())){
                    //找到儿子
                    children.add(son);
                    auths.remove(son);
                }
            }
        father.setChildren(children);
        }
        for (int i=0;i<auths.size();i++){
            Auth auth = auths.get(i);
            if (!auth.getLayer().equals("1")){
                auths.remove(i);
                i--;
            }
        }
        return auths;
    }
}
