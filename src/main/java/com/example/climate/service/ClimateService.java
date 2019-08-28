package com.example.climate.service;

import java.util.logging.Logger;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import org.bson.Document;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.climate.model.Climate;
//import com.mongodb.MongoClientURI;
//import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


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
		try {
		RestTemplate restTemplate = new RestTemplate();


//		have issue with connecting to MongoDB 
//			MongoClient mongoClient = MongoClients.create("mongodb://thiru:<thiru124>@cluster0-shard-00-00-uvnua.mongodb.net:27017,cluster0-shard-00-01-uvnua.mongodb.net:27017,cluster0-shard-00-02-uvnua.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
//			MongoDatabase database = mongoClient.getDatabase("climate");
//			MongoCollection<Document> val = database.getCollection("parameter");
//			if (val != null) {
//				System.out.println("val"+val);				
//			}
//			else {
////				the below code of checking if and connecting to external api must be triggered only if the document is not present 
//				
//			}
		if(!url.isEmpty()) {
			//this is used to call external API Darksky   with secret KEY as aef528f94224485b6dc4d7b7a60f67b9
			
			LOGGER.info("Before Entering External API");
			 response = restTemplate.getForObject(url, String.class);
		}
		else {
			return "No ID found";			}	
		
		}catch(Exception ex) {
			
			return ex.toString();
		}
		LOGGER.info("After Entering External API - Success");
		return response;
	}

}
