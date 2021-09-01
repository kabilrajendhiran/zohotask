package com.company.audit;
import java.sql.Timestamp;

public class Audit {
	private String email;
	private String userAgent;
	private String browser;
	private String device;
	private String os;
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


	public Audit(String email, String userAgent, String browser, String device, String os, String ipAddr, String activity, Timestamp currentTimestamp, boolean validity) {
		
		this.email = email;
		this.userAgent = userAgent;
		this.browser = browser;
		this.device = device;
		this.os = os;
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


	public String getBrowser() {
		return browser;
	}


	public void setBrowser(String browser) {
		this.browser = browser;
	}


	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	public String getOs() {
		return os;
	}


	public void setOs(String os) {
		this.os = os;
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
