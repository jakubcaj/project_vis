package com.vis.common.service.impl;

import com.vis.common.dao.UserDao;
import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.service.SecurityService;
import com.vis.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    SecurityService securityService;

    @Override
    public boolean isUserExisting(String userName) {
        return userDao.getUserByUsername(userName) != null;
    }

    @Override
    public boolean registerUser(UserProcessAwaiting userProcessAwaiting) {
        userProcessAwaiting.setPassword(securityService.hashPassword(userProcessAwaiting.getPassword()));

        return userDao.registerUser(userProcessAwaiting) != null;
    }

    @Override
    public boolean editUser(UserProcessAwaiting userToModify) {
        User originalUser = securityService.getLoggedUser();
        if(hasUserDifferences(originalUser, userToModify)) {
            userDao.updateUser(userToModify,originalUser.getId());
            return true;
        } else {
            return false;
        }
    }

    private boolean hasUserDifferences(User user, UserProcessAwaiting userToModify) {
        if(!user.getFirstName().equals(userToModify.getFirstName())) {
            return true;
        }

        if(!user.getLastName().equals(userToModify.getLastName())) {
            return true;
        }

        if(!user.getEmail().equals(userToModify.getEmail())) {
            return true;
        }

        if(!user.getUsername().equals(userToModify.getUsername())) {
            return true;
        }

        return false;
    }
}
