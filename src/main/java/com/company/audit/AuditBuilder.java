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
	}
	
	
    public String getUserAgent(HttpServletRequest request)
    {
    	return request.getHeader("user-agent");
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
    
    public Audit build(HttpServletRequest request)
    {
    	Audit audit = new Audit();
    	audit.setUserAgent(getUserAgent(request));
    	audit.setIpAddr(getUserIpAddr(request));
    	audit.setActivity(getActivity(request));
    	
    	String tickedID = cookieManager.getAuthCookieValue(request.getCookies());
        String email = userManager.getUserEmailIdFromTicketId(tickedID);
    	
        audit.setEmail(email);
        
    	return audit;
    }
    
    public Audit build(HttpServletRequest request, String ticketId)
    {
    	Audit audit = new Audit();
    	audit.setUserAgent(getUserAgent(request));
    	audit.setIpAddr(getUserIpAddr(request));
    	audit.setActivity(getActivity(request));
    	
        String email = userManager.getUserEmailIdFromTicketId(ticketId);
        audit.setEmail(email);
        
    	return audit;
    }
    
    
}
