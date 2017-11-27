package com.vis.common.controller;

import com.vis.common.domain.User;
import com.vis.common.dto.JSONResponse;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.enums.Role;
import com.vis.common.service.SecurityService;
import com.vis.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BaseController {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");

        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        model.addObject("roles", Role.values());

        return model;

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView();
        User user = securityService.getLoggedUser();

        modelAndView.addObject("firstName", user.getFirstName());
        modelAndView.addObject("lastName", user.getLastName());
        modelAndView.addObject("email", user.getEmail());
        modelAndView.addObject("username", user.getUsername());
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
//                              @RequestParam(value = "logout", required = false) String logout) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("hello");
//
//        return model;
//
//    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public @ResponseBody
    JSONResponse registerUser(@RequestBody UserProcessAwaiting userProcessAwaiting) {
        JSONResponse response = new JSONResponse();
        try {
            if(userService.isUserExisting(userProcessAwaiting.getUsername())) {
                response.setSuccess(false);
                response.setErrorMessage("Username is already existing.");
            } else {
                boolean success = userService.registerUser(userProcessAwaiting);
                if (!success) {
                    response.setErrorMessage("Registration failed.");
                }
                response.setSuccess(success);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setErrorMessage("There was an unexpected error during registration.");
        }
        return response;
    }


}