package com.vis.common.dataMapper.impl;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.vis.common.dataMapper.AbstractBaseMapper;
import com.vis.common.dataMapper.BaseMapper;
import com.vis.common.domain.User;
import com.vis.common.enums.Roles;
import com.vis.common.querydsl.dimension.QSQLUser;
import com.vis.common.querydsl.keystone.QSQLRole;
import com.vis.common.querydsl.keystone.QSQLUserRole;

public class UserMapper extends AbstractBaseMapper implements BaseMapper<User> {

    private static final QSQLUser user = QSQLUser.user;
    private static final QSQLUserRole userRole = QSQLUserRole.userRole;
    private static final QSQLRole role = QSQLRole.role1;

    public static Expression[] projection = {user.id, user.firstName, user.lastName, user.username, user.fullName, user.email, role.role};

    @Override
    public SQLQuery getQuery(SQLQuery query) {
        query.from(user)
            .join(userRole).on(userRole.dimensionUserId.eq(user.id))
            .join(role).on(role.id.eq(userRole.roleId));

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
        resultUser.setEmail(tuple.get(user.email));
        resultUser.setRole(Roles.getRole(tuple.get(role.role)));
        return resultUser;
    }

}
