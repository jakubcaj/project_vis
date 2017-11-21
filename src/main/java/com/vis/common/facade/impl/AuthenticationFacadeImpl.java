package com.vis.common.facade.impl;

import com.vis.common.facade.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl  implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
//        DataSource dataSource = (DataSource)ApplicationContextProvider.getApplicationContext().getBean("dataSource");
//        getJdb
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
