package BrazilCenter.DaoUtils.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicFileType {
	
	@Id
	@Column(name = "filename")
	private String fileName;
	
	@Column(name = "filepath")
	private String filePath;
	
	@Column(name = "filesize")
	private int fileSize;
	
	@Column(name = "dataTime")
	private String dataTime;
	
	
	@Column(name = "inTime")
	private Date inTime;


	public BasicFileType(String fileName, String filePath, int fileSize, String dataTime, Date inTime) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.dataTime = dataTime;
		this.inTime = inTime;
	}

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


	public int getFileSize() {
		return fileSize;
	}


	public void setFileSize(int fileSize) {
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

}
