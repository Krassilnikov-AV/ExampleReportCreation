/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import doc.ReadExcelData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;

import java.sql.*;
import java.util.List;

/**
 * Класс ExampleConnection
 */
public class ExampleConnection {

	private static Connection conn = null;
	static PreparedStatement stm = null;
	static ResultSet result = null;
	static String SQL = "";

	private static String URL = "jdbc:postgresql://localhost:5432/test";
	private static String USERNAME = "postgres";
	private static String PASSWORD = "alex159";

	public static void main(String[] args) throws SQLException {
		//запрос на внесение прочитанных данных в столбец
		SQL = "insert into contact(value) values (?)";

		try {
			System.out.println("Устанавливаем соединение с БД...");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			stm = conn.prepareStatement(SQL);

			ReadExcelData read = new ReadExcelData();
			List<String> list = read.getDataStringIntegerDate(0);

			for (String value : list) {
				stm.setString(1, value);
			}
			stm.addBatch();
			stm.executeUpdate();
		}
	 catch(Exception e)	{
		assert e != null; e.printStackTrace();
	} finally 	{
			conn.close();
			System.out.println("Закрыли соединение с БД " +
				"после внесения и просмотра данных...");
		}
		// запрос просмотра данных в столбце
		SQL = "SELECT value FROM \"public\".contact LIMIT 100000;";
		stm = conn.prepareStatement(SQL);
		result = stm.executeQuery();

		while (result.next()) {
			String value = result.getString("value");
			System.out.println(value);
		}
	}
}
