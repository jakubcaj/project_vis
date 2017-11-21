package com.vis.common.service;

import com.vis.common.domain.User;

public interface SecurityService {

    boolean isUserAuthenticated();

    User getLoggedUser();

    String getLoggedUsername();

    String hashPassword(String password);
}
