package com.vis.common.controller;

import com.thoughtworks.xstream.XStream;
import com.vis.common.domain.Crime;
import com.vis.common.domain.Profile;
import com.vis.common.dto.*;
import com.vis.common.enums.Role;
import com.vis.common.service.ProfileService;
import com.vis.common.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/profile")
public class RestProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    SecurityService securityService;

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
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during searching.");
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/crime/releasetopublic")
    public JSONResponse releaseToPublic(@RequestBody Long id) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            if (securityService.hasLoggedUserRole(Role.RELEASE_PUBLIC.getRoleString())) {
                profileService.releaseToPublic(id);
                jsonResponse.setSuccess(true);
            } else {
                jsonResponse.setSuccess(false);
                jsonResponse.setErrorMessage("Access Denied");
            }
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("There was an unexpected error during releasing to public.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/export/{crimeId}")
    public HttpEntity<byte[]> exportCrime(@PathVariable(value = "crimeId") Long crimeId) {

        Crime crime = profileService.getCrime(crimeId);
        XStream xs = new XStream();
        byte[] result = xs.toXML(crime).getBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_XML);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=crime");
        header.setContentLength(result.length);

        return new HttpEntity<>(result, header);
    }

    @RequestMapping(value = "/import")
    public JSONResponse importCrime(@RequestBody String crimeXml) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            XStream xs = new XStream();
            Crime crime = (Crime) xs.fromXML(crimeXml);

            profileService.insertUpdateCrime(crime);

            jsonResponse.setSuccess(true);
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
        }
        return jsonResponse;
    }
}
