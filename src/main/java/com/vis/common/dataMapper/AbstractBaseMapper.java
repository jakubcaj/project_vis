package com.vis.common.dataMapper;

import com.mysema.query.sql.SQLQuery;

public abstract class AbstractBaseMapper {

public abstract SQLQuery getQuery(SQLQuery query);

}
