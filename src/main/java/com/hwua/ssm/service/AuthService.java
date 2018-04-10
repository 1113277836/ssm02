package com.hwua.ssm.service;

import com.hwua.ssm.po.Auth;

import java.util.List;

public interface AuthService {
    public List<Auth> queryByDbid(Integer dbid);

    public int doUpdate(Auth auth);

    public int doInsert(Auth auth);


}
