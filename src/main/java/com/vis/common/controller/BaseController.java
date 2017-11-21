package com.vis.common.controller;

import com.vis.common.dao.UserDao;
import com.vis.common.domain.User;
import com.vis.common.dto.JSONResponse;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BaseController {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");

//        addSecurittyContext(model);
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("hello");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public @ResponseBody JSONResponse registerUser(@RequestBody UserProcessAwaiting userProcessAwaiting) {
        JSONResponse response = new JSONResponse();

        return response;
    }

    @RequestMapping(value = "/successLogin", method = RequestMethod.POST)
    public @ResponseBody User successLogin() {
        User user = new User();
        if(securityService.isUserAuthenticated()) {
            user = securityService.getLoggedUser();
        }
        return user;
    }

    private void addSecurittyContext(ModelAndView modelAndView) {
        modelAndView.addObject("isUserAuthenticated", securityService.isUserAuthenticated());
        modelAndView.addObject("loggedUser", securityService.getLoggedUsername());
    }
}