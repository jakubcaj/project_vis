package com.vis.common.dao.impl;

import com.mysema.query.sql.*;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Path;
import com.vis.common.dao.UserDao;
import com.vis.common.dataMapper.impl.UserMapper;
import com.vis.common.domain.User;
import com.vis.common.dto.UserProcessAwaiting;
import com.vis.common.querydsl.dimension.QSQLUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Transactional
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    private static final QSQLUser user = QSQLUser.user;

    @Override
    public User getUserByUsername(String username) {
        UserMapper userMapper = new UserMapper();
        SQLQuery query = userMapper.getQuery(query());

        return getSingleResult(query, userMapper, UserMapper.projection);
    }

    @Override
    public User registerUser(UserProcessAwaiting userP) {
        getSqlInsertClause(user)
                .columns((Path<?>[]) user.getColumns().stream().filter(x -> !x.toString().equals("id")).toArray())
                .values(userP.getUsername(), userP.getEmail(), userP.getFirstName(), userP.getLastName(),
                        userP.getFirstName() + " " + userP.getLastName()).execute();
//        getSqlInsertClause(user).columns
        return null;
    }
}
