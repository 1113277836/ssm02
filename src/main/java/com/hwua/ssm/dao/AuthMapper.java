package com.hwua.ssm.dao;

import com.hwua.ssm.po.Auth;

import java.util.List;

public interface AuthMapper {
    public List<Auth> queryByDbid(Integer dbid);
    public int doUpdate(Auth auth);
    public int doInsert(Auth auth);
    public List<Auth> queryByUserId(Integer userId);
}
