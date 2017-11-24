package com.vis.common.dao.impl;

//import com.mysema.query.Tuple;
import com.mysema.query.Tuple;
import com.mysema.query.sql.PostgresTemplates;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.vis.common.dataMapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Expression;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BaseDao {

    @Autowired
    @Qualifier("dataSource")
    DriverManagerDataSource dataSource;

    private static final SQLTemplates dialect = PostgresTemplates.builder().printSchema().quote().build();

    SQLQuery query() {
        try {
            Connection connection = dataSource.getConnection();
//            SQLTemplates dialect = PostgresTemplates.builder().printSchema().quote().build();
            return new SQLQuery(connection, dialect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    SQLInsertClause getSqlInsertClause(RelationalPath entity) {
        try {
            return new SQLInsertClause(dataSource.getConnection(), dialect, entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    SQLUpdateClause getSqlUpdateClause(RelationalPath entity) {
        try {
            return new SQLUpdateClause(dataSource.getConnection(), dialect, entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    <T> List<T> getResult(SQLQuery query, BaseMapper<T> mapper, Expression... expressions) {
        List<T> results = new ArrayList<>();
        for(Tuple tuple : query.list(expressions)) {
            results.add(mapper.map(tuple));
        }
        return results;
    }

    <T> T getSingleResult(SQLQuery query, BaseMapper<T> mapper, Expression... expressions) {
        List<Tuple> resultSet = query.list(expressions);
        if(!resultSet.isEmpty()) {
            return mapper.map(resultSet.get(0));
        } else {
            return null;
        }
    }
}
