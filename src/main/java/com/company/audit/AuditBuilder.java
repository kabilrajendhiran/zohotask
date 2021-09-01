package com.company.audit;

import com.company.audit.Audit;
import com.company.ticket.CookieManager;
import com.company.user.UserManager;

import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

public class AuditBuilder {
	
	ConcurrentHashMap<String, String> uri_activity = new ConcurrentHashMap<String, String>();
	CookieManager cookieManager = new CookieManager();
	UserManager userManager = new UserManager();
	
	
	public AuditBuilder()
	{
		uri_activity.put("GetAllSessions", "Viewed All Sessions");
		uri_activity.put("Login", "Logged In");
		uri_activity.put("Logout", "Logged Out");
		uri_activity.put("RemoveSession", "Remote logout");
		uri_activity.put("welcome.jsp", "Visited welcome");
	}
	
	
    public String getUserAgent(HttpServletRequest request)
    {
    	return request.getHeader("user-agent");
    }
    
    public String getBrowserName(HttpServletRequest request)
    {
    	String browser = "Unknown browser";
    	String userAgent =  getUserAgent(request).toLowerCase();
    	if(userAgent.contains("firefox") && !userAgent.contains("seamonkey"))
    	{
    		browser = "firefox";
    	}
    	else if(userAgent.contains("chrome") && !userAgent.contains("chromium")) {
    		browser = "chrome";
    	}
    	else if(userAgent.contains("chromium")) {
    		browser = "Chromium";
    	}
    	else if(userAgent.contains("safari") && !userAgent.contains("seamonkey") 
    			&& !userAgent.contains("chromium")) {
    		
    		browser = "Safari";
    	}
    	
    	System.out.println(browser);
    	
    	return browser;
    }
    
    public String getOsName(HttpServletRequest request)
    {
    	String os = "Unknown Os";
    	String userAgent =  getUserAgent(request).toLowerCase();
    	if(userAgent.contains("windows"))
    	{
    		os = "windows";
    	}else if(userAgent.contains("x11")) {
			os = "linux";
		}
    	else if(userAgent.contains("mac")) {
			os = "mac";
		}
    	else if(userAgent.contains("android")) {
			os = "android";
		}
    	else if(userAgent.contains("iphone")) {
			os = "iphone";
		}
    	System.out.println(os);
    	
    	return os;
    }
    
    public String getDevice(HttpServletRequest request)
    {
    	String userAgent =  getUserAgent(request).toLowerCase();
    	if(userAgent.contains("mobi"))
    	{
    		System.out.println("mobile");
    		return "Mobile";
    	}
    	else
    	{
    		System.out.println("desktop");
    		return "Desktop";
    	}
    }
    
    
    public String getActivity(HttpServletRequest request) 
    {
    	String uri =  request.getRequestURI();
    	String[] uriParts = uri.split("/");
    	String target = uriParts[uriParts.length-1];
    	return getActivityByUri(target);
    }
    
    public String getUserIpAddr(HttpServletRequest request)
    {
    	System.out.println(request.getRemoteAddr());
    	return request.getRemoteAddr();
    }
    
    public String getActivityByUri(String target)
    {
    	return uri_activity.get(target);
    }
    
    public Audit build(HttpServletRequest request, boolean validity)
    {
    	Audit audit = new Audit();
    	audit.setUserAgent(getUserAgent(request));
    	audit.setIpAddr(getUserIpAddr(request));
    	audit.setActivity(getActivity(request));
    	audit.setValidity(validity);
    	
    	String tickedID = cookieManager.getAuthCookieValue(request.getCookies());
        String email = userManager.getUserEmailIdFromTicketId(tickedID);
    	
        audit.setEmail(email);
        
        audit.setBrowser(getBrowserName(request));
        audit.setDevice(getDevice(request));
        audit.setOs(getOsName(request));
        
    	return audit;
    }
    
    public Audit build(HttpServletRequest request, String ticketId, boolean validity)
    {
    	Audit audit = new Audit();
    	audit.setUserAgent(getUserAgent(request));
    	audit.setIpAddr(getUserIpAddr(request));
    	audit.setActivity(getActivity(request));
    	audit.setValidity(validity);
    	
    	
        String email = userManager.getUserEmailIdFromTicketId(ticketId);
        audit.setEmail(email);
        
        audit.setBrowser(getBrowserName(request));
        audit.setDevice(getDevice(request));
        audit.setOs(getOsName(request));
        
    	return audit;
    }
    
    
}
