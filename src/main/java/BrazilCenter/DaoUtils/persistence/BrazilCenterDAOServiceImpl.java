package BrazilCenter.DaoUtils.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import BrazilCenter.DaoUtils.persistence.impl.FKT_DPS01_IIG_L31_STP_IMPL;
import BrazilCenter.DaoUtils.persistence.impl.I_FKT_DPS01_IIG_L31_STP;

/**
 * Implementation class of the ISSSOLAC DAO methods.
 * 
 */
public class BrazilCenterDAOServiceImpl extends AbstractDAOService implements BrailCenterDAOService {

	/**
	 * Spring DAO Configuration file.
	 */
	private static final String DAO_CONFIG_FILE = "spring-dao-config.xml";

	private FKT_DPS01_IIG_L31_STP_IMPL FKT_DPS01_IIG_L31_STP_impl;
	/**
	 * Constructor, it will initialize the spring context with the default
	 * spring config file.
	 */
	public BrazilCenterDAOServiceImpl() {
		super(DAO_CONFIG_FILE);
		FKT_DPS01_IIG_L31_STP_impl = new FKT_DPS01_IIG_L31_STP_IMPL();
	}

	/**
	 * Constructor, it will initialize the spring context with a particular
	 * spring config file.
	 */
	public BrazilCenterDAOServiceImpl(String specificDaoConfigFile) {
		super(specificDaoConfigFile);
	}

	public I_FKT_DPS01_IIG_L31_STP getFTK_DPS01_IIG_L31_STP_DAO() {
		// TODO Auto-generated method stub
		return FKT_DPS01_IIG_L31_STP_impl;
	}

	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
