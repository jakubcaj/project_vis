package com.vis.common.service;

import com.vis.common.domain.User;
import com.vis.common.enums.Role;

import java.util.List;

public interface SecurityService {

    boolean isUserAuthenticated();

    User getLoggedUser();

    String getLoggedUsername();

    String hashPassword(String password);

    List<Role> getUserRoles(Long id);
}
