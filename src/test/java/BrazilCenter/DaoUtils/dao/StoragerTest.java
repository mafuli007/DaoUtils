package BrazilCenter.DaoUtils.dao;

import java.io.File;
import java.io.IOException;

import BrazilCenter.models.FileObj;
import junit.framework.TestCase;

public class StoragerTest extends TestCase {

	public void testGetValue() {
		Storager storer;
			storer = new Storager();
			
			FileObj obj = new FileObj();
			obj.setName("FKT_DPS01_IIG_L31_STP_20150312000000.PNG");
			obj.setPath("C:"+File.separator + "BrazilTest"+File.separator + "ClientBackData");

		
	}
}
