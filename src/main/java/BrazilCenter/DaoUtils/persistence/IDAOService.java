package BrazilCenter.DaoUtils.persistence;

import org.hibernate.SessionFactory;

/**
 * Interface used to provide access to the general query DAO be used in all the
 * generic queries.
 * @author nfajersz
 */
public interface IDAOService {

   /**
    * Retrieves sessionFactory bean.
    * @return Session factory spring bean
    */
   SessionFactory getSessionFactory();

   /**
    * Returns IGenericDAO object.
    * @return IGenericDAO object
    */

}
