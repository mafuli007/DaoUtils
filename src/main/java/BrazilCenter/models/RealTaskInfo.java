package BrazilCenter.models;

/**
 * task info that used to monitor what's happening in the software. */
public class RealTaskInfo {

	private String softwareId; 	// source software id
	private String targetSoftwareId;  // target software id
	private String startTime;  // begin time.
	private String endTime;  // end time.
	private String fileName; 
	private String result; 
	private long fileSize; 
	private String failReason;	 
	private String sourceAddress; // file's source address. 

	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}

	public String getTargetSoftwareId() {
		return targetSoftwareId;
	}

	public void setTargetSoftwareId(String targetSoftwareId) {
		this.targetSoftwareId = targetSoftwareId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	@Override
	public String toString() {
		return "RealTaskInfo [softwareId=" + softwareId + ", targetSoftwareId=" + targetSoftwareId + ", startTime="
				+ startTime + ", endTime=" + endTime + ", fileName=" + fileName + ", result=" + result + ", fileSize="
				+ fileSize + ", failReason=" + failReason + ", sourceAddress=" + sourceAddress + "]";
	}
}
