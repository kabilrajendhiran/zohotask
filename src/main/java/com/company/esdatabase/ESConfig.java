//$Id$
package com.company.esdatabase;

import org.elasticsearch.client.RestHighLevelClient;

public enum ESConfig {
	ESINSTANCE;
	
	RestHighLevelClient esClient;
	
	private ESConfig()
    {
        esClient = ESClientProvider.getClient();
    }

    public static ESConfig getInstance()
    {
    	return ESINSTANCE;
    }
    
    public RestHighLevelClient getClient()
    {
    	return ESClientProvider.getClient();
    }
	
}
