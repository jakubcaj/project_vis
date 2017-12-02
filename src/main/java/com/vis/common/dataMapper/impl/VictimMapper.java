package com.vis.common.dataMapper.impl;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.vis.common.dataMapper.AbstractBaseMapper;
import com.vis.common.dataMapper.BaseMapper;
import com.vis.common.domain.Profile;
import com.vis.common.domain.Victim;
import com.vis.common.querydsl.dimension.QSQLProfile;
import com.vis.common.querydsl.dimension.QSQLVictim;
import com.mysema.query.types.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VictimMapper extends AbstractBaseMapper implements BaseMapper<Victim> {

    private static final QSQLVictim victim = QSQLVictim.victim;
    private static final QSQLProfile profile = QSQLProfile.profile;

    @Autowired
    ProfileMapper profileMapper;

    public static Expression[] projection = {victim.id, victim.dimensionProfileId, victim.injured, victim.statement,
            profile.id, profile.firstName, profile.lastName,profile.fullName, profile.deceased};

    @Override
    public SQLQuery getQuery(SQLQuery query) {
        query.from(victim).join(profile).on(profile.id.eq(victim.dimensionProfileId));

        return query;
    }

    @Override
    public Victim map(Tuple tuple) {
        Victim resultVictim = new Victim();
        resultVictim.setId(tuple.get(victim.id));
        resultVictim.setInjured(tuple.get(victim.injured));
        resultVictim.setStatement(tuple.get(victim.statement));
        resultVictim.setProfile( profileMapper.map(tuple));
        return resultVictim;
    }
}
