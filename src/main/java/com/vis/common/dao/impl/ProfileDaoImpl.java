package com.vis.common.dao.impl;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.vis.common.dao.ProfileDao;
import com.vis.common.dataMapper.impl.CrimeMapper;
import com.vis.common.dataMapper.impl.ProfileMapper;
import com.vis.common.dataMapper.impl.VictimMapper;
import com.vis.common.domain.Crime;
import com.vis.common.domain.Profile;
import com.vis.common.domain.Victim;
import com.vis.common.dto.CrimeDto;
import com.vis.common.dto.ProfileDto;
import com.vis.common.querydsl.dimension.QSQLCrime;
import com.vis.common.querydsl.dimension.QSQLProfile;
import com.vis.common.querydsl.dimension.QSQLSuspect;
import com.vis.common.querydsl.dimension.QSQLVictim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public class ProfileDaoImpl extends BaseDao implements ProfileDao {

    private static final QSQLProfile profile = QSQLProfile.profile;
    private static final QSQLCrime crime = QSQLCrime.crime;
    private static final QSQLSuspect suspect = QSQLSuspect.suspect;
    private static final QSQLVictim victim = QSQLVictim.victim;

    @Autowired
    ProfileMapper profileMapper;

    @Autowired
    VictimMapper victimMapper;

    @Autowired
    CrimeMapper crimeMapper;

    @Override
    public Long createProfile(ProfileDto profileDto) {

        SQLInsertClause sqlInsertClause = getSqlInsertClause(profile)
                .set(profile.firstName, profileDto.getFirstName())
                .set(profile.lastName, profileDto.getLastName())
                .set(profile.fullName, profileDto.getFirstName() + " " + profileDto.getLastName())
                .set(profile.birthDate, profileDto.getBirthDate());

        if (profileDto.isDeceased()) {
            sqlInsertClause.set(profile.deceased, true);
        } else {
            if (!profileDto.getEmail().isEmpty()) {
                sqlInsertClause.set(profile.email, profileDto.getEmail());
            }
            if (!profileDto.getPhone().isEmpty()) {
                sqlInsertClause.set(profile.phone, profileDto.getPhone());
            }
        }
        return sqlInsertClause.executeWithKey(profile.id);
    }

    @Override
    public List<Profile> getProfiles(String searchTerm,List<Long> excludeFromSearch) {
        SQLQuery query = profileMapper.getQuery(query());

        query.where(profile.fullName.likeIgnoreCase("%" + searchTerm + "%"));
        query.where(profile.id.notIn(excludeFromSearch));

        return getResult(query, profileMapper, ProfileMapper.projection);
    }

    @Override
    public Long createCrime(CrimeDto crimeDto) {
        Long id = getSqlInsertClause(crime)
                .set(crime.shortDescription, crimeDto.getShortDescription())
                .set(crime.dateCommitted, crimeDto.getDateCommitted())
                .set(crime.description, crimeDto.getDescription())
                .executeWithKey(crime.id);

        crimeDto.getSuspects().forEach(suspectItem -> getSqlInsertClause(suspect)
                .set(suspect.dimensionCrimeId, id)
                .set(suspect.dimensionProfileId, suspectItem.getId())
                .execute());

        crimeDto.getVictims().forEach(victimItem -> getSqlInsertClause(victim)
                .set(victim.dimensionCrimeId, id)
                .set(victim.dimensionProfileId, victimItem.getId())
                .set(victim.statement, victimItem.getStatement())
                .set(victim.injured, victimItem.isInjured())
                .execute());

        return id;
    }

    @Override
    public Crime getCrime(Long id) {
        SQLQuery query = crimeMapper.getQuery(query());
        query.where(crime.id.eq(id));

        return getSingleResult(query, crimeMapper, CrimeMapper.projection);
    }

    @Override
    public List<Crime> getCrime(String term) {
        SQLQuery query = crimeMapper.getQuery(query());
        query.where(crime.shortDescription.likeIgnoreCase("%" + term + "%"));

        return getResult(query, crimeMapper, CrimeMapper.projection);
    }

    @Override
    public void updateCrime(Crime crimeT) {
        getSqlUpdateClause(crime)
                .set(crime.shortDescription, crimeT.getShortDescription())
                .set(crime.description, crimeT.getDescription())
                .set(crime.releasedToPublic, crimeT.getReleasedToPublic())
                .set(crime.dateCommitted, crimeT.getDateCommitted())
                .where(crime.id.eq(crimeT.getId())).execute();
    }

    @Override
    public List<Profile> getSuspects(Long crimeId) {
        SQLQuery query = query().from(suspect);
        query.join(profile).on(profile.id.eq(suspect.dimensionProfileId));
        query.where(suspect.dimensionCrimeId.eq(crimeId));

        return getResult(query, profileMapper, ProfileMapper.projection);
    }

    @Override
    public List<Victim> getVictims(Long crimeId) {
        SQLQuery query = victimMapper.getQuery(query());
        query.where(victim.dimensionCrimeId.eq(crimeId));

        return getResult(query, victimMapper, VictimMapper.projection);
    }

    @Override
    public void releaseToPublic(Long id) {
        getSqlUpdateClause(crime)
                .set(crime.releasedToPublic, Timestamp.valueOf(LocalDateTime.now())).where(crime.id.eq(id)).execute();
    }

    @Override
    public List<Crime> getFirstNCrimesReleasedToPublic(int n) {
        SQLQuery query = crimeMapper.getQuery(query());
        query.where(crime.releasedToPublic.isNotNull());
        query.orderBy(crime.releasedToPublic.desc());
        query.limit(n);

        return getResult(query, crimeMapper, CrimeMapper.projection);
    }
}
