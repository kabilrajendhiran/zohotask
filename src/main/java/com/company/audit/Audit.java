package com.company.audit;
import java.sql.Timestamp;

public class Audit {
	private String email;
	private String userAgent;
	private String ipAddr;
	private String activity;
	private Timestamp currentTimestamp;
	private boolean validity;
	
	public Audit()
	{
		
	}
	
	public Audit(String email, String userAgent, String ipAddr, String activity, Timestamp currentTimestamp, boolean validity) {
		this.email = email;
		this.userAgent = userAgent;
		this.ipAddr = ipAddr;
		this.activity = activity;
		this.currentTimestamp = currentTimestamp;
		this.validity = validity;
	}
	


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Timestamp getCurrentTimestamp() {
		return currentTimestamp;
	}
	public void setCurrentTimestamp(Timestamp currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}
	public boolean isValidity() {
		return validity;
	}
	public void setValidity(boolean validity) {
		this.validity = validity;
	}
	
	
	
}
