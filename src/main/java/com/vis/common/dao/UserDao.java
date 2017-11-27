package com.vis.common.dao;

import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.enums.Role;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);

    User getUserById(Long id);

    User registerUser(UserProcessAwaiting userProcessAwaiting);

    User updateUser(UserProcessAwaiting userProcessAwaiting, Long id);

    List<User> getUsers(UserProcessAwaiting userProcessAwaiting);

//    void changeUserRole(List<Role> roles, Long id);

    void insertRolesToUser(List<Role> roles, Long id);

    void deleteRolesToUser(List<Role> roles, Long id);

    List<Role> getUserRoles(Long id);
}
