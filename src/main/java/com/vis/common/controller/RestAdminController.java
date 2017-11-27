package com.vis.common.controller;

import com.vis.common.domain.User;
import com.vis.common.dto.JSONResponse;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class RestAdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/search")
    JSONResponse searchUsers (@RequestBody UserProcessAwaiting userProcessAwaiting) {
        JSONResponse jsonResponse = new JSONResponse();
        try{
            List<User> usersResult = userService.getUsersBasedOnSearchCriteria(userProcessAwaiting);
            jsonResponse.setSuccess(true);
            jsonResponse.setObject(usersResult);
        }catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during searching.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/edit")
    JSONResponse editUser (@RequestBody UserProcessAwaiting userProcessAwaiting) {
        JSONResponse jsonResponse = new JSONResponse();
        try{
            if(userService.editUser(userProcessAwaiting, false)) {
                jsonResponse.setSuccess(true);
            } else {
                jsonResponse.setSuccess(false);
                jsonResponse.setErrorMessage("No changes made.");
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during updating.");
        }
        return jsonResponse;
    }
}
