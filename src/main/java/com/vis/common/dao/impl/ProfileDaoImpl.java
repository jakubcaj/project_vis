package com.vis.common.dao.impl;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.vis.common.dao.ProfileDao;
import com.vis.common.dataMapper.impl.ProfileMapper;
import com.vis.common.dto.ProfileDto;
import com.vis.common.querydsl.dimension.QSQLProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProfileDaoImpl extends BaseDao implements ProfileDao {

    private static final QSQLProfile profile = QSQLProfile.profile;

    @Autowired
    ProfileMapper profileMapper;

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
}
