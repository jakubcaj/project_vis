package com.vis.common.service.impl;

import com.vis.common.dao.ProfileDao;
import com.vis.common.domain.Crime;
import com.vis.common.domain.Profile;
import com.vis.common.dto.CrimeDto;
import com.vis.common.dto.CrimeProfileDto;
import com.vis.common.dto.ProfileDto;
import com.vis.common.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileDao profileDao;

    @Override
    public void createProfile(ProfileDto profileDto) {
        profileDao.createProfile(profileDto);
    }

    @Override
    public List<Profile> getProfiles(String searchTerm, List<CrimeProfileDto> crimeProfileDtoList) {
        return profileDao.getProfiles(searchTerm, crimeProfileDtoList.stream()
                                                    .map(CrimeProfileDto::getId)
                                                    .collect(Collectors.toList()));
    }

    @Override
    public void createCrime(CrimeDto crimeDto) {
        profileDao.createCrime(crimeDto);
    }

    @Override
    public Crime getCrime(Long id) {
        return profileDao.getCrime(id);
    }

    @Override
    public List<Crime> getCrime(String term) {
        return profileDao.getCrime(term);
    }

}
