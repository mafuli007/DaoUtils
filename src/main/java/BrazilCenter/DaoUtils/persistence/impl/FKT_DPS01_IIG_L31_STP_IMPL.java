package BrazilCenter.DaoUtils.persistence.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import BrazilCenter.DaoUtils.persistence.AbstractHibernateDAOImpl;

public class FKT_DPS01_IIG_L31_STP_IMPL extends AbstractHibernateDAOImpl implements I_FKT_DPS01_IIG_L31_STP{

	@Override
	public Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return FKT_DPS01_IIG_L31_STP_IMPL.class;
	}

	public File getEmptyFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public File queryFileByOid(Integer oid) {
		// TODO Auto-generated method stub
		return null;
	}

	public File queryFileByFilename(String filename, String extension) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<File> queryFileByTypeAndDay(String fileType, Double doy, Integer year) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Date> queryDaysWithFiles(Integer year) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> executeDistinctJoinedCriteriaQuery(DetachedCriteria criteriaQuery, int maxResults, int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer executeDistinctJoinedCountCriteriaQuery(DetachedCriteria criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer executeCountCriteriaQuery(DetachedCriteria criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Object persistentObject) {
		// TODO Auto-generated method stub
		   Session session = this.getCurrentSession();
		   session.beginTransaction();
		   session.save(persistentObject);
		   session.getTransaction().commit();
	}

	public void insertOrUpdate(Object persistentObject) {
		// TODO Auto-generated method stub
		   Session session = this.getCurrentSession();
		   session.beginTransaction();
		   session.saveOrUpdate(persistentObject);
		   session.getTransaction().commit();
		   session.close();
	}

	
}
