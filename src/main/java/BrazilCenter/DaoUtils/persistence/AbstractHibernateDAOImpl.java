package BrazilCenter.DaoUtils.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.hibernate.cache.spi.UpdateTimestampsCache;
*/import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/*import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.internal.SessionFactoryImpl;*/

import BrazilCenter.DaoUtils.Utils.LogUtils;
import BrazilCenter.DaoUtils.model.FKT_DPS01_IIG_L31_STP;


/**
 * Abstract class to define all common methods for DAOs. The only method that
 * they must implement is getPersistentClass (which is abstract).
 * @author ileon
 */
public abstract class AbstractHibernateDAOImpl implements IHibernateDAO {

   /**
    * Commons-LogUtils.loggerging variable.
    */

   /**
    * Hibernate (linked with Spring) session factory.
    */
//   private SessionFactory sessionFactory;
   private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
   /**
    * Set session factory.
    * @param sessionFactory Input hibernate session factory.
    */
/*   public final void setSessionFactory(final SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }*/

   /**
    * Get current hibernate session from session factory.
    * @return Current Hibernate session.
    */
   protected Session getCurrentSession() {
      return AbstractHibernateDAOImpl.sessionFactory.openSession();
   }

   /**
    * Returns a persistent Class object, used by Session and Criteria object.
    * @return Persistent class
    */
   public abstract Class<?> getPersistentClass();

   /**
    * Return the persistent instance of the given entity class with the given
    * identifier, or null if not found. The following method is making a call to
    * the Hibernate Session object, to perform a search by ID.
    * @param id the identifier of the persistent instance
    * @return the persistent instance, or null if not found
    */
   public Object findById(final Serializable id) {
      return this.getCurrentSession().get(getPersistentClass().getName(), id);
   }

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

   public final void insert(FKT_DPS01_IIG_L31_STP persistentObject) {
	   System.out.println(persistentObject.toString());
	   this.getCurrentSession().beginTransaction();
      this.getCurrentSession().save(persistentObject);
      this.getCurrentSession().getTransaction().commit();
   }
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

   /**
    * Find Instrument objects that match the values of the input example.
    * @param exampleObject Example object
    * @return List of objects that match the example object
    */
   public final List<?> findByExample(final Object exampleObject) {
      return findByExample(exampleObject, -1, 0);
   }

   /**
    * Find objects that match the values of the input example.
    * @param exampleObject Example object
    * @param maxResults Maximum number of objects returned
    * @return List of objects that match the example.
    */
   public final List<?> findByExample(final Object exampleObject,
         final int maxResults) {
      return findByExample(exampleObject, maxResults, 0);
   }

