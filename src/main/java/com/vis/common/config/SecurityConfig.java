package com.vis.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT\n" +
                        "  users.username,\n" +
                        "  users.password,\n" +
                        "  users.enabled\n" +
                        "FROM keystone.user_auth users\n" +
                        "WHERE users.username = ?;")
                .authoritiesByUsernameQuery("SELECT\n" +
                        "  users.username,\n" +
                        "  r.role\n" +
                        "FROM keystone.user_auth users\n" +
                        "  JOIN keystone.user_role ur ON ur.dimension_user_id = users.id\n" +
                        "  JOIN keystone.role r ON r.id = ur.role_id\n" +
                        "WHERE users.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();

    }
}
