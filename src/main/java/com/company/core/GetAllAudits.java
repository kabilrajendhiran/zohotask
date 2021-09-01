//$Id$
package com.company.core;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.audit.AuditManager;
import com.company.auth.AuthFilter;
import com.company.ticket.CookieManager;
import com.company.user.UserManager;

public class GetAllAudits extends HttpServlet {

	 AuthFilter authFilter = new AuthFilter();
	 AuditManager auditManager = new AuditManager();
	 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!authFilter.doFilter(req)){
	           resp.sendRedirect("login.jsp");
	           return;
	    }
		
		
		req.setAttribute("audits", auditManager.getAllAudits(req));
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("audit.jsp");
        requestDispatcher.forward(req,resp);
	}
}
