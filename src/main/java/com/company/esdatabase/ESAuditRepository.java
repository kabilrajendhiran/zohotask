//$Id$
package com.company.esdatabase;

import java.util.List;

import com.company.audit.Audit;

public class ESAuditRepository {

	ESDBContext esdbContext = new ESDBContext();
	
	public void insert(Audit audit)
	{
		esdbContext.insertAudit(audit);
	}

	public List<Audit> getAllAudits(String email) {
		return esdbContext.getAllAudits(email);
	}
	
	
	
}
