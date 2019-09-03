package com.example.climate.service;

import java.util.Date;
import java.util.Map;

import org.bson.BsonTimestamp;
import org.bson.Document;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;


public class MongoDBService {
	MongoClient mongoClient =  MongoClients.create("mongodb://localhost:27017");         
	 MongoDatabase databases = mongoClient.getDatabase("climate");
	
	 final Logger LOGGER = Logger.getLogger(MongoDBService.class.getName());		
	
	public String findRecordById(String id) {
		
		String responseMongoDB = null; 
				
		 BasicDBObject query = new BasicDBObject();
//		 BasicDBObject field = new BasicDBObject();
//		 field.put("key",id.toString());
		 try {
//			 passed empty string to get all records in db
		 FindIterable<Document> climateCollection = databases.getCollection("parameters").find(query);		 
		 int key_db=0;
		 for (Document test : climateCollection) {			
			 responseMongoDB = test.toJson();
			 ObjectMapper mapper = new ObjectMapper();
			 Map<String, String> map = mapper.readValue(responseMongoDB, Map.class);
			 key_db=  Integer.parseInt(map.get("key"));
			if(key_db == Integer.parseInt(id)) {				
				LOGGER.info("Matched ==== db val ===>"+key_db+"=====passed is==>> "+id);				
				 break;
			}
			else {
				responseMongoDB = null;
				LOGGER.info("NOT MATCHED <<<<<<<<<<<<<<<<"+key_db+">>>>>>>>>>>>>>>>>>>> "+id);
			}			
		 }		
		 }catch(Exception ex) {
			 LOGGER.info("Exception -----IN GETTING DOC FROM DB----XXXXXXXXXX----------"+ex.toString());
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
		 DBObject db = new BasicDBObject();
		 db.put("ts", new BsonTimestamp());
		 
		 document.put("key", id);
		 document.put("value", inputForMongoDB.toString());
		 document.put("ts", new Date());
		 MongoCollection<Document> doc = databases.getCollection("parameters");
		 doc.insertOne(document);
		 mongoClient.close();
		 response = true;
		}catch(Exception ex){
			LOGGER.info("ERROR----IN INSERT RECORD XXXXXXXXXX-------->>>>"+ex.toString());			
		}
		return response;
		
		
	}
	public boolean deleteRecordGrt3Days() {
		boolean response = false; 
		try {
		 MongoCollection<Document> doc = databases.getCollection("parameters");
		 BasicDBObject query = new BasicDBObject();
		 doc.deleteMany(query);
		 response = true;
		}
		catch(Exception ex) {
			LOGGER.info("ERROR----IN DEL FUNCTION from scheduler --XXXXXXXXXX-------->>>>"+ex.toString());	
		}
		
		 return response;
		
		
		
		
		
	}

}
