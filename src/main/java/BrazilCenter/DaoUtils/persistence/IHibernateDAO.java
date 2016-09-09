package BrazilCenter.DaoUtils.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * List of common DAO methods, to send global queries and criteria queries to
 * database.
 * @author ileon
 */
public interface IHibernateDAO {

   /**
    * Stores a persistent object into the database.
    * @param persistentObject Input persistent object.
    */
   void insert(final Object persistentObject);

   /**
    * Stores a persistent object into the database or updates it if it exists.
    * @param persistentObject Input persistent object.
    */
   void insertOrUpdate(final Object persistentObject);

   /**
    * Updates a persistent AccessLog class.
    * @param persistentObject Input persistent object.
    */
   void update(final Object persistentObject);

   /**
    * Deletes a persistent AccessLog class.
    * @param persistentObject Input persistent object.
    */
   void delete(final Object persistentObject);

   /**
    * Executes a criteria query against the databse. Returns list of results.
    * @param criteriaQuery Detached criteria query.
    * @return List of results
    */
   List<?> executeCriteriaQuery(DetachedCriteria criteriaQuery);

   /**
    * Executes a criteria query against the databse. Returns list of results.
    * @param criteriaQuery Detached criteria query.
    * @param maxResults Maximum number of results to be returned.
    * @return List of results
    */
   List<?> executeCriteriaQuery(final DetachedCriteria criteriaQuery,
         final int maxResults);

   /**
    * Executes a criteria query against the databse. Returns list of results.
    * @param criteriaQuery Detached criteria query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @return List of results
    */
   List<?> executeCriteriaQuery(final DetachedCriteria criteriaQuery,
         final int maxResults, final int firstResult);

   /**
    * Executes a hql query against the database. Returns list of results.
    * @param hqlQuery String with hql query.
    * @return List of results
    */
   List<?> executeHqlQuery(final String hqlQuery);

   /**
    * Executes a hql query against the database. Returns list of results.
    * @param hqlQuery String with hql query.
    * @param maxResults Maximum number of results to be returned.
    * @return List of results
    */
   List<?> executeHqlQuery(final String hqlQuery, final int maxResults);

   /**
    * Executes a hql query against the database. Returns list of results.
    * @param hqlQuery String with hql query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @return List of results
    */
   List<?> executeHqlQuery(final String hqlQuery, final int maxResults,
         final int firstResult);

   /**
    * Executes a criteria query against the database. Returns list of results.
    * This method is called when it is a joined query to overcome a known
    * Hibernate Criteria problem. (See
    * http://floledermann.blogspot.com/2007/10/solving
    * -hibernate-criterias-distinct.html)
    * @param criteriaQuery Detached criteria query.
    * @param maxResults Maximum number of results to be returned.
    * @param firstResult First result of the result set.
    * @return List of results
    */
   List<?> executeDistinctJoinedCriteriaQuery(
         final DetachedCriteria criteriaQuery, final int maxResults,
         final int firstResult);

   /**
    * Executes the count of a criteria query against the database. Returns
    * number of results. This method is called when it is a joined query to
    * overcome a known Hibernate Criteria problem. (See
    * http://floledermann.blogspot
    * .com/2007/10/solving-hibernate-criterias-distinct.html)
    * @param criteriaQuery Detached criteria query.
    * @return Number of results
    */
   Integer executeDistinctJoinedCountCriteriaQuery(
         final DetachedCriteria criteriaQuery);

   /**
    * Executes a criteria query against the database, and returns number of
    * results.
    * @param criteriaQuery Detached criteria query.
    * @return Number of results
    */
   Integer executeCountCriteriaQuery(final DetachedCriteria criteriaQuery);

   /**
    * Executes a hql query against the database, and returns number of results
    * found.
    * @param hqlQuery String with hql query.
    * @return Number of results
    */
   Integer executeCountHqlQuery(final String hqlQuery);

    /**
    * Flush all pending saves, updates and deletes to the database.
    */
   void flushPendingActionsToDatabase();
}
