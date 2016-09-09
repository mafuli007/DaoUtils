package BrazilCenter.DaoUtils.persistence;

import BrazilCenter.DaoUtils.persistence.impl.I_FKT_DPS01_IIG_L31_STP;

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
	I_FKT_DPS01_IIG_L31_STP getFTK_DPS01_IIG_L31_STP_DAO();

}