   /**
    * Find objects that match the values of the input example.
    * @param exampleObject Example object
    * @param maxResults Maximum number of objects returned
    * @param firstResult First result returned (discard previous ones)
    * @return List of objects that match the example.
    */
   public final List<?> findByExample(final Object exampleObject,
         final int maxResults, final int firstResult) {
      Criteria crit = this.getCurrentSession()
            .createCriteria(getPersistentClass());
      // Add to cache!!
      crit.setCacheable(true);
      Example example = Example.create(exampleObject);
      crit.add(example);
      if (maxResults != -1) {
         crit.setMaxResults(maxResults);
      }
      crit.setFirstResult(firstResult);
      return crit.list();
   }

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
    * Executes a criteria query against the database. Returns list of results.
    * This method is called when it is a joined query to overcome a known
    * Hibernate Criteria problem. (See
    * http://floledermann.bLogUtils.loggerspot.com/2007/10/solving
    * -hibernate-criterias-distinct.html). This method gets really complicated
    * because of the "Order By" clause.
    * @param criteriaQuery Detached criteria query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @return List of results
    */
 /*  public final List<?> executeDistinctJoinedCriteriaQuery(
         final DetachedCriteria criteriaQuery, final int maxResults,
         final int firstResult) {
      // Retrieve initial criteria
      CriteriaImpl idCriteria = (CriteriaImpl) criteriaQuery
            .getExecutableCriteria(this.getCurrentSession());
      // Add to cache!!
      idCriteria.setCacheable(true);

      // Make a copy of the criteria
      Criteria secondCriteria = this.getCurrentSession()
            .createCriteria(criteriaQuery.getAlias());
      // Add to cache!!
      secondCriteria.setCacheable(true);

      // Add group by to retrieve only the "distinct" id's
      ProjectionList groupByList = Projections.projectionList();
      // Copy Order by clauses from idCriteria to secondCriteria
      Iterator<?> orderByIterator = idCriteria.iterateOrderings();
      for (Iterator<?> iter = orderByIterator; iter.hasNext();) {
         OrderEntry orderEntry = (OrderEntry) iter.next();
         secondCriteria.addOrder(orderEntry.getOrder());
         // iter.remove();
         // Add order by elements to group by
         String orderPropertyName = cleanOrderPropertyString(orderEntry
               .toString());
         groupByList.add(Projections.groupProperty(orderPropertyName));
      }
      // Add id now
      groupByList.add(Projections.groupProperty(Projections.id().toString()));
      // Add all the group by
      idCriteria.setProjection(groupByList);
      if (maxResults != -1) {
         idCriteria.setMaxResults(maxResults);
      }
      idCriteria.setFirstResult(firstResult);
      List<Object> idResultList = idCriteria.list();

      // Execute second query with ID's, and retrieve the whole
      // objects.
      if (idResultList.size() > 0) {
         // This list can be a list of attributes or a list of
         // tuples...
         if (idResultList.get(0) instanceof Object[]) {
            List<Object> firstResultsList = new ArrayList<Object>(
                  idResultList.size());
            Iterator<Object> resultsIterator = idResultList.iterator();
            while (resultsIterator.hasNext()) {
               Object[] listElement = (Object[]) resultsIterator.next();
               // The first attribute is the ID...
               // firstResultsList.add(listElement[0]);
               // The last element is the ID...
               firstResultsList.add(listElement[listElement.length - 1]);
            }
            secondCriteria.add(Restrictions.in(Projections.id().toString(),
                                               firstResultsList));
         } else {
            secondCriteria.add(Restrictions.in(Projections.id().toString(),
                                               idResultList));
         }
      } else {
         return new ArrayList<Object>();
      }
      return secondCriteria.list();
   }
*/
   /**
    * Executes the count of a criteria query against the database. Returns
    * number of results. This method is called when it is a joined query to
    * overcome a known Hibernate Criteria problem. (See
    * http://floledermann.bLogUtils.loggerspot
    * .com/2007/10/solving-hibernate-criterias-distinct.html)
    * @param criteriaQuery Detached criteria query.
    * @return Number of results
    */
  /* public final Integer executeDistinctJoinedCountCriteriaQuery(
         final DetachedCriteria criteriaQuery) {
      // Retrieve initial criteria
      CriteriaImpl idCriteria = (CriteriaImpl) criteriaQuery
            .getExecutableCriteria(this.getCurrentSession());
      // Add to cache!!
      idCriteria.setCacheable(true);
      // Add projection to make it distinct and to retrieve only the
      // id's
      // idCriteria.setProjection(Projections.distinct(Projections.
      // projectionList().add(Projections.id())));
      // idCriteria.setProjection(Projections.groupProperty(
      // Projections.id().toString()));

      // /////////////////////////////////////////////////
      // Option 1 (using scroll)
      // /////////////////////////////////////////////////
      // LogUtils.loggerger.debug("About to use scrollable results...");
      // ScrollableResults results = idCriteria.scroll();
      // results.last();
      // int numberOfRows = results.getRowNumber() + 1;
      // LogUtils.loggerger.debug("Number of rows is [" + numberOfRows + "]");
      // results.close();
      // return new Integer(numberOfRows);
      // /////////////////////////////////////////////////
      // Option 2 (using projections)
      // /////////////////////////////////////////////////
      idCriteria.setProjection(Projections.countDistinct(Projections.id()
            .toString()));

      return convertObjectToInteger(idCriteria.list().get(0));
      // /////////////////////////////////////////////////
   }*/

