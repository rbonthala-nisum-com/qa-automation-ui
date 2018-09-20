package com.nisum.qa.automation.db;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDbConnection implements IDatabaseConnection {

	@Override
	public String findRecord(String hostName, String dbName, String tableName, Map<String, String> colNameValuePair) {
		String record = null;
		try {
			/**
			 * Establish connection with mongo database to fetch required records from the
			 * specific tables
			 */
			MongoClient mongo = new MongoClient(hostName, 27017);
			DB db = mongo.getDB(dbName);
			DBCollection table = db.getCollection(tableName);
			BasicDBObject searchQuery = new BasicDBObject();
			for (Map.Entry<String, String> pair : colNameValuePair.entrySet()) {
				String key = pair.getKey().replaceAll("\\s+","");
				char c[] = key.toCharArray();
				c[0] = Character.toLowerCase(c[0]);
				key = new String(c);
				searchQuery.put(key, pair.getValue());
			}
			DBCursor cursor = table.find(searchQuery);
			if (cursor.hasNext()) {
				record = cursor.next().toString();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return record;
	}
}
