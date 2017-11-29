package com.vis.common.service.impl;

import com.vis.common.dao.ProfileDao;
import com.vis.common.dto.ProfileDto;
import com.vis.common.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileDao profileDao;

    @Override
    public void createProfile(ProfileDto profileDto) {
        profileDao.createProfile(profileDto);
    }

}
