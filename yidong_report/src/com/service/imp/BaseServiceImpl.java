package com.service.imp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
 
import com.dao.BaseDao;
import com.service.BaseService;

@Transactional
public class BaseServiceImpl implements BaseService {

	
	private BaseDao basedao;
	 
	public BaseDao getBasedao() {
		return basedao;
	}

	public void setBasedao(BaseDao basedao) {
		this.basedao = basedao;
	}

	@Override
	@Transactional
	public int save(Object entity) {
		// TODO Auto-generated method stub
		return basedao.save(entity);
	}

	@Override
	@Transactional
	public int delete(Object entity) {
		return basedao.delete(entity);
	}

	@Override
	@Transactional
	public int delete(Object entity, String id) {
		// TODO Auto-generated method stub
		return basedao.delete(entity, id);
	}

	@Override
	@Transactional
	public int delete(Object entity, String[] ids) {
		return basedao.delete(entity, ids);
	}

	@Override
	@Transactional
	public int saveorupdate(Object entity) {
		// TODO Auto-generated method stub
		return basedao.saveorupdate(entity);
	}

	@Override
	@Transactional
	public int update(Object entity) {
		// TODO Auto-generated method stub
		return basedao.update(entity);
	}

	@Override
	public Object queryById(Class<?> entityClass, int id) {
		// TODO Auto-generated method stub
		return basedao.queryById(entityClass, id);
	}

	@Override
	public List<?> queryAll(Class<?> entityClass) {
		// TODO Auto-generated method stub
		return basedao.queryAll(entityClass);
	}

	@Override
	public List<?> query(String queryString) {
		// TODO Auto-generated method stub
		return basedao.query(queryString);
	}

	@Override
	public List<?> querysql(String queryString) {
		// TODO Auto-generated method stub
		return basedao.querysql(queryString);
	}

	@Override
	public Query queryBy(String queryString) {
		// TODO Auto-generated method stub
		return basedao.queryBy(queryString);
	}

	@Override
	public Integer queryCount(String queryString) {
		// TODO Auto-generated method stub
		return basedao.queryCount(queryString);
	}

	@Override
	public List<?> queryByParameter(String queryString, String paramName,
			Object paramValue) {
		// TODO Auto-generated method stub
		return basedao.queryByParameter(queryString, paramName, paramValue);
	}

	@Override
	public List<?> queryByParameter(String queryString, Object value) {
		// TODO Auto-generated method stub
		return basedao.queryByParameter(queryString, value);
	}

	@Override
	public List<?> queryByParameter(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return basedao.queryByParameter(queryString, values);
	}

	@Override
	public List<?> queryByparameter(String queryString, String[] paramNames,
			Object[] paramValues) {
		// TODO Auto-generated method stub
		return basedao.queryByparameter(queryString, paramNames, paramValues);
	}

	@Override
	public List<?> queryByParameter(Class<?> entityClass, int setfirstResult,
			int maxSize) {
		// TODO Auto-generated method stub
		return basedao.queryByParameter(entityClass, setfirstResult, maxSize);
	}

	@Override
	public List<?> queryByParameter(String queryString, int setfirstResult,
			int maxSize) {
		// TODO Auto-generated method stub
		return basedao.queryByParameter(queryString, setfirstResult, maxSize);
	}

	@Override
	public List queryAll(String tableName) {
		// TODO Auto-generated method stub
		return basedao.queryAll(tableName);
	}

	public Integer querySqlCount(String queryString) {
		return (int) basedao.querySqlCount(queryString);
	}
 
}
