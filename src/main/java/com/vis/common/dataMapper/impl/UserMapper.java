package com.vis.common.dataMapper.impl;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.vis.common.dataMapper.AbstractBaseMapper;
import com.vis.common.dataMapper.BaseMapper;
import com.vis.common.domain.User;
import com.vis.common.querydsl.dimension.QSQLUser;

public class UserMapper extends AbstractBaseMapper implements BaseMapper<User> {

    private static final QSQLUser user = QSQLUser.user;

    public static Expression[] projection = {user.id, user.firstName, user.lastName, user.username, user.fullName};

    @Override
    public SQLQuery getQuery(SQLQuery query) {
        query.from(user);
        return query;
    }

    @Override
    public User map(Tuple tuple) {
        User resultUser = new User();
        resultUser.setId(tuple.get(user.id));
        resultUser.setFirstName(tuple.get(user.firstName));
        resultUser.setLastName(tuple.get(user.lastName));
        resultUser.setUsername(tuple.get(user.username));
        resultUser.setFullName(tuple.get(user.fullName));
        return resultUser;
    }

}
