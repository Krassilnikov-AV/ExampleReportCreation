/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import doc.ReadExcelData;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Класс ExampleSQLQuery
 */
public class ExampleSQLQuery {

	ExampleConnection exmpCon = new ExampleConnection();
	/*
	    метод для внесения данных в таблицу выбранных с таблицы Excel
	 */
	public void insertQuerySQL() {
			String SQLinsert = "insert into contact(person) values (?)";
		try (Connection connection = exmpCon.getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(SQLinsert)) {

				ReadExcelData read = new ReadExcelData();
				List<String> list = read.getDataStringIntegerDate(0);

				for (String value : list) {
					stm.setString(1, value);  // вставка значений
					stm.addBatch();
					stm.executeUpdate();
				}
			}
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
			System.out.println("Закрыли соединение с БД после внесения данных...");
		}
	}

	/*
	метод для просмотра внесённых данных в таблицу
	*/
	public void viewData() throws SQLException {
		String SQLselect = "SELECT contact FROM raspisanie";
		try (Connection connection = exmpCon.getPostConnection()) {
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
	public int deletedDataSQL() throws SQLException {
		String SQLDeleted = "DELETE FROM contact";
		try (Connection connection = exmpCon.getPostConnection()) {
			System.out.println("Соединение установлено...");
			try (PreparedStatement stm = connection.prepareStatement(SQLDeleted)) {
				return stm.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			System.out.println("УДАЛИЛИ ДАННЫЕ БД");
			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
			System.out.println("Закрыли соединение с БД после удаления данных...");
		}
		return 0;
	}

}