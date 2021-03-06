package BrazilCenter.models;

import java.util.*;

/**
 * tranfser software configuration
 */
public class Configuration {

	private String softwareId;

	private int heartbeatInterval;
	private int errRecordScanInterval;

	private String MonitorServerIp;
	private int MonitorServerPort;

	private List<TcpServerObj> reUploadServers;

	private String ReportRootDir;

	private String transferSwitch;

	private String storeRootDir;

	private String ftpMountDirectory;

	private List<FtpServerAddress> addresslist;

	/** rabbitmq configuration */
	private String mqHostIp;
	private String mqUserName;
	private String mqUserPasswd;

	/** used to listen the reupload message. */
	private int ReupLoadServerPort;

	public Configuration() {
		this.addresslist = new LinkedList<FtpServerAddress>();
		this.transferSwitch = "yes";
		this.reUploadServers = new LinkedList<TcpServerObj>();

		/** default value. */
		this.heartbeatInterval = 5;
		this.errRecordScanInterval = 300;

	}

	public String getTransferSwitch() {
		return transferSwitch;
	}

	public void setTransferSwitch(String transferSwitch) {
		this.transferSwitch = transferSwitch;
	}

	public String getReportRootDir() {
		return ReportRootDir;
	}

	public void setReportRootDir(String reportRootDir) {
		ReportRootDir = reportRootDir;
	}

	public List<FtpServerAddress> getAddresslist() {
		return this.addresslist;
	}

	public void AddAddress(FtpServerAddress address) {
		this.addresslist.add(address);
	}

	public int getHeartbeatInterval() {
		return heartbeatInterval;
	}

	public int getErrRecordScanInterval() {
		return errRecordScanInterval;
	}

	public void setErrRecordScanInterval(int errRecordScanInterval) {
		this.errRecordScanInterval = errRecordScanInterval;
	}

	public void setHeartbeatInterval(int heartbeatInterval) {
		this.heartbeatInterval = heartbeatInterval;
	}

	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}

	public String getMonitorServerIp() {
		return MonitorServerIp;
	}

	public void setMonitorServerIp(String monitorServerIp) {
		MonitorServerIp = monitorServerIp;
	}

	public int getMonitorServerPort() {
		return MonitorServerPort;
	}

	public void setMonitorServerPort(int monitorServerPort) {
		MonitorServerPort = monitorServerPort;
	}

	public String getFtpMountDirectory() {
		return ftpMountDirectory;
	}

	public void setFtpMountDirectory(String ftpMountDirectory) {
		this.ftpMountDirectory = ftpMountDirectory;
	}

	public List<TcpServerObj> getReUploadServers() {
		return reUploadServers;
	}

	public void addReuploadServer(TcpServerObj obj) {
		this.reUploadServers.add(obj);
	}

	public String getStoreRootDir() {
		return storeRootDir;
	}

	public void setStoreRootDir(String storeRootDir) {
		this.storeRootDir = storeRootDir;
	}

	public int getReupLoadServerPort() {
		return ReupLoadServerPort;
	}

	public void setReupLoadServerPort(int reupLoadServerPort) {
		ReupLoadServerPort = reupLoadServerPort;
	}

	public String getMqHostIp() {
		return mqHostIp;
	}

	public void setMqHostIp(String mqHostIp) {
		this.mqHostIp = mqHostIp;
	}

	public String getMqUserName() {
		return mqUserName;
	}

	public void setMqUserName(String mqUserName) {
		this.mqUserName = mqUserName;
	}

	public String getMqUserPasswd() {
		return mqUserPasswd;
	}

	public void setMqUserPasswd(String mqUserPasswd) {
		this.mqUserPasswd = mqUserPasswd;
	}
}
