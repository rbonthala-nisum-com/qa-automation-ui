package com.nisum.qa.automation.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.nisum.qa.automation.util.PropertiesUtils;

/**
 * @author Nisum
 *
 */
public class DbConnection extends PropertiesUtils {

	private static Logger log = LoggerFactory.getLogger(DbConnection.class);

	public MongoDatabase getConnection(String filePath) {
		MongoDatabase database = null;
		try {
			MongoCredential credential;
			MongoClient mongo = new MongoClient(readPropertyValue(filePath,"host"), 27017);
			credential = MongoCredential.createCredential(readPropertyValue(filePath,"username"),
					readPropertyValue(filePath,"database"), readPropertyValue(filePath,"password").toCharArray());

			database = mongo.getDatabase(readPropertyValue(filePath,"password"));
		} catch (Exception e) {
			log.error("Problem in loading or " + "registering MongoDb driver", e);
		}
		return database;
	}
}
