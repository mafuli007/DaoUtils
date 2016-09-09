package BrazilCenter.models;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import BrazilCenter.HeartBeat.Utils.HeartBeatUtils;

/**
 * 20160314
 * */
public class HeartBeatObj {
	
	private String softwareid;	 
	private String localip;			 // ip address where the software has been deployed.
	private String recvtime;	 // the time when the object was created.
	private String lastingtime;	 // how long has the software started.
	private String hostname;	 // host name
		
	private Date startTime;
	
	public HeartBeatObj() {
		startTime = new Date();
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.localip = addr.getHostAddress();
		this.hostname = addr.getHostName();
	}
	
	public String getSoftwareid() {
		return softwareid;
	}
	public void setSoftwareid(String softwareid) {
		this.softwareid = softwareid;
	}

	public String getLocalip() {
		return localip;
	}
	public void setLocalip(String localip) {
		this.localip = localip;
	}
	public String getRecvtime() {
		return recvtime;
	}
	public void setRecvtime(String recvtime) {
		this.recvtime = recvtime;
	}
	public String getLastingtime() {
		return lastingtime;
	}
	public void setLastingtime(String lastingtime) {
		this.lastingtime = lastingtime;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	/** update lasting time */
	public void update(){
		//String date_str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		long date_now = (new Date()).getTime();
		long date_start = this.startTime.getTime();
		long diff = date_now - date_start; // 共计毫秒数
		this.lastingtime = HeartBeatUtils.formatTime(diff);
	}
	
	@Override
	public String toString() {
		return "HeartBeatObj [softwareid=" + softwareid + ", localip=" + localip + ", recvtime=" + recvtime
				+ ", lastingtime=" + lastingtime + ", hostname=" + hostname + "]";
	}

}
