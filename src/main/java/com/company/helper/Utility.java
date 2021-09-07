package com.company.helper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.company.audit.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {
    public static String generateTicketID()
    {
        StringBuilder id = new StringBuilder("TICKETID-");
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int[] raw_id = random.ints(97,122).limit(15).toArray();
        for (int i:raw_id) {
            id.append((char) i);
        }
        return id.toString();
    }

    public static String convertDateTime(LocalDateTime dateTime)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime convertDateTime(String dateTime)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime,dateTimeFormatter);
    }
    
    public static Map<String,Object> auditToJson(Audit audit)
    {
    	ObjectMapper oMapper = new ObjectMapper();
    	Map<String,Object> map = oMapper.convertValue(audit, Map.class);
    	return map;
    }
    
    public static Audit jsonToAudit(String json)
    {
    	ObjectMapper oMapper = new ObjectMapper();
    	Audit audit=null;
		try {
			audit = oMapper.readValue(json, Audit.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return audit;
    }
    

}
