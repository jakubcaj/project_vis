package com.vis.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
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

        http.authorizeRequests()//.anyRequest().permitAll()
//                .antMatchers("/*").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user*/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .antMatchers("/profile*/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFILES')")
                .antMatchers("/crime*/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFILES')")
//                .anyRequest().access("!hasRole('ROLE_ANONYMOUS')")
                .and()
                .formLogin().loginProcessingUrl("/auth").failureUrl("/").defaultSuccessUrl("/")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/")
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .and()
                .csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
