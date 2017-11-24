package com.vis.common.controller;

import com.vis.common.dto.JSONResponse;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class RestUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public JSONResponse editUser(@RequestBody UserProcessAwaiting user) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            if(userService.editUser(user)) {
                jsonResponse.setSuccess(true);
            } else {
                jsonResponse.setSuccess(false);
                jsonResponse.setErrorMessage("No changes made.");
            }
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during update.");
        }
        return jsonResponse;
    }
}
