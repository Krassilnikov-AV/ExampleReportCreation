/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package example.exConnected;

import connection.ConfigurateApp;
import example.exQuery.ExQuery;

import java.io.IOException;
import java.sql.*;

/**
 * Класс ExampleConnection, устанавливает соединение с базой данных
 * прописывает название и URL базы данных
 */
public class ExampleConnection {

	static ConfigurateApp conf = new ConfigurateApp();

	public static void main(String[] args) throws SQLException, IOException {
		ExampleConnection exampleConnection = new ExampleConnection();
//
		ExSQLQueryDate exSQLQueryDate = new ExSQLQueryDate();
//		ExQuery exQuery = new ExQuery();

		exampleConnection.getNameURL();

		exSQLQueryDate.deletedDataSQL();
		exSQLQueryDate.insertExecuteBatchQuerySQL();
//		exSQLQueryDate.insertExecuteUpdateQuerySQL();

//		exSQLQueryDate.insertStartDateSQL();
//		exSQLQueryDate.insertStartTimeSQL();

//		exQuery.writeWithCompileQuery(100);
//		exQuery.writeInABatchWithCompiledQuery(100);
	}

	/*
	метод, который возвращает url базы данных, прописанной в property файле
}*/
	private String getURL() {
		String databaseDriver = conf.getDatabaseDriver();
		String databaseHost = conf.getDatabaseHost();
		String databasePort = conf.getDatabasePort();
		String databaseName = conf.getDatabaseName();

		String url =databaseDriver.concat("://").concat(databaseHost).concat(":").concat(databasePort).concat("/").concat(databaseName);
		return url;
	}

	public Connection getPostConnection() throws SQLException, IOException {
		System.out.println("Устанавливаем соединение с БД...");
		conf.init();
		Connection connection = DriverManager.getConnection(getURL(),
			conf.getDatabaseUser(),
			conf.getDatabasePassword());
		return connection;
	}

	private void getNameURL() throws IOException {
		conf.init();
		System.out.println("Название базы данных: " + conf.getDatabaseName());
		System.out.println("URL базы данных: " + getURL());
	}
}