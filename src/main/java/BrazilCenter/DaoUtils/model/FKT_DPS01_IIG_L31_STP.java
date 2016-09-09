package BrazilCenter.DaoUtils.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FKT_DPS01_IIG_L31_STP")
public class FKT_DPS01_IIG_L31_STP{

	@Id
	@Column(name = "filename")
	private String fileName;
	
	@Column(name = "filepath")
	private String filePath;
	
	@Column(name = "filesize")
	private long fileSize;
	
	@Column(name = "dataTime")
	private String dataTime;
	
	
	@Column(name = "inTime")
	private Date inTime;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public FKT_DPS01_IIG_L31_STP(){
		
	}

	@Override
	public String toString() {
		return "FKT_DPS01_IIG_L31_STP [fileName=" + fileName + ", filePath=" + filePath + ", fileSize=" + fileSize
				+ ", dataTime=" + dataTime + ", inTime=" + inTime + "]";
	}
}
