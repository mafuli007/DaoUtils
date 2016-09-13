package BrazilCenter.DaoUtils.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ServiceManager for providing DAO objects.
 * @author nfajersz
 */
public abstract class AbstractDAOService implements IDAOService {

   /**
    * Hibernate application context.
    */
   protected ApplicationContext appContext;

   /**
    * Commons-logging variable.
    */
   private static Log log = LogFactory.getLog(AbstractDAOService.class);

   /**
    * Constructor, it will initialize the spring context.
    * @param springDaoConfigFile Input spring dao config file.
    */
   public AbstractDAOService(final String springDaoConfigFile) {
      log.debug("Starting DAO Application Context...");
      if (springDaoConfigFile == null) {
         log.fatal("Spring DAO file cannot be null. "
               + "It must be set before getting an instance "
               + "of the DAOServiceManager");
         throw new IllegalStateException("Spring DAO file cannot be null. "
               + "It must be set before getting an instance "
               + "of the DAOServiceManager");
      } else {
         this.appContext = new ClassPathXmlApplicationContext(
               new String[] {springDaoConfigFile});
      }
   }

   /**
    * Retrieves sessionFactory bean.
    * @return Session factory spring bean
    */
   public final SessionFactory getSessionFactory() {
      log.debug("Retrieving Application context with "
            + "appContext.getBean('mySessionFactory')");
      return (SessionFactory) this.appContext
            .getBean("hibernateSessionFactory");
   }
   
}
