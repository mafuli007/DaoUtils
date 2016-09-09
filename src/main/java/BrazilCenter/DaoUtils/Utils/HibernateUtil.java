package BrazilCenter.DaoUtils.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

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

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

/*	public static List queryBySql(String sql) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List<Object[]> list = session.createSQLQuery(sql).list();

		session.getTransaction().commit();
		return list;
	}
*/

	public static boolean isTableExist(String tableName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String querySql = "select * from information_schema.TABLES where table_schema='brazil_data_center' and table_name='"
					+ tableName + "';";
			SQLQuery query = session.createSQLQuery(querySql);
			List data = query.list();

			if (data.size() > 0) {
				return true;
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LogUtils.logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}
	
	public static boolean isFileExist(String tableName, String fileName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String querySql = "select * from " + tableName + " where filename='" + fileName + "'";
			SQLQuery query = session.createSQLQuery(querySql);
			List data = query.list();

			if (data.size() > 0) {
				return true;
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LogUtils.logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	public static int update(String sql) {
		int result = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();

			SQLQuery query = session.createSQLQuery(sql);
			result = query.executeUpdate();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LogUtils.logger.error(e.getMessage());
		} finally {
			session.close();
		}
		
		return result;
	}
}