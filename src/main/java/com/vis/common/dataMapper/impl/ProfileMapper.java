package com.vis.common.dataMapper.impl;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.vis.common.dataMapper.AbstractBaseMapper;
import com.vis.common.dataMapper.BaseMapper;
import com.vis.common.domain.Profile;
import com.vis.common.querydsl.dimension.QSQLProfile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper extends AbstractBaseMapper implements BaseMapper<Profile> {

    private static final QSQLProfile profile = QSQLProfile.profile;

    public static Expression[] projection = {profile.id, profile.firstName, profile.lastName,profile.fullName, profile.deceased};

    @Override
    public SQLQuery getQuery(SQLQuery query) {
        query.from(profile);
        return query;
    }

    @Override
    public Profile map(Tuple tuple) {
        Profile resultProfile = new Profile();
        resultProfile.setId(tuple.get(profile.id));
        resultProfile.setFirstName(tuple.get(profile.firstName));
        resultProfile.setLastName(tuple.get(profile.lastName));
        resultProfile.setFullName(tuple.get(profile.fullName));
        resultProfile.setDeceased(tuple.get(profile.deceased));

        return resultProfile;
    }
}
