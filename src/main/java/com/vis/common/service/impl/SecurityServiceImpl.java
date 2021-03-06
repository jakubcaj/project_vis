package com.vis.common.service.impl;

import com.vis.common.dao.UserDao;
import com.vis.common.domain.User;
import com.vis.common.enums.Role;
import com.vis.common.facade.AuthenticationFacade;
import com.vis.common.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    UserDao userDao;

    @Override
    public boolean isUserAuthenticated() {
        return !authenticationFacade.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }

    @Override
    public User getLoggedUser() {
        return userDao.getUserByUsername(authenticationFacade.getAuthentication().getName());
    }

    @Override
    public String getLoggedUsername() {
        return authenticationFacade.getAuthentication().getName();
    }

    @Override
    public String hashPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public List<Role> getUserRoles(Long id) {
        return userDao.getUserRoles(id);
    }

    @Override
    public boolean hasLoggedUserRole(String role) {
        return authenticationFacade.getAuthentication().getAuthorities().stream()
                .anyMatch(x -> x.toString().equals(role) || x.toString().equals(Role.ADMIN.getRoleString()));
    }
}
