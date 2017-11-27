package com.vis.common.service.impl;

import com.vis.common.dao.UserDao;
import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.enums.Role;
import com.vis.common.service.SecurityService;
import com.vis.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public boolean editUser(UserProcessAwaiting userToModify, boolean currentUser) {
        User originalUser;
        boolean userChanged;

        if (currentUser) {
            originalUser = securityService.getLoggedUser();
        } else {
            originalUser = userDao.getUserById(userToModify.getId());
        }

        if (hasUserDifferences(originalUser, userToModify)) {
            userDao.updateUser(userToModify, originalUser.getId());
            userChanged = true;
        } else {
            userChanged = false;
        }

        if (userToModify.getRoles() != null && hasRoleChanged(originalUser, userToModify)) {
            List<Role> rolesToInsert = userToModify.getRoles().stream()
                    .filter(x -> originalUser.getRoles().stream().noneMatch(y -> y.equals(x)))
                    .collect(Collectors.toList());

            List<Role> rolesToDelete = originalUser.getRoles().stream()
                    .filter(x -> userToModify.getRoles().stream().noneMatch(y -> y.equals(x)))
                    .collect(Collectors.toList());

            if(!rolesToDelete.isEmpty()) {
                userDao.deleteRolesToUser(rolesToDelete, originalUser.getId());
            }

            if(!rolesToInsert.isEmpty()) {
                userDao.insertRolesToUser(rolesToInsert, originalUser.getId());
            }
            userChanged = true;
        }
        return userChanged;
    }

    @Override
    public List<User> getUsersBasedOnSearchCriteria(UserProcessAwaiting user) {
        return userDao.getUsers(user);
    }

    private boolean hasUserDifferences(User user, UserProcessAwaiting userToModify) {
        if (!user.getFirstName().equals(userToModify.getFirstName())) {
            return true;
        }

        if (!user.getLastName().equals(userToModify.getLastName())) {
            return true;
        }

        if (!user.getEmail().equals(userToModify.getEmail())) {
            return true;
        }

        if (!user.getUsername().equals(userToModify.getUsername())) {
            return true;
        }

        return false;
    }

    private boolean hasRoleChanged(User user, UserProcessAwaiting userToModify) {
        return !user.getRoles().equals(userToModify.getRoles());
    }
}
