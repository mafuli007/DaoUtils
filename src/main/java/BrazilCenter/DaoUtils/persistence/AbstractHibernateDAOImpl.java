package BrazilCenter.DaoUtils.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import BrazilCenter.DaoUtils.Utils.LogUtils;


/**
 * Abstract class to define all common methods for DAOs. The only method that
 * they must implement is getPersistentClass (which is abstract).
 * @author ileon
 */
public abstract class AbstractHibernateDAOImpl implements IHibernateDAO {


   /**
    * Hibernate (linked with Spring) session factory.
    */
   private SessionFactory sessionFactory;

   /**
    * Set session factory.
    * @param sessionFactory Input hibernate session factory.
    */
   public final void setSessionFactory(final SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   /**
    * Get current hibernate session from session factory.
    * @return Current Hibernate session.
    */
   protected Session getCurrentSession() {
      return this.sessionFactory.openSession();
   }

   /**
    * Returns a persistent Class object, used by Session and Criteria object.
    * @return Persistent class
    */
   public abstract Class<?> getPersistentClass();

   /**
    * Stores a persistent object into the database.
    * @param persistentObject Input persistent object.
    */
/*   public final void insert(final Object persistentObject) {
	   this.getCurrentSession().beginTransaction();

      this.getCurrentSession().save(getPersistentClass().getName(),
                                    persistentObject);
      this.getCurrentSession().getTransaction().commit();
   }*/

	public void insert(Object persistentObject) {
		// TODO Auto-generated method stub
		   Session session = this.getCurrentSession();
		   session.beginTransaction();
		   session.save(getPersistentClass().getName(), persistentObject);
		   session.getTransaction().commit();
	}

	public void insertOrUpdate(Object persistentObject) {
		// TODO Auto-generated method stub
		   Session session = this.getCurrentSession();
		   session.beginTransaction();
		   session.saveOrUpdate(getPersistentClass().getName(), persistentObject);
		   session.getTransaction().commit();
		   session.close();
	}
	
/*   public final void insert(FKT_DPS01_IIG_L31_STP persistentObject) {
	   System.out.println(persistentObject.toString());
	   this.getCurrentSession().beginTransaction();
      this.getCurrentSession().save(persistentObject);
      this.getCurrentSession().getTransaction().commit();
   }*/
	
   /**
    * Stores a persistent object into the database or updates it if it exists.
    * @param persistentObject Input persistent object.
    */
/*   public final void insertOrUpdate(final Object persistentObject) {
      this.getCurrentSession().saveOrUpdate(getPersistentClass().getName(),
                                            persistentObject);
   }*/

   /**
    * Updates a persistent AccessLogUtils.logger class.
    * @param persistentObject Input persistent object.
    */
   public final void update(final Object persistentObject) {
      this.getCurrentSession().update(getPersistentClass().getName(),
                                      persistentObject);
   }

   /**
    * Deletes a persistent AccessLogUtils.logger class.
    * @param persistentObject Input persistent object.
    */
   public final void delete(final Object persistentObject) {
      this.getCurrentSession().delete(getPersistentClass().getName(),
                                      persistentObject);
   }

   
   /***************************** ######### query ############ ***********************/
   
   /**
    * Executes a criteria query against the database. Returns list of results.
    * @param criteriaQuery Detached criteria query.
    * @return List of results
    */
   public final List<?> executeCriteriaQuery(
         final DetachedCriteria criteriaQuery) {
      return executeCriteriaQuery(criteriaQuery, -1, 0);
   }

   /**
    * Executes a hql query against the database. Returns list of results.
    * @param hqlQuery String with hql query.
    * @return List of results
    */
   public final List<?> executeHqlQuery(final String hqlQuery) {
      return executeHqlQuery(hqlQuery, -1, 0);
   }

   /**
    * Executes a criteria query against the database. Returns list of results.
    * @param criteriaQuery Detached criteria query.
    * @param maxResults Maximum number of results to be returned.
    * @return List of results
    */
   public final List<?> executeCriteriaQuery(
         final DetachedCriteria criteriaQuery, final int maxResults) {
      return executeCriteriaQuery(criteriaQuery, maxResults, 0);
   }

   /**
    * Executes a hql query against the database. Returns list of results.
    * @param hqlQuery String with hql query.
    * @param maxResults Maximum number of results to be returned.
    * @return List of results
    */
   public final List<?> executeHqlQuery(final String hqlQuery,
         final int maxResults) {
      return executeHqlQuery(hqlQuery, maxResults, 0);
   }

   /**
    * Executes a criteria query against the database. Returns list of results.
    * @param criteriaQuery Detached criteria query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @return List of results
    */
   public final List<?> executeCriteriaQuery(
         final DetachedCriteria criteriaQuery, final int maxResults,
         final int firstResult) {
      Criteria crit = criteriaQuery.getExecutableCriteria(this
            .getCurrentSession());
      // Add to cache!!
      crit.setCacheable(true);
      if (maxResults != -1) {
         crit.setMaxResults(maxResults);
      }
      crit.setFirstResult(firstResult);
      return crit.list();
   }

   /**
    * Executes a hql query against the database. Returns list of results.
    * @param hqlQuery String with hql query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @return List of results
    */
   public final List<?> executeHqlQuery(final String hqlQuery,
         final int maxResults, final int firstResult) {
      Query query = this.getCurrentSession().createQuery(hqlQuery);
      // Add to cache!!
      query.setCacheable(true);
      if (maxResults != -1) {
         query.setMaxResults(maxResults);
      }
      query.setFirstResult(firstResult);
      return query.list();
   }

   /**
    * Executes a hql query against the database, and returns number of results
    * found.
    * @param hqlQuery String with hql query.
    * @return Number of results
    */
   public final Integer executeCountHqlQuery(final String hqlQuery) {
      Query query = this.getCurrentSession().createQuery(hqlQuery);
      // Add to cache!!
      query.setCacheable(true);

      // Option 1 (using scroll)
      ScrollableResults results = query.scroll();
      results.last();
      int numberOfRows = results.getRowNumber() + 1;
      results.close();
      return new Integer(numberOfRows);
      // // Option 2 (using projections)
      // crit.setProjection(Projections.rowCount());
      //
      // return convertObjectToInteger(crit.list().get(0));
   }



   /**
    * When executing a count(*), postgres return Long objects, and we need to
    * convert them to Integer.
    * @param inputValue Input object (normally a long or an integer)
    * @return Integer value.
    */
   final Integer convertObjectToInteger(final Object inputValue) {
      if (inputValue instanceof Long) {
         Long longResult = (Long) inputValue;
         Integer integerResult = new Integer(longResult.intValue());
         return integerResult;
      }
      return (Integer) inputValue;
   }

   /**
    * The only way to know what is the property name of a Hibernate Order
    * property is to convert it to string and parse it... The string always look
    * like "<<column-name>> asc" or <<column-name>> desc.
    * @param orderEntry Input string.
    * @return column name.
    */
   final String cleanOrderPropertyString(final String orderEntry) {
      if (orderEntry == null) {
         return null;
      }
      String columnName = orderEntry;
      columnName = columnName.replace(" asc", "");
      columnName = columnName.replace(" desc", "");
      columnName = columnName.trim();
      return columnName;
   }

   /**
    * Executes a sql query against the database. Returns list of results.
    * @param sqlQuery String with sql query.
    * @return List of results
    */
   public final List<?> executeSqlQuery(final String sqlQuery) {
      return executeSqlQuery(sqlQuery, -1, 0, true);
   }

   /**
    * Executes a sql query against the database. Returns list of results.
    * @param sqlQuery String with sql query.
    * @param cacheable Add query results to cache.
    * @return List of results
    */
   public final List<?> executeSqlQuery(final String sqlQuery,
         final boolean cacheable) {
      return executeSqlQuery(sqlQuery, -1, 0, cacheable);
   }

   /**
    * Executes a sql query against the database. Returns list of results.
    * @param sqlQuery String with sql query.
    * @param maxResults Maximum number of results to be returned.
    * @return List of results
    */
   public final List<?> executeSqlQuery(final String sqlQuery,
         final int maxResults) {
      return executeSqlQuery(sqlQuery, maxResults, 0, true);
   }

   /**
    * Executes a sql query against the database. Returns list of results.
    * @param sqlQuery String with sql query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @param cacheable Add query results to cache.
    * @return List of results
    */
   public final List<?> executeSqlQuery(final String sqlQuery,
         final int maxResults, final int firstResult, final boolean cacheable) {
      SQLQuery query = this.getCurrentSession().createSQLQuery(sqlQuery);
      // Add to cache!!
      if (cacheable) {
         query.setCacheable(true);
      }
      if (maxResults != -1) {
         query.setMaxResults(maxResults);
      }
      if (firstResult != 0) {
         query.setFirstResult(firstResult);
      }
      return query.list();
   }

   /**
    * Flush all pending saves, updates and deletes to the database.
    */
   public final void flushPendingActionsToDatabase() {
      LogUtils.logger.debug("Into AbstractHibernateDAOImpl.flushPendingActionsToDatabase()");
      this.getCurrentSession().flush();
      LogUtils.logger.debug("End of AbstractHibernateDAOImpl.flushPendingActionsToDatabase()");
   }
  
   
}
