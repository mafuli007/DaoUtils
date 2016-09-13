package BrazilCenter.DaoUtils.persistence;


/**
 * Interface to expose all methods for accessing all DAO objects.
 * @author Hector Perez
 * @author Beatriz Martinez - Copyright (c) 2016 - ESAC/ESA
 */
public interface BrailCenterDAOService extends IDAOService {


   /**
    * Return IArtifactTypeDAO object (INGESTION schema)
    * @return
    */
	IHibernateDAO getFTK_DPS01_IIG_L31_STP_DAO();

	IHibernateDAO getSZT_ISM01_DNP_L01_30M_DA0();
}
