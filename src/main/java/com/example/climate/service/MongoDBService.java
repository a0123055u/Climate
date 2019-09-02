package com.example.climate.service;

import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class MongoDBService {
	MongoClient mongoClient =  MongoClients.create("mongodb://localhost:27017");         
	 MongoDatabase databases = mongoClient.getDatabase("climate");
	
	 final Logger LOGGER = Logger.getLogger(MongoDBService.class.getName());		
	
	public String findRecordById(String id) {
		
		String responseMongoDB = null; 
		boolean  foundRecordInDB = false; 		
		 BasicDBObject query = new BasicDBObject();
		 BasicDBObject field = new BasicDBObject();
		 field.put("key",id.toString());
		 try {
		 FindIterable<Document> climateCollection = databases.getCollection("parameters").find(query);		 
		 int key_db=0;
		 for (Document test : climateCollection) {			
			 responseMongoDB = test.toJson();
			 ObjectMapper mapper = new ObjectMapper();
			 Map<String, String> map = mapper.readValue(responseMongoDB, Map.class);
			 key_db=  Integer.parseInt(map.get("key"));
			if(key_db == Integer.parseInt(id)) {
				foundRecordInDB = true;
				LOGGER.info("Matched ==== db val ===>"+key_db+"=====passed is==>> "+id);				
				 break;
			}
			else {
				responseMongoDB = null;
				LOGGER.info("NOT MATCHED <<<<<<<<<<<<<<<<"+key_db+">>>>>>>>>>>>>>>>>>>> "+id);
			}
			
		 }		
		 }catch(Exception ex) {
			 LOGGER.info("Exception -----XXXXXXXXXX----------"+ex.toString());
			 return ex.toString();
		 }
		if (responseMongoDB != null) {
			mongoClient.close();
		}
		return responseMongoDB;
	}
	
	public boolean insertRecord(String inputForMongoDB, String id) {
		boolean response = false;
		try {
		 Document  document = new Document();
		 document.put("key", id);
		 document.put("value", inputForMongoDB.toString());
		 MongoCollection<Document> doc = databases.getCollection("parameters");
		 doc.insertOne(document);
		 mongoClient.close();
		 response = true;
		}catch(Exception ex){
			LOGGER.info("ERROR----XXXXXXXXXX-------->>>>"+ex.toString());
			
		}
		return response;
		
		
	}

}
