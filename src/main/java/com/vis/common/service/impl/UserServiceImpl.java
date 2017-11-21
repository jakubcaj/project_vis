package com.vis.common.service.impl;

import com.mysema.query.sql.dml.SQLInsertClause;
import com.vis.common.dao.UserDao;
import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.service.SecurityService;
import com.vis.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    SecurityService securityService;

    public boolean isUserExisting(String userName) {
        return userDao.getUserByUsername(userName) != null;
    }

    public User registerUser(UserProcessAwaiting userProcessAwaiting) {
        userProcessAwaiting.setPassword(securityService.hashPassword(userProcessAwaiting.getPassword()));
        return null;
    }
}
