package com.vis.common.service;

import com.vis.common.domain.Crime;
import com.vis.common.domain.Profile;
import com.vis.common.dto.CrimeDto;
import com.vis.common.dto.CrimeProfileDto;
import com.vis.common.dto.ProfileDto;

import java.util.List;

public interface ProfileService {

    void createProfile(ProfileDto profileDto);

    List<Profile> getProfiles(String searchTerm, List<CrimeProfileDto> crimeProfileDtoList);

    void createCrime(CrimeDto crimeDto);

    Crime getCrime(Long id);

    List<Crime> getCrime(String term);
}
