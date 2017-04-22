package com.dao;

import com.entity.Operator;

public interface OperatorDao extends BaseDao {

	public Operator login(Operator operator);
}
