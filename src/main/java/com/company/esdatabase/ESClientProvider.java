//$Id$
package com.company.esdatabase;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClientProvider {
	
	public static RestHighLevelClient getClient() {
		
		RestHighLevelClient restHighLevelClient =  new RestHighLevelClient(
    	        RestClient.builder(
    	                new HttpHost("localhost", 9200, "http"),
    	                new HttpHost("localhost", 9201, "http"))
				);
		
		return restHighLevelClient;
	}
	
}
