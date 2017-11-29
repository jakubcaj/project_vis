package com.vis.common.controller;

import com.vis.common.dto.JSONResponse;
import com.vis.common.dto.ProfileDto;
import com.vis.common.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class RestProfileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = "/create")
    public JSONResponse createProfile(@RequestBody ProfileDto profileDto) {
        JSONResponse jsonResponse = new JSONResponse();

        try {
            profileService.createProfile(profileDto);
            jsonResponse.setSuccess(true);
        }catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during creation of profile.");
        }

        return jsonResponse;
    }
}
