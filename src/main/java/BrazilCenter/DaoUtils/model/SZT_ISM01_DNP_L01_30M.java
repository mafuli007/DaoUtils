package BrazilCenter.DaoUtils.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SZT_ISM01_DNP_L01_30M")
public class SZT_ISM01_DNP_L01_30M{

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

	public SZT_ISM01_DNP_L01_30M(){
		
	}

}
