package com.vis.common.dao;

import com.vis.common.domain.Crime;
import com.vis.common.domain.Profile;
import com.vis.common.domain.Victim;
import com.vis.common.dto.CrimeDto;
import com.vis.common.dto.ProfileDto;

import java.util.List;

public interface ProfileDao {
    Long createProfile(ProfileDto profileDto);

    List<Profile> getProfiles(String searchTerm, List<Long> excludeFromSearch);

    Long createCrime(CrimeDto crimeDto);

    Crime getCrime(Long id);

    List<Crime> getCrime(String term);

    void updateCrime(Crime crime);

    List<Profile> getSuspects(Long crimeId);

    List<Victim> getVictims(Long crimeId);

    void releaseToPublic(Long id);

    List<Crime> getFirstNCrimesReleasedToPublic(int n);
}
