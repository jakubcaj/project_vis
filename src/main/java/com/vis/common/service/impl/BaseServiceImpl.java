package com.vis.common.service.impl;

import com.vis.common.dao.UserDao;
import com.vis.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService{

    @Autowired
    UserDao userDao;


}
