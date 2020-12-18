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
 * Класс ExampleConnection
 */
public class ExampleConnection {

	static Connection conn = null;
	static String SQL = "";
	static PreparedStatement stm = null;
	static ConfigurateApp conf = new ConfigurateApp();

	public static void main(String[] args) throws SQLException, IOException {
//		insertQuerySQL();
//		viewData();
		conf.init();
		System.out.println(conf.getDatabaseName());
		System.out.println(getURL());
	}

	/*
	* private static String getUrl() {

	//jdbc:derby://localhost:1527/Samples
	String url = "jdbc:derby://localhost:1527/Samples";
        System.out.println("есть url...");
        return url;
}*/
	private static String getURL() {
		String databaseDriver = conf.getDatabaseDriver();
		String databaseHost = conf.getDatabaseHost();
		String databasePort = conf.getDatabasePort();
		String databaseName = conf.getDatabaseName();

		String url = databaseDriver.concat("://").concat(databaseHost).concat(":").concat(databasePort).concat("/").concat(databaseName);
		return url;
	}

	public static Connection getPostConnection() throws SQLException {

		System.out.println("Устанавливаем соединение с БД...");
		final Connection connection;
		connection = DriverManager.getConnection(getURL(), conf.getDatabaseUser(), conf.getDatabasePassword());
		return connection;
	//	return DriverManager.getConnection("jdbc:postgresql://localhost:5432/raspisanie", "postgres", "alex159");
	}

	// метод для внесения данных в таблицу
	public static void insertQuerySQL() throws SQLException {
		//запрос на внесение прочитанных данных в столбец
		SQL = "insert into contact(person) values (?)";
		try {
			Connection connection = null;
			//	Connection connection = getPostConnection();
			//			stm = connection.prepareStatement(SQL);
			ReadExcelData read = new ReadExcelData();
			List<String> list = read.getDataStringIntegerDate(0);

			for (String value : list) {
				stm.setString(1, value);
				stm.addBatch();
				stm.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//	conn.close();
			System.out.println("Закрыли соединение с БД после внесения данных...");
		}
	}

	// метод для просмотра внесённых данных в таблицу
	private static void viewData() throws SQLException {
		ResultSet result = null;

		// запрос просмотра данных в столбце
		SQL = "SELECT contact FROM raspisanie";
		try {
			stm = conn.prepareStatement(SQL);

			result = stm.executeQuery();

			while (result.next()) {
				String value = result.getString("value");
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println("Закрыли соединение с БД после просмотра данных...");
		}
	}
}
