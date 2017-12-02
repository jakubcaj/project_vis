package com.vis.common.dataMapper.impl;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.vis.common.dao.ProfileDao;
import com.vis.common.dataMapper.AbstractBaseMapper;
import com.vis.common.dataMapper.BaseMapper;
import com.vis.common.domain.Crime;
import com.mysema.query.types.Expression;
import com.vis.common.querydsl.dimension.QSQLCrime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrimeMapper extends AbstractBaseMapper implements BaseMapper<Crime> {

    @Autowired
    ProfileDao profileDao;

    private static final QSQLCrime crime = QSQLCrime.crime;

    public static Expression[] projection = {crime.id, crime.shortDescription, crime.dateCommitted, crime.description, crime.releasedToPublic};

    @Override
    public SQLQuery getQuery(SQLQuery query) {
        query.from(crime);
        return query;
    }

    @Override
    public Crime map(Tuple tuple) {
        Crime result = new Crime();
        result.setId(tuple.get(crime.id));
        result.setShortDescription(tuple.get(crime.shortDescription));
        result.setDescription(tuple.get(crime.description));
        result.setDateCommitted(tuple.get(crime.dateCommitted));
        result.setReleasedToPublic(tuple.get(crime.releasedToPublic));
        result.setSuspects(profileDao.getSuspects(result.getId()));
        result.setVictims(profileDao.getVictims(result.getId()));

        return result;
    }
}
