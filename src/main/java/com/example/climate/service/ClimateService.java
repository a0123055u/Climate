package com.example.climate.service;

import java.util.logging.Logger;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import org.apache.tomcat.util.json.JSONParser;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.climate.model.Climate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
//import com.mongodb.MongoClientURI;
//import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import static com.mongodb.client.model.Filters.*;
//import com.example.climate.model.ClimateRepository;


public class ClimateService {
//	@Autowired
//	private MongoTemplate mongoTemplate;
//	@Autowired
//	private ClimateRepository repository;
	
 
	
	
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
		int validateRecord = 0; 
		try {
		RestTemplate restTemplate = new RestTemplate();
		
		 MongoClient mongoClient =  MongoClients.create("mongodb://localhost:27017");         
		 MongoDatabase databases = mongoClient.getDatabase("climate");
		 MongoCollection<Document> doc = databases.getCollection("parameters");
		 BasicDBObject query = new BasicDBObject();
		 BasicDBObject field = new BasicDBObject();
		 field.put("key",id);
		 FindIterable<Document> climateCollection = databases.getCollection("parameters").find();
		 climateCollection = databases.getCollection("parameters").find(query);
		 System.out.println("tt=================>"+climateCollection);

		 for (Document test : climateCollection) {
			 System.out.println("tesst--"+test.toJson());	
			
			 responseMongoDB = test.toJson();
			 System.out.println();
		 }
		 
		 ObjectMapper mapper = new ObjectMapper();
		 Map<String, String> map = mapper.readValue(responseMongoDB, Map.class);
		 System.out.println("parse-------------------------------------"+map.get("key"));
		 
		 if(responseMongoDB!=null && (Integer.parseInt(map.get("key")) == Integer.parseInt(id))) {
			 
			 mongoClient.close();
			 return responseMongoDB;
		 }
		 else{
			 if(!url.isEmpty()) {				
					
					LOGGER.info("Before Entering External API");
					 response = restTemplate.getForObject(url, String.class);
					 Document  document = new Document();
					 document.put("key", id);
					 document.put("value", response.toString());
					 doc.insertOne(document);						
				}
				else {
					return "No ID found";			}			 
		 }		
		 mongoClient.close();
		}catch(Exception ex) {
			
			return ex.toString();
		}
		LOGGER.info("After Entering External API - Success");
		
		return response;
	}

}
