package connection;/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

import doc.ReadExcelData;

import java.sql.*;
import java.util.List;

/**
 * Класс connection.ClassConnection
 */
public class ClassConnection {
	private static Connection conn = null;
	/**
    private static String URL = "jdbc:postgresql://localhost:5432/test";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "alex159";


	 * public static void main(String[] args) throws SQLException {
	 *
	 * PreparedStatement stm = null; ResultSet result = null; String SQL = "";
	 *
	 * //запрос на внесение прочитанных данных в столбец
	 * SQL = "insert into
	 * contact(value) values (?)";
	 *
	 * try { System.out.println("Устанавливаем соединение с БД..."); conn =
	 * DriverManager.getConnection(URL, USERNAME, PASSWORD);
	 *
	 * stm = conn.prepareStatement(SQL);
	 *
	 * doc.ReadExcelData read = new doc.ReadExcelData(); List<String> list =
	 * read.getDataStringIntegerDate(0);
	 *
	 * for (String value : list) { stm.setString(1, value); stm.addBatch();
	 * stm.executeUpdate(); }
	 *
	 * // запрос просмотра данных в столбце SQL = "SELECT value FROM
	 * \"public\".contact LIMIT 100000;"; stm = conn.prepareStatement(SQL);
	 * result = stm.executeQuery();
	 *
	 * while (result.next()) { String value = result.getString("value");
	 * System.out.println(value); } } catch (Exception e) { e.printStackTrace();
	 * } finally { conn.close(); System.out.println("Закрыли соединение с БД
	 * после внесения и просмотра данных..."); } }
	 *
	 */

	public static void main(String[] args) throws SQLException {
	/*	ClassConnection classConn = new ClassConnection();
		classConn.*/
	}

	public static Connection getConnection() throws SQLException {
		PreparedStatement stm = null;
		ResultSet result = null;
		String SQL = "";

		//!!! вынести в отдельный метод, запрос на внесение прочитанных данных в столбец
		SQL = "insert into contact(value) values (?)";

		try {
			System.out.println("Устанавливаем соединение с БД...");
			ConfigurateApp confApp = new ConfigurateApp();

			conn = DriverManager.getConnection(confApp.getDatabaseUrl(),
				confApp.getDatabaseUser(), confApp.getDatabasePassword());

			stm = conn.prepareStatement(SQL);

			ReadExcelData read = new ReadExcelData();
			List<String> list = read.getDataStringIntegerDate(0);

			for (String value : list) {
				stm.setString(1, value);
				stm.addBatch();
				stm.executeUpdate();
			}

			//     запрос просмотра данных в столбце
			SQL = "SELECT value FROM \"public\".contact LIMIT 100000;";
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
			System.out.println("Соединение с БД закрыто после внесения изменений и просмотра данных...");
		}
		return conn;
	}
}