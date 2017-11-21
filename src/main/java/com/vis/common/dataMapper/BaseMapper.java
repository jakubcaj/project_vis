package com.vis.common.dataMapper;

import com.mysema.query.Tuple;

import java.util.List;

public interface BaseMapper<T> {

    T map(Tuple tuple);
}
