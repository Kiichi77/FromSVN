package cn.com.kxcomm.task.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;


/** 
 * DAO操作基类<br> 
 * 本DAO层实现了通用的数据操作 
 *  
 * @author zhaobd
 *  
 * @param <T> 
 *            POJO实体对象 
 * @param <ID> 
 *            ID 
 */ 
@Repository
@SuppressWarnings("unchecked")  
public class CommonDAO<T> extends  HibernateDaoSupport {  

	private static final Logger logger = Logger  
	.getLogger(CommonDAO.class);  

	protected Class<T>  entityClass;
	public CommonDAO(Class<T> entityClass){
		this.entityClass = entityClass;
		
	}
	
	public CommonDAO(){
		this.entityClass = getSuperClassGenricType(getClass(), 0);
	}
	
	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}
	
	/** 
	 * 保存指定实体类 
	 *  
	 * @param entityobj 
	 *            实体类 
	 */  
	public void save(T entity) {  
		try {  
			getHibernateTemplate().save(entity);  
			if (logger.isDebugEnabled()) {  
				logger.debug("保存实体类成功," + entity.getClass().getName());  
			}  
		} catch (RuntimeException e) {  
			logger.error("保存实体异常," + entity.getClass().getName(), e);  
			throw e;  
		}  
	}  

	/** 
	 * 删除指定实体 
	 *  
	 * @param entityobj 
	 *            实体类 
	 */  
	public void delete(T entity) {  
		try {  
			getHibernateTemplate().delete(entity);  
			if (logger.isDebugEnabled()) {  
				logger.debug("删除实体类成功," + entity.getClass().getName());  
			}  
		} catch (RuntimeException e) {  
			logger.error("删除实体异常", e);  
			throw e;  
		}  
	}  
	
	/** 
	 * 删除多个实体对象 
	 *  
	 * @param entityobj 
	 *            实体类 
	 */  
	public void deleteAll(Collection<T> entities) {  
		try {  
			this.getHibernateTemplate().deleteAll(entities);
			if (logger.isDebugEnabled()) {  
				logger.debug("删除实体类成功," + entities);  
			}  
		} catch (RuntimeException e) {  
			logger.error("删除实体异常", e);  
			throw e;  
		}  
	} 

	/** 
	 * 获取所有实体集合 
	 *  
	 * @param entityClass 
	 *            实体 
	 * @return 集合 
	 */  
	public List<T> findAll() {  
		try {  
			if (logger.isDebugEnabled()) {  
				logger.debug("获取所有实体集合：" + entityClass.getName());  
			}  
			return getHibernateTemplate().find("from " + entityClass.getName());  
		} catch (RuntimeException e) {  
			logger.error("查找指定实体集合异常，实体：" + entityClass.getName(), e);  
			throw e;  
		}  
	}  

	/** 
	 * 更新或保存指定实体 
	 *  
	 * @param entity 
	 *            实体类 
	 */  
	public void saveOrUpdate(T entity) {  
		try {  
			getHibernateTemplate().saveOrUpdate(entity);  
			if (logger.isDebugEnabled()) {  
				logger.debug("更新或者保存实体成功," + entity.getClass().getName());  
			}  
		} catch (RuntimeException e) {  
			logger.error("更新或保存实体异常", e);  
			throw e;  
		}  
	}    
	
	/** 
	 * 查找指定ID实体类对象 
	 *  
	 * @param entityClass 
	 *            实体Class 
	 * @param id 
	 *            实体ID 
	 * @return 实体对象 
	 */  
	public T findById(Serializable id) {  
		try {  
			if (logger.isDebugEnabled()) {  
				logger.debug("开始查找ID为" + id + "的实体：" + entityClass.getName());  
			}  
			return (T) getHibernateTemplate().get(entityClass, id);  
		} catch (RuntimeException e) {  
			logger.error("查找指定ID实体异常，ID：" + id, e);  
			throw e;  
		}  
	}

	/** 
	 * 查询指定HQL，并返回集合 
	 *  
	 * @param hql 
	 *            HQL语句 
	 * @param values 
	 *            可变的参数列表 
	 * @return 集合 
	 */  
	public List<?> find(String hql, Object... values) {  
		try {  
			if (logger.isDebugEnabled()) {  
				logger.debug("开始查询指定HQL语句," + hql);  
			}  
			return getHibernateTemplate().find(hql, values);  
		} catch (RuntimeException e) {  
			logger.error("查询指定HQL异常，HQL：" + hql, e);  
			throw e;  
		}  
	}  

	/** 
	 * 按照HQL语句查询唯一对象. 
	 *  
	 * @param hql 
	 *            HQL语句 
	 * @param values 
	 *            可变参数集合 
	 * @return OBJECT对象 
	 */  
	public Object findUnique(final String hql, final Object... values) {  
		try {  
			if (logger.isDebugEnabled()) {  
				logger.debug("开始查询返回唯一结果的HQL语句," + hql);  
			}  
			return getHibernateTemplate().execute(new HibernateCallback() {  
				public Object doInHibernate(Session s)  
				throws HibernateException, SQLException {  
					Query query = createQuery(s, hql, values);  
					return query.uniqueResult();  
				}  
			});  
		} catch (RuntimeException e) {  
			logger.error("查询指定HQL异常，HQL：" + hql, e);  
			throw e;  
		}  
	}  

	/** 
	 * 查找指定HQL并返回INT型 
	 *  
	 * @param hql 
	 *            HQL语句 
	 * @param values 
	 *            可变参数列表 
	 * @return INT 
	 */  
	public int findInt(final String hql, final Object... values) {
		String temp=findUnique(hql, values).toString();
		int returnint=Integer.parseInt(temp);
		return returnint;  
	}  

	/** 
	 * 获取指定实体Class指定条件的记录总数 
	 *  
	 * @param entityClass 
	 *            实体Class 
	 * @param where 
	 *            HQL的查询条件,支持参数列表 
	 * @param values 
	 *            可变参数列表 
	 * @return 记录总数 
	 */  
	public int findTotalCount(final String hql,  
			final Object... values) {  

		return findInt(hql, values);  
	}  

	
	/**
	 * 
	 * 根据查询条件获取符合条件的数据条数
	 * @author chenxinwei 新增日期：2012-1-22
	 * @param criteria
	 * @return
	 */
	public Integer countByCriteria(final DetachedCriteria criteria){
		Assert.notNull(criteria, "DetachedCriteria must not be null");
		Integer count = (Integer) this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Criteria c = criteria.getExecutableCriteria(session);
				return c.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
		return count;
	}

	/** 
	 * 查找指定属性的实体集合 
	 *  
	 * @param entityClass 
	 *            实体 
	 * @param propertyName 
	 *            属性名 
	 * @param value 
	 *            条件 
	 * @return 实体集合 
	 */  
	public List<T> findByProperty(Class<T> entityClass, String propertyName,  
			Object value) {  
		try {  
			if (logger.isDebugEnabled()) {  
				logger.debug("开始查找指定属性：" + propertyName + "为" + value + "的实体"  
						+ entityClass.getName());  
			}  
			String queryStr = "from " + entityClass.getName()  
			+ " as model where model." + propertyName + "=?";  
			return getHibernateTemplate().find(queryStr, value);  
		} catch (RuntimeException e) {  
			logger.error("查找指定条件实体集合异常，条件：" + propertyName, e);  
			throw e;  
		}  
	}  

	/** 
	 * 模糊查询指定条件对象集合 <br> 
	 * 用法：可以实例化一个空的T对象，需要查询某个字段，就set该字段的条件然后调用本方法<br> 
	 * 缺点：目前测试貌似只能支持String的模糊查询，虽然有办法重写，但没必要，其他用HQL<br> 
	 *  
	 * @param entity 
	 *            条件实体 
	 * @return 结合 
	 */  
	public List<T> findByExample(T entity) {  
		try {  
			List<T> results = getHibernateTemplate().findByExample(entity);  
			return results;  
		} catch (RuntimeException re) {  
			logger.error("查找指定条件实体集合异常", re);  
			throw re;  
		}  
	}  

	/** 
	 * 补充方法(未测) 据说可以无视session的状态持久化对象 
	 *  
	 * @param entity 
	 *            实体类 
	 * @return 持久后的实体类 
	 */  
	public T merge(T entity) {  
		try {  
			T result = (T) getHibernateTemplate().merge(entity);  
			return result;  
		} catch (RuntimeException re) {  
			logger.error("merge异常", re);  
			throw re;  
		}  
	}  

	/** 
	 * 清除实体的锁定状态<br> 
	 * 方法未测 
	 *  
	 * @param entity 
	 *            实体 
	 */  
	public void attachClean(T entity) {  
		try {  
			getHibernateTemplate().lock(entity, LockMode.NONE);  
		} catch (RuntimeException re) {  
			logger.error("实体解锁异常", re);  
			throw re;  
		}  
	
	}  
	
	/**
	 * 
	* 方法用途和描述: 
	* @param hql
	* @param p
	* @author chenliang 新增日期：2012-5-18
	* @author 你的姓名 修改日期：2012-5-18
	* @since gdgroup
	 */
	public int executeByHQL(final String hql,final Object ...p){
		return (Integer)super.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < p.length; i++) {
					if(p[i]  instanceof Long){
						Long params = Long.parseLong(p[i].toString());
						query.setLong(i, params);
					}
					else if(p[i]  instanceof Integer){
						Integer params = Integer.parseInt(p[i].toString());
						query.setInteger(i, params);
					}
                    else if(p[i]  instanceof String){
                    	query.setString(i, p[i].toString());
					}
                    else if(p[i]  instanceof Date){
                    	query.setTimestamp(i, (Date)p[i]);
                    }
					
				}
				return query.executeUpdate();
			}
			
		});
	}
	
	/** 
	 * 根据查询条件与参数列表创建Query对象 
	 *  
	 * @param session 
	 *            Hibernate会话 
	 * @param hql 
	 *            HQL语句 
	 * @param objects 
	 *            参数列表 
	 * @return Query对象 
	 */  
	public Query createQuery(Session session, String hql, Object... objects) {  
		Query query = session.createQuery(hql);  
		logger.info("objects:"+objects);
		int j = 0;
		if (objects != null) {  
			for (int i = 0; i < objects.length; i++) { 
				logger.info("objects["+i+"]"+objects[i]);
				if(objects[i] != null && !"".equals(objects[i])){
					query.setParameter(j, objects[i]);
					j++;
				}
			}  
		}  
		return query;  
	}  

	/** 
	 * 从Spring上下文中获取本类对象<br> 
	 * 此方法可能存在线程并发问题（待测） 
	 *  
	 * @param context 
	 *            Spring上下文 
	 * @return 本类对象 
	 */  
	public static CommonDAO<?> getFromApplicationContext(  
			WebApplicationContext context) {  
		return (CommonDAO<?>) context.getBean("BaseHibernateDAO");  
	}  

	/**
	 * 根据某字段=某值查询单个对象
	 * @author wukq 新增日期:2012-03-23
	 * @param fieldName 字段名
	 * @param fieldValue 字段值
	 * @return 单个对象 或 null
	 */
	public T findByField(String fieldName,Object fieldValue){
		Assert.notNull(fieldName, "fieldName must not be null");
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq(fieldName, fieldValue));
		return this.getByCriteria(criteria);
	}
	
	/**
	 * 
	 * 根据查询条件查询单个对象 
	 * @author chenxinwei 新增日期：2012-1-22
	 * @author wukq 修改日期:2012-03-23
	 * @param criteria
	 * @return
	 */
	public T getByCriteria(DetachedCriteria criteria){
		Assert.notNull(criteria, "DetachedCriteria must not be null");
		List<T> result = this.findByCriteria(criteria, 0, 1);
		if(result!=null && result.size()>0){
			return result.get(0);
		}else{
			return null;
		}		
	}
	
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		Assert.notNull(criteria, "DetachedCriteria must not be null");
		return this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}
	
}  

