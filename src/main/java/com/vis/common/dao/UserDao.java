package com.vis.common.dao;

import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;

public interface UserDao {

    User getUserByUsername(String username);

    User registerUser(UserProcessAwaiting userProcessAwaiting);
}
