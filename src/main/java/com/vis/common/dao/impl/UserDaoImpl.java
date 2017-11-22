package com.vis.common.dao.impl;

import com.mysema.query.sql.*;
import com.vis.common.dao.UserDao;
import com.vis.common.dataMapper.impl.UserMapper;
import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.querydsl.dimension.QSQLUser;
import com.vis.common.querydsl.keystone.QSQLRole;
import com.vis.common.querydsl.keystone.QSQLUserAuth;
import com.vis.common.querydsl.keystone.QSQLUserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    private static final QSQLUser user = QSQLUser.user;
    private static final QSQLUserAuth userAuth = QSQLUserAuth.userAuth;
    private static final QSQLUserRole userRole = QSQLUserRole.userRole;
    private static final QSQLRole role = QSQLRole.role1;

    @Override
    public User getUserByUsername(String username) {
        UserMapper userMapper = new UserMapper();
        SQLQuery query = userMapper.getQuery(query())
                .where(user.username.eq(username));

        return getSingleResult(query, userMapper, UserMapper.projection);
    }

    @Override
    public User getUserById(Long id) {
        UserMapper userMapper = new UserMapper();
        SQLQuery query = userMapper.getQuery(query())
                .where(user.id.eq(id));

        return getSingleResult(query, userMapper, UserMapper.projection);
    }

    @Override
    public User registerUser(UserProcessAwaiting userP) {
        Long id = getSqlInsertClause(user)
                .columns(user.username, user.email, user.firstName, user.lastName, user.fullName)
                .values(userP.getUsername(), userP.getEmail(), userP.getFirstName(), userP.getLastName(),
                        userP.getFirstName() + " " + userP.getLastName()).executeWithKey(user.id);

        registerUserAuth(id, userP);
        assignUserRole(id, "ROLE_USER");
        return getUserById(id);
    }

    private void registerUserAuth(Long id, UserProcessAwaiting userP) {
        getSqlInsertClause(userAuth)
                .columns(userAuth.dimensionUserId, userAuth.username, userAuth.password)
                .values(id, userP.getUsername(), userP.getPassword()).execute();
    }

    private void assignUserRole(Long userId, String roleName) {
        getSqlInsertClause(userRole)
                .columns(userRole.dimensionUserId, userRole.roleId)
                .values(userId, getRoleId(roleName)).execute();
    }

    private Long getRoleId(String name) {
        SQLQuery query = query().from(role)
                .where(role.role.eq(name));

        return getSingleResult(query, (tuple -> tuple.get(role.id)),role.id);
    }

}