   /**
    * Executes a criteria query against the database, and returns number of
    * results found..
    * @param criteriaQuery Detached criteria query.
    * @return Number of results
    */
   @Deprecated
 /*  public final Integer executeCountCriteriaQuery(
         final DetachedCriteria criteriaQuery) {
      Criteria crit = criteriaQuery.getExecutableCriteria(this
            .getCurrentSession());
      // Add to cache!!
      crit.setCacheable(true);

      // Option 1 (using scroll)
      // ScrollableResults results = crit.scroll();
      // results.last();
      // int numberOfRows = results.getRowNumber() + 1;
      // results.close();
      // return new Integer(numberOfRows);

      // Option 2 (using projections)
      crit.setProjection(Projections.rowCount());

      return convertObjectToInteger(crit.list().get(0));
   }
*/
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
    * Find Number of objects that match the values of the input example.
    * @param exampleObject Example object
    * @return Number of objects that match the example.
    */
   public final Integer findCountByExample(final Object exampleObject) {
      // session.beginTransaction();
      Criteria crit = this.getCurrentSession()
            .createCriteria(getPersistentClass());
      // Add to cache!!
      crit.setCacheable(true);
      crit.setProjection(Projections.rowCount());
      Example example = Example.create(exampleObject);
      crit.add(example);
      return convertObjectToInteger(crit.list().get(0));
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

   // ///////////////////////////////////////////////////////////////////////////////////////////////////////
   // ///////////////////////////////////////////////////////////////////////////////////////////////////////
   // CACHE METHODS
   // ///////////////////////////////////////////////////////////////////////////////////////////////////////
   // ///////////////////////////////////////////////////////////////////////////////////////////////////////

   /**
    * Clean hibernate cache for all entities.
    */
/*   public final void cleanAllHibernateEntityCache() {
      LogUtils.logger.debug("Into AbstractHibernateDAOImpl.cleanAllHibernateEntityCache()");
      if (this.getCurrentSession() == null) {
         LogUtils.logger.warn("Hibernate Session is NULL (not intialized yet?)");
      } else {
         // Retrieve SessionFactory
         SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) this
               .getCurrentSession().getSessionFactory();
         // Every persistent class extends java.lang.Object, so the
         // following call will return all persistent classes
         String[] persistentClasses = sessionFactoryImpl
               .getImplementors("java.lang.Object");
         for (String persistentClass : persistentClasses) {
            sessionFactoryImpl.evictEntity(persistentClass);
         }
      }
      LogUtils.logger.debug("End of AbstractHibernateDAOImpl.cleanAllHibernateEntityCache()");
   }*/

   /**
    * Clean hibernate cache for all entities.
    * @param persistentClass Target persistent class.
    */
/*   public final void cleanHibernateEntityCache(final Class<?> persistentClass) {
      LogUtils.logger.debug("Into AbstractHibernateDAOImpl.cleanHibernateEntityCache()");
      if (this.getCurrentSession() == null) {
         LogUtils.logger.warn("Hibernate Session is NULL (not intialized yet?)");
      } else {
         // Retrieve SessionFactory
         SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) this
               .getCurrentSession().getSessionFactory();
         // Remove entity cache
         sessionFactoryImpl.evict(persistentClass);
      }
      LogUtils.logger.debug("End of AbstractHibernateDAOImpl.cleanHibernateEntityCache()");
   }*/

   /**
    * Clean hibernate cache for all queries.
    */
/*   public final void cleanHibernateQueryCache() {
      LogUtils.logger.debug("Into AbstractHibernateDAOImpl.cleanHibernateQueryCache()");
      if (this.getCurrentSession() == null) {
         LogUtils.logger.warn("Hibernate Session is NULL (not intialized yet?)");
      } else {
         // Retrieve SessionFactory
         SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) this
               .getCurrentSession().getSessionFactory();
         // And now evict all queries
         sessionFactoryImpl.evictQueries();
      }
      LogUtils.logger.debug("End of AbstractHibernateDAOImpl.cleanHibernateQueryCache()");
   }*/

   /**
    * Clean hibernate update timestamps cache.
    */
/*   public final void cleanHibernateUpdateTimestampsCache() {
      LogUtils.logger.debug("Into AbstractHibernateDAOImpl."
            + "cleanHibernateUpdateTimestampsCache()");
      if (this.getCurrentSession() == null) {
         LogUtils.logger.warn("Hibernate Session is NULL (not intialized yet?)");
      } else {
         // Retrieve SessionFactory
         SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) this
               .getCurrentSession().getSessionFactory();
         // Retrieve timestamps cache
         UpdateTimestampsCache updateTimestampsCache = sessionFactoryImpl
               .getUpdateTimestampsCache();
         // Clean timestamps cache
         updateTimestampsCache.clear();
      }
      LogUtils.logger.debug("End of AbstractHibernateDAOImpl.cleanHibernateUpdateTimestampsCache()");
   }*/

   /**
    * Flush all pending saves, updates and deletes to the database.
    */
   public final void flushPendingActionsToDatabase() {
      LogUtils.logger.debug("Into AbstractHibernateDAOImpl.flushPendingActionsToDatabase()");
      this.getCurrentSession().flush();
      LogUtils.logger.debug("End of AbstractHibernateDAOImpl.flushPendingActionsToDatabase()");
   }
}
