/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import doc.ReadExcelData;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Класс ExampleSQLQuery, содержит методы для работы с базой данных,
 * соединение с которой получено в классе ExampleConnection
 * имя БД - schedule
 * имя таблиц group, person, typeofemployment
 * имя столбцов д/л group: groupid, groupcode,
 *    programm, datestart, timestart, dateend,
 *    timeend, classrum, typelessons
 */
public class ExampleSQLQuery {

	String insertSQL = "insert into group(groupid) values (?)";
	String selectSQL = "SELECT contact FROM raspisanie";
	String deletedSQL = "DELETE FROM contact";

/**
 * ЗАДАНИЕ
 * нужно List<<int><int><string><date><time><date><time><string><string>>
 */
	ExampleConnection exmpCon = new ExampleConnection();

	/*
	    метод для внесения данных в таблицу выбранных с таблицы Excel
	 */
	public void insertQuerySQL() {
		try (Connection connection = exmpCon.getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(insertSQL)) {

				ReadExcelData read = new ReadExcelData();
				List<String> list = read.getDataStringIntegerDate(0);

				for (String value : list) {
					stm.setString(1, value);  // вставка значений
					stm.addBatch();
					stm.executeUpdate();
				}
			}
		} catch (Exception e) {
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
		try (Connection connection = exmpCon.getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(selectSQL)) {
				ResultSet result =
					stm.executeQuery(selectSQL);
				while (result.next()) {
					String value = result.getString("value");
					System.out.println(value);
				}

				System.out.println("Закрыли соединение с БД после просмотра данных...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("ВСТАВИЛИ ДАННЫЕ в БД");
			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
			System.out.println("Закрыли соединение с БД после удаления данных...");
		}
	}

	/*
	метод для удаления внесённых данных в таблицу
	*/
	public int deletedDataSQL() throws SQLException {
		try (Connection connection = exmpCon.getPostConnection()) {
			System.out.println("Соединение установлено...");
			try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
				return stm.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("УДАЛИЛИ ДАННЫЕ БД");
			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
			System.out.println("Закрыли соединение с БД после удаления данных...");
		}
		return 0;
	}

}