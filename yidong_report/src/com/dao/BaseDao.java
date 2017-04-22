package com.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.bean.Pager;

public interface BaseDao {

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
	public long querySqlCount(String queryString);
	public List<?> queryByParameter(String queryString,String paramName,Object paramValue);
	public List<?> queryByParameter(String queryString,Object value);
	public List<?> queryByParameter(String queryString,Object... values);
	public List<?> queryByparameter(String queryString,String[] paramNames,Object[] paramValues);
	public List<?> queryByParameter(Class<?> entityClass,int setfirstResult,int maxSize);
	public List<?> queryByParameter(String queryString,int setfirstResult,int maxSize);
	public List queryAll(String tableName);  
	/**
	 * 分页查询
	 * @param cls 实体类
	 * @param pager  分页实体类
	 * @param criterions   查询条件
	 * @return
	 */
	Pager  findPager(Class cls,Pager pager , Criterion  ...criterions);
	
	Pager findPagerBySql(Pager pager,String sql,String sqlCount);
}
