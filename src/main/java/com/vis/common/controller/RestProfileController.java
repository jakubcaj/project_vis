package com.vis.common.controller;

import com.vis.common.domain.Crime;
import com.vis.common.domain.Profile;
import com.vis.common.dto.*;
import com.vis.common.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during creation of profile.");
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/search")
    public JSONResponse searchProfile(@RequestBody SearchProfileDto searchProfileDto) {
        JSONResponse jsonResponse = new JSONResponse();

        try {
            List<Profile> profileList = profileService.getProfiles(searchProfileDto.getTerm(), searchProfileDto.getCrimeProfileDtoList());
            jsonResponse.setSuccess(true);
            jsonResponse.setObject(profileList);
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during searching.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/crime/create")
    public JSONResponse createCrime(@RequestBody CrimeDto crimeDto) {
        JSONResponse jsonResponse = new JSONResponse();

        try {
            profileService.createCrime(crimeDto);
            jsonResponse.setSuccess(true);
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during creation of crime.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/crime/search")
    public JSONResponse searchCrime(@RequestBody String term) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            List<Crime> crimeList = profileService.getCrime(term);
            jsonResponse.setSuccess(true);
            jsonResponse.setObject(crimeList);
        }catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during searching");
        }

        return jsonResponse;
    }
}
