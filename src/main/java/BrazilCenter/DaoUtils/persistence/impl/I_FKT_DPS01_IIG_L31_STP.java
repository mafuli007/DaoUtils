/**
 *
 */
package BrazilCenter.DaoUtils.persistence.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BrazilCenter.DaoUtils.persistence.IHibernateDAO;


/**
 * Interface to define methods used by FileDAO
 *
 * @author Hector Perez Copyright (c) 2016 European Space Agency.
 */
public interface I_FKT_DPS01_IIG_L31_STP extends IHibernateDAO {

   /**
    * Returns a File Class object, used by Session and Criteria
    *
    * @return File class
    */
   File getEmptyFile();

   /**
    * Query Files by OID
    *
    * @param oid
    *
    * @return File Class
    */
   File queryFileByOid(final Integer oid);

   /**
    * Query Files by the filename and extension (which is the unique constraint).
    * @param filename name of the file without extension
    * @param extension
    * @return File found or <code>null</code> if none found.
    */
   File queryFileByFilename(final String filename, final String extension);

   /**
    * Query Files by fileType and day in the year
    *
    * @param fileType
    * @param doy
    * @param year
    *
    * @return List<File>
    */
   List<File> queryFileByTypeAndDay(final String fileType, final Double doy, final Integer year);

   /**
    * Query for Days that contain files
    *
    * @param year
    *
    * @return ArrayList<Date>
    */
   ArrayList<Date> queryDaysWithFiles(final Integer year) throws Exception;

}
