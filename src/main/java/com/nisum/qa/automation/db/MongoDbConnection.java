package com.nisum.qa.automation.db;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.exceptionhandler.ExceptionHandler;
import com.exceptionhandler.HostNameCheckerException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.MongoSocketException;
import com.nisum.qa.automation.util.LoggerUtils;

public class MongoDbConnection implements IDatabaseConnection, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1434380699698710104L;

	@Override
	public String findRecord(String hostName, String dbName, String tableName, Map<String, String> colNameValuePair) {

		String record = null;
		MongoClient mongo = null; // new MongoClient(hostName, 2707);
		try {
		mongo = new MongoClient(hostName, 27017);

		@SuppressWarnings("deprecation")
		DB db = mongo.getDB(dbName);

		DBCollection table = db.getCollection(tableName);
		BasicDBObject searchQuery = new BasicDBObject();
		for (Map.Entry<String, String> pair : colNameValuePair.entrySet()) {
			String key = pair.getKey().replaceAll("\\s+", "");
			char c[] = key.toCharArray();
			c[0] = Character.toLowerCase(c[0]);
			key = new String(c);
			searchQuery.put(key, pair.getValue());
		}
		DBCursor cursor = table.find(searchQuery);
		if (cursor.hasNext()) {
			record = cursor.next().toString();
		}
		}catch (HostNameCheckerException e) {
			System.out.println("error");
	    } catch (Exception e) {
	        LoggerUtils.getInstance().error("Please check the entered values");
	    }
		return record;
	}
	// catch (MongoSocketException e) {
		// System.out.println("error");
		// LoggerUtils.getInstance().error("Exception occured due to :
		// ",e.printStackTrace(););
		// throw new ExceptionHandler("Please check the given hostName : "+hostName);
		// }
	
	public static void main(String[] args) {
		Map<String, String> colNameValuePair = new HashMap<>();
		MongoDbConnection m = new MongoDbConnection();
		m.findRecord("local", "mytime", "Accounts", colNameValuePair);
	}
}
