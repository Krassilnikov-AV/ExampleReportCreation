/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import connection.ConfigurateApp;
import doc.ReadExcelData;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * Класс ExampleConnection
 */
public class ExampleConnection {
	private static Connection conn = null;

	static String SQL = "";
	static PreparedStatement stm = null;

//	private static String URL = "jdbc:postgresql://localhost:5432/test";
//	private static String USERNAME = "postgres";
//	private static String PASSWORD = "alex159";

	public static void main(String[] args) throws SQLException {
		insertQuerySQL();
		viewData();
	}

	// метод для установления соединения
	private static Connection getPostConnection() throws SQLException {
		//ConfigurateApp conf = new ConfigurateApp();
		System.out.println("Устанавливаем соединение с БД...");

	//	return DriverManager.getConnection(conf.getDatabaseUrl(), conf.getDatabaseUser(), conf.getDatabasePassword());
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/raspisanie", "postgres", "alex159");
	}

	// метод для внесения данных в таблицу
	private static void insertQuerySQL() throws SQLException {
		//запрос на внесение прочитанных данных в столбец
		SQL = "insert into contact(person) values (?)";

		try {
			stm = getPostConnection().prepareStatement(SQL);
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
