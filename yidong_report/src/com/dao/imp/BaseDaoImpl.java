package com.dao.imp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Pager;
import com.dao.BaseDao;
import com.entity.Customer;

@Transactional
@Component
public class BaseDaoImpl implements BaseDao {

	/**
	 * 自动注入SessionFactory
	 * 
	 * @param sessionfactory
	 */
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public int delete(Object entity) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			getSession().delete(entity);
			flag = 1;
		} catch (RuntimeException re) {
			throw re;
		}
		return flag;
	}

	@Transactional
	public int delete(Object entity, String id) {
		int flag = 0;
		try {

			entity = getSession().load(entity.getClass(), id);
			getSession().delete(entity);
			flag = 1;

		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		}
		return flag;

	}

	@Transactional
	public int delete(Object entity, String[] ids) {
		int flag = 0;
		try {

			for (String id : ids) {
				entity = getSession().load(entity.getClass(),
						Integer.parseInt(id));
				getSession().delete(entity);
			}
			flag = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Object queryById(Class<?> entityClass, int id) {
		// TODO Auto-generated method stub

		try {
			Object instance = getSession().get(entityClass.getName(), id);
			return instance;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Transactional
	public int save(Object entity) {
		// TODO Auto-generated method stub
		int flag = 0;

		try {
			getSession().save(entity);
			flag = 1;

		} catch (RuntimeException re) {

			throw re;
		}
		return flag;
	}

	@Transactional
	public int saveorupdate(Object entity) {
		// TODO Auto-generated method stub
		int flag = 0;

		try {
			getSession().saveOrUpdate(entity);
			flag = 1;

		} catch (RuntimeException re) {

			throw re;
		}
		return flag;
	}

	@Transactional
	public int update(Object entity) {
		int flag = 0;

		try {
			getSession().update(entity);
			flag = 1;

		} catch (RuntimeException re) {

			throw re;
		}
		return flag;
	}

	public List queryAll(Class<?> entityClass) {
		// TODO Auto-generated method stub

		try {
			String queryString = "from  " + entityClass.getClass().getName();
			return getSession().createQuery(queryString).list();

		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List queryByParameter(String queryString, String paramName,
			Object paramValue) {

		try {
			Query query = getSession().createQuery(queryString);
			query.setParameter(paramName, paramValue);
			return query.list();
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List queryByParameter(String queryString, Object value) {
		// TODO Auto-generated method stub

		try {
			Query query = getSession().createQuery(queryString);
			query.setParameter(0, value);
			return query.list();
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List queryByParameter(String queryString, Object... values) {

		// TODO Auto-generated method stub

		try {
			Query query = getSession().createQuery(queryString);
			query.setParameterList(null, values);
			return query.list();
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List queryByparameter(String queryString, String[] paramNames,
			Object[] paramValues) {
		// / TODO Auto-generated method stub

		try {
			Query query = getSession().createQuery(queryString);
			for (int i = 0; i < paramNames.length; i++) {
				query.setParameter(paramNames[i], paramValues[i]);
			}
			return query.list();
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List query(String queryString) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		try {
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {

			throw re;
		}
	}

	 

	public Integer queryCount(String queryString) {
		try {
			return (Integer) getSession().createQuery(queryString)
					.uniqueResult();
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List<?> querysql(String queryString) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			return getSession().createSQLQuery(queryString).list();

		} catch (RuntimeException re) {
			throw re;
		}

	}

	public List queryByParameter(Class<?> entityClass, int setfirstResult,
			int maxSize) {
		// TODO Auto-generated method stub

		try {
			Session session = getSession();
			return session.createQuery("from  " + entityClass.getName())
					.setFirstResult((setfirstResult - 1) * maxSize)
					.setMaxResults(maxSize).list();

		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List queryByParameter(String queryString, int setfirstResult,
			int maxSize) {
		// TODO Auto-generated method stub

		try {
			/*
			 * Session session=getSession(); int
			 * start=(setfirstResult-1)*maxSize; Query query =
			 * session.createQuery(queryString); query
			 * .setFirstResult(start).setMaxResults(maxSize); List list =
			 * query.list(); return list;
			 */
			return getSession().createSQLQuery(queryString)
					.setFirstResult((setfirstResult - 1) * maxSize)
					.setMaxResults(maxSize).list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	public Query queryBy(String queryString) {
		// TODO Auto-generated method stub
		return this.getSession().createQuery(queryString);

	}

	@Override
	public List queryAll(String tableName) {
		// TODO Auto-generated method stub

		List all = new ArrayList();
		try {
			String hql = "from " + tableName;
			Query q = getSession().createQuery(hql);
			all = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return all;
	}

	/**
	 * 获取泛型中传入的实体类
	 * 
	 * @return
	 */
	public Class getcls() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			return (Class) parameterizedType[0];
		}
		return null;

	}

	@Override
	public long querySqlCount(String queryString) {

		try {
			return Long.parseLong((String.valueOf(getSession().createSQLQuery(
					queryString).uniqueResult())));
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public Pager findPager(Class cls, Pager pager, Criterion... criterions) {
		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();

		Session session = getSession();
		Criteria criteria = session.createCriteria(cls);

		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}

		pager.setTotalCount(criteriaResultTotalCount(criteria));

		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pager.getPageSize());

		pager.setResult(criteria.list());
		return pager;
	}

	private int criteriaResultTotalCount(Criteria criteria) {
		int criteriaResultTotalCount = 0;
		try {
			CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;

			Projection projection = criteriaImpl.getProjection();
			ResultTransformer resultTransformer = criteriaImpl
					.getResultTransformer();

			Integer totalCount = ((Long) criteriaImpl.setProjection(
					Projections.rowCount()).uniqueResult()).intValue();
			if (totalCount != null) {
				criteriaResultTotalCount = totalCount;
			}

			criteriaImpl.setProjection(projection);
			if (projection == null) {
				criteriaImpl
						.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			}
			if (resultTransformer != null) {
				criteriaImpl.setResultTransformer(resultTransformer);
			}

		} catch (Exception e) {

		}
		return criteriaResultTotalCount;
	}

	@Override
	public Pager findPagerBySql(Pager pager, String sql, String sqlCount) {
		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		List<?> list = new ArrayList();
		int ResultTotalCount = 0;
		try {
			list = getSession().createSQLQuery(sql)
					.setFirstResult((pageNumber - 1) * pageSize)
					.setMaxResults(pageSize).list();
			Long count = querySqlCount(sqlCount);
			ResultTotalCount = count.intValue();

		} catch (RuntimeException re) {
			throw re;
		}
		pager.setTotalCount(ResultTotalCount);
		pager.setResult(list);
		return pager;
	}

}
