package com.vis.common.dao.impl;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.sql.*;
import com.mysema.query.types.expr.BooleanExpression;
import com.vis.common.dao.UserDao;
import com.vis.common.dataMapper.impl.UserMapper;
import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.querydsl.dimension.QSQLUser;
import com.vis.common.querydsl.keystone.QSQLRole;
import com.vis.common.querydsl.keystone.QSQLUserAuth;
import com.vis.common.querydsl.keystone.QSQLUserRole;
import org.springframework.security.core.userdetails.memory.UserMap;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Override
    public User updateUser(UserProcessAwaiting userProcessAwaiting, Long id) {

        getSqlUpdateClause(user)
                .set(user.username, userProcessAwaiting.getUsername())
                .set(user.firstName, userProcessAwaiting.getFirstName())
                .set(user.lastName, userProcessAwaiting.getLastName())
                .set(user.email, userProcessAwaiting.getEmail())
                .where(user.id.eq(id)).execute();

        return getUserById(id);
    }

    @Override
    public List<User> getUsers(UserProcessAwaiting userProcessAwaiting) {
        UserMapper userMapper = new UserMapper();
        SQLQuery query = userMapper.getQuery(query());
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(!userProcessAwaiting.getFirstName().equals("")) {
            booleanBuilder.and(user.firstName.eq(userProcessAwaiting.getFirstName()));
        }
        if(!userProcessAwaiting.getLastName().equals("")) {
            booleanBuilder.and(user.lastName.eq(userProcessAwaiting.getLastName()));
        }
        if(!userProcessAwaiting.getEmail().equals("")) {
            booleanBuilder.and(user.email.eq(userProcessAwaiting.getEmail()));
        }
        if(!userProcessAwaiting.getUsername().equals("")) {
            booleanBuilder.and(user.username.eq(userProcessAwaiting.getUsername()));
        }

        query.where(booleanBuilder);

        return getResult(query, userMapper, UserMapper.projection);
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
