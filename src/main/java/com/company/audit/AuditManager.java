//$Id$
package com.company.audit;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.company.data.AuthDatabaseContext;
import com.company.ticket.CookieManager;
import com.company.user.UserManager;

public class AuditManager {

	AuthDatabaseContext database = new AuthDatabaseContext();
	CookieManager cookieManager = new CookieManager();
	UserManager userManager = new UserManager();
	
	public void createAudit(boolean validity, HttpServletRequest request)
	{
		AuditBuilder auditBuilder = new AuditBuilder();
		Audit audit = auditBuilder.build(request,true);
		try {
			database.createAudit(audit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createAudit(boolean validity, HttpServletRequest request, String ticketId)
	{
		AuditBuilder auditBuilder = new AuditBuilder();
		Audit audit = auditBuilder.build(request,ticketId,true);
		try {
			database.createAudit(audit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Audit> getAllAudits(HttpServletRequest req)
	{
		
		String ticketID = cookieManager.getAuthCookieValue(req.getCookies());
        String email = userManager.getUserEmailIdFromTicketId(ticketID);
		
		List<Audit> audits = null;
		try {
		 	audits = database.getAllAudits(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audits;
	}
	
	
}
