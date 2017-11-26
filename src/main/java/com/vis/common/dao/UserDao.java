package com.vis.common.dao;

import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);

    User getUserById(Long id);

    User registerUser(UserProcessAwaiting userProcessAwaiting);

    User updateUser(UserProcessAwaiting userProcessAwaiting, Long id);

    List<User> getUsers(UserProcessAwaiting userProcessAwaiting);
}
