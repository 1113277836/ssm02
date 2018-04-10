package com.hwua.ssm.service.impl;

import com.hwua.ssm.dao.AuthMapper;
import com.hwua.ssm.po.Auth;
import com.hwua.ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Override
    public List<Auth> queryByDbid(Integer dbid) {
        return authMapper.queryByDbid(-1);
    }

    @Override
    public int doUpdate(Auth auth) {
        return authMapper.doUpdate(auth);
    }

    @Override
    public int doInsert(Auth auth) {
        return authMapper.doInsert(auth);
    }
}
