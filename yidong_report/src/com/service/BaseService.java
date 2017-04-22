package com.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface BaseService extends Serializable {

	public int save(Object entity);
	public int delete(Object entity);
	public int delete(Object entity,String id);
	public int delete(Object entity,String[] ids);
	public int saveorupdate(Object entity);
	public int update(Object entity);
	public Object queryById(Class<?> entityClass,int id);
	public List<?> queryAll(Class<?> entityClass);
	public List<?> query(String queryString);
	public List<?> querysql(String queryString);
	public Query queryBy(String queryString);
	public Integer queryCount(String queryString);
	public List<?> queryByParameter(String queryString,String paramName,Object paramValue);
	public List<?> queryByParameter(String queryString,Object value);
	public List<?> queryByParameter(String queryString,Object... values);
	public List<?> queryByparameter(String queryString,String[] paramNames,Object[] paramValues);
	public List<?> queryByParameter(Class<?> entityClass,int setfirstResult,int maxSize);
	public List<?> queryByParameter(String queryString,int setfirstResult,int maxSize);
	public List queryAll(String tableName); 
	/**
	 * 原生sql 总条数
	 * @param queryString
	 * @return
	 */
	public Integer querySqlCount(String queryString);
}
