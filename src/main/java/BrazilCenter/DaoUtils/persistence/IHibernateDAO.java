package BrazilCenter.DaoUtils.persistence;


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
   
}
