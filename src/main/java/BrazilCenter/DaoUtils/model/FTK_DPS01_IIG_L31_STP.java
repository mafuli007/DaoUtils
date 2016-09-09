package BrazilCenter.DaoUtils.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FTK_DPS01_IIG_L31_STP")
public class FTK_DPS01_IIG_L31_STP extends BasicFileType{

	public FTK_DPS01_IIG_L31_STP(String fileName, String filePath, int fileSize, String dataTime, Date inTime) {
		super(fileName, filePath, fileSize, dataTime, inTime);
		// TODO Auto-generated constructor stub
	}
}
