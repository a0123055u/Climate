package com.example.climate.service;

import java.util.logging.Logger;

import org.springframework.web.client.RestTemplate;

//import com.example.climate.model.ClimateRepository;


public class ClimateService {
 
	
	
	public String getOrCreateClimateParameter(String id) {
		final Logger LOGGER = Logger.getLogger(ClimateService.class.getName());		
		
//			URL for redirecting into 6 different location with KEY for my user details 
		String url1 = new String("https://api.darksky.net/forecast/aef528f94224485b6dc4d7b7a60f67b9/37.2872,121.9500");
		String url2 = new String("https://api.darksky.net/forecast/aef528f94224485b6dc4d7b7a60f67b9/41.2565,95.9345");
		String url3 = new String("https://api.darksky.net/forecast/aef528f94224485b6dc4d7b7a60f67b9/30.2672,97.7431");
		String url4 = new String("https://api.darksky.net/forecast/aef528f94224485b6dc4d7b7a60f67b9/42.8048,140.6874");
		String url5 = new String("https://api.darksky.net/forecast/aef528f94224485b6dc4d7b7a60f67b9/34.6851,135.8048");
		String url6 = new String("https://api.darksky.net/forecast/aef528f94224485b6dc4d7b7a60f67b9/41.2565,95.9345");
		String url;
		switch(id.toString()){
		case "1": 
			url=url1;
			break;
		case "2":
			url=url2;
			break;
		case "3": 
			url=url3;
			break;
		case "4":
			url=url4;
			break;
		case "5": 
			url=url5;
			break;
		case "6":
			url=url6;
			break;
		default:
			url = "";
		}
		String response;
		String responseMongoDB = null; 		
		try {
		RestTemplate restTemplate = new RestTemplate();
		
		MongoDBService mongoService = new MongoDBService();
		responseMongoDB = mongoService.findRecordById(id);	 
		 
		 if(responseMongoDB != null) {
			 return responseMongoDB;
		 }
		 else{
			 if(!url.isEmpty()) {
					LOGGER.info("Before Entering External API");
					 response = restTemplate.getForObject(url, String.class);
					 mongoService.insertRecord(response, id);										
				}
				else {
						return "No ID found";		
					}			 
		 }		
		
		}catch(Exception ex) {
			
			return ex.toString();
		}
		LOGGER.info("After Entering External API - Success");
		
		return response;
	}

}
