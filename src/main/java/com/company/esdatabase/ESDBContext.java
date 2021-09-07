//$Id$
package com.company.esdatabase;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.company.audit.Audit;
import com.company.helper.Utility;

public class ESDBContext {
	
	public void insertAudit(Audit audit) {
		
		String dateTime = Utility.convertDateTime(LocalDateTime.now());
		
		audit.setCurrentTimestamp(dateTime);
		
		Map<String,Object> sourceData = Utility.auditToJson(audit);
		IndexRequest indexRequest = new IndexRequest("audit");
		indexRequest.source(sourceData);
		
		try(RestHighLevelClient restHighLevelClient = ESConfig.ESINSTANCE.getClient()){
			IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println(indexResponse.getResult());
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public List<Audit> getAllAudits(String emailId)
	{
		List<Audit> audits = Collections.synchronizedList(new ArrayList<>()); 
		
		
		SearchSourceBuilder ssb = new SearchSourceBuilder();
		QueryBuilder query = new TermQueryBuilder("email", emailId);
		ssb.query(query);
		
		SearchRequest sr = new SearchRequest();
		sr.source(ssb);
		
		try(RestHighLevelClient restHighLevelClient = ESConfig.ESINSTANCE.getClient()){
			SearchResponse sResponse = restHighLevelClient.search(sr, RequestOptions.DEFAULT);
			
			SearchHits searchHits = sResponse.getHits();
		    
	    	for(SearchHit sh : searchHits)
	    	{
	    		Audit audit = Utility.jsonToAudit(sh.getSourceAsString());
	    		audits.add(audit);
	    	}
	    	
	    	restHighLevelClient.close();
			
		}
		
		catch(IOException io)
		{
			io.printStackTrace();
		}
		
		return audits;
	}
	
}
