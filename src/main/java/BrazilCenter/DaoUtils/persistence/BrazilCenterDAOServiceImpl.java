package BrazilCenter.DaoUtils.persistence;

import BrazilCenter.DaoUtils.persistence.impl.FKT_DPS01_IIG_L31_STP_IMPL;
import BrazilCenter.DaoUtils.persistence.impl.SZT_ISM01_DNP_L01_30M_IMPL;

/**
 * Implementation class of the ISSSOLAC DAO methods.
 * 
 */
public class BrazilCenterDAOServiceImpl extends AbstractDAOService implements BrailCenterDAOService {

	/**
	 * Spring DAO Configuration file.
	 */
	private static final String DAO_CONFIG_FILE = "spring-dao-config.xml";

	/**
	 * Constructor, it will initialize the spring context with the default
	 * spring config file.
	 */
	public BrazilCenterDAOServiceImpl() {
		super(DAO_CONFIG_FILE);
	}

	/**
	 * Constructor, it will initialize the spring context with a particular
	 * spring config file.
	 */
	public BrazilCenterDAOServiceImpl(String specificDaoConfigFile) {
		super(specificDaoConfigFile);
	}

	public IHibernateDAO getFTK_DPS01_IIG_L31_STP_DAO() {
		// TODO Auto-generated method stub
		return (FKT_DPS01_IIG_L31_STP_IMPL) this.appContext.getBean("FKT_DPS01_IIG_L31_STP_Dao");
	}
	
	public IHibernateDAO getSZT_ISM01_DNP_L01_30M_DA0(){
		return (SZT_ISM01_DNP_L01_30M_IMPL) this.appContext.getBean("SZT_ISM01_DNP_L01_30M_Dao");
	}
	
}
