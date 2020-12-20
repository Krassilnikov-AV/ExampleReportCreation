/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import connection.ConfigurateApp;
import doc.ReadExcelData;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Класс ExampleConnection, устанавливает соединение с базой данных
 * прописывает название и URL базы данных
 */
public class ExampleConnection {

	static ConfigurateApp conf = new ConfigurateApp();

	public static void main(String[] args) throws SQLException, IOException {
		ExampleConnection exampleConnection = new ExampleConnection();
		ExampleSQLQuery exampleSQLQuery = new ExampleSQLQuery();

		exampleConnection.getNameURL();
//		exampleSQLQuery.insertQuerySQL();
		exampleSQLQuery.deletedDataSQL();
	}

	/*
	метод, который возвращает url базы данных, прописанной в property файле
}*/
	private String getURL() {
		String databaseDriver = conf.getDatabaseDriver();
		String databaseHost = conf.getDatabaseHost();
		String databasePort = conf.getDatabasePort();
		String databaseName = conf.getDatabaseName();

		String url = databaseDriver.concat("://").concat(databaseHost).concat(":").concat(databasePort).concat("/").concat(databaseName);
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