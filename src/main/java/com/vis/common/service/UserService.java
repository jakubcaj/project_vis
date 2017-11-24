package com.vis.common.service;

import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;

public interface UserService {

    boolean isUserExisting(String userName);

    boolean registerUser(UserProcessAwaiting userProcessAwaiting);

    boolean editUser(UserProcessAwaiting userToModify);
}
