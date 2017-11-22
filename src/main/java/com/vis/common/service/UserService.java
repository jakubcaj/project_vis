package com.vis.common.service;

import com.vis.common.dto.UserProcessAwaiting;

public interface UserService {

    boolean isUserExisting(String userName);

    boolean registerUser(UserProcessAwaiting userProcessAwaiting);
}
