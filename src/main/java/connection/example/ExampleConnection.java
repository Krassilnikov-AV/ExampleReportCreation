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
	static ConfigurateApp conf = new ConfigurateApp();
	static Connection connection = null;
	static int result = 0;

	public static void main(String[] args) throws SQLException, IOException {
//		insertQuerySQL();
//		viewData();
		conf.init();
		System.out.println(conf.getDatabaseName());
		System.out.println(getURL());
//		getPostConnection();
//		insertQuerySQL();
//		viewData();
		ExampleConnection exampleConnection = new ExampleConnection();

		exampleConnection.deletedDataSQL();
	}

	/*
	метод, который возвращает url базы данных, прописанной в property файле
}*/
	private static String getURL() {
		String databaseDriver = conf.getDatabaseDriver();
		String databaseHost = conf.getDatabaseHost();
		String databasePort = conf.getDatabasePort();
		String databaseName = conf.getDatabaseName();

		String url = databaseDriver.concat("://").concat(databaseHost).concat(":").concat(databasePort).concat("/").concat(databaseName);
		return url;
	}

	public static Connection getPostConnection() throws SQLException, IOException {
		System.out.println("Устанавливаем соединение с БД...");
		conf.init();
		connection = DriverManager.getConnection(getURL(),
			conf.getDatabaseUser(),
			conf.getDatabasePassword());
		return connection;
	}

	/*
	    метод для внесения данных в таблицу выбранных с таблицы Excel
	 */
	public static void insertQuerySQL() throws SQLException {
		//запрос на внесение прочитанных данных в столбец
		String SQLinsert = "insert into contact(person) values (?)";
		try (Connection connection = getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(SQLinsert)) {

				ReadExcelData read = new ReadExcelData();
				List<String> list = read.getDataStringIntegerDate(4);

				for (String value : list) {
					stm.setString(1, value);
					stm.addBatch();
					stm.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//	conn.close();
			System.out.println("Закрыли соединение с БД после внесения данных...");
		}
	}

	/*
	метод для просмотра внесённых данных в таблицу
	*/
	private static void viewData() throws SQLException {
		String SQLselect = "SELECT contact FROM raspisanie";
		try (Connection connection = getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(SQLselect)) {
				ResultSet result =
					stm.executeQuery(SQLselect);
				while (result.next()) {
					String value = result.getString("value");
					System.out.println(value);
				}

				System.out.println("Закрыли соединение с БД после просмотра данных...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	метод для удаления внесённых данных в таблицу
	*/
		private int deletedDataSQL () throws SQLException {
			String SQLDeleted = "DELETE FROM contact";
			try (Connection connection = getPostConnection()) {
				try(PreparedStatement stm = connection.prepareStatement(SQLDeleted)) {
					int rs = stm.executeUpdate();
				}
				return stm.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
				System.out.println("Закрыли соединение с БД после удаления данных...");
			}
			return 0;
		}
	}
