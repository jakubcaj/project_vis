package com.vis.common.dao;

import com.vis.common.dto.ProfileDto;

public interface ProfileDao {
    Long createProfile(ProfileDto profileDto);
}
