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
}
