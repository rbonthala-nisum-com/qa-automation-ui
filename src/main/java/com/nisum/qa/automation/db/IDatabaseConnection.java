package com.nisum.qa.automation.db;

import java.util.Map;

public interface IDatabaseConnection {
	
	String findRecord(String hostName, String dbName, String tableName, Map<String, String> colNameValuePair) throws Exception;

}
