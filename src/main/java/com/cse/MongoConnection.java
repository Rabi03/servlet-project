package com.cse;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection{
	public MongoDatabase db;
	public MongoConnection() {
		MongoClient client=MongoClients.create("<your mongodb connection url>");
		System.out.println("server connection successfully done");
		this.db = client.getDatabase("webTech");
		
	}
	
	public MongoDatabase getDatabase() {
		return this.db;
	}
}


//MongoCollection col = db.getCollection("collection1");
//
//Document sampleDoc = new Document("_id", "1").append("name", "John Smith");
//
//col.insertOne(sampleDoc);
