package com.nisum.automation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

/**
 * @author Nisum
 *
 */
public class DbConnection extends ReadProperties {

	private static Logger log = LoggerFactory.getLogger(DbConnection.class);

	public MongoDatabase getConnection() {
		MongoDatabase database = null;
		try {
			MongoCredential credential;
			MongoClient mongo = new MongoClient(toGetGivenProperty("host"), 27017);
			credential = MongoCredential.createCredential(toGetGivenProperty("username"),
					toGetGivenProperty("database"), toGetGivenProperty("password").toCharArray());

			database = mongo.getDatabase(toGetGivenProperty("password"));
		} catch (Exception e) {
			log.error("Problem in loading or " + "registering MongoDb driver", e);
		}
		return database;
	}
}
