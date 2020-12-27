/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */
package example.exConnected;

import doc.ReadExcelData;
import example.exRead.ExReadExcelColums;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Класс ExampleSQLQueryRaspisanie, содержит методы для работы с базой данных,
 * соединение с которой получено в классе ExampleConnection
 * имя БД - schedule
 * имя таблиц group, person, typeofemployment
 * имя столбцов д/л group: groupid, groupcode,
 * programm, datestart, timestart, dateend,
 * timeend, classrum, typelessons
 */
public class ExampleSQLQueryRaspisanie {
	/**
	 * Команда INSERT INTO <table_name> в SQL отвечает за добавление данных в таблицу:
	 * <p>
	 * INSERT INTO <table_name> (<col_name1>, <col_name2>, <col_name3>, …)
	 * VALUES (<value1>, <value2>, <value3>, …);
	 */
	String insertSQL = "INSERT INTO schedule(program, codgroup) VALUES(?, ?)";
	String deletedSQL = "DELETE FROM schedule";
//	String insertSQL = "insert into group(groupid) values (?)";
//	String selectSQL = "SELECT programm FROM raspisanie";
//	String deletedSQL = "DELETE FROM group";

	/**
	 * ЗАДАНИЕ
	 * нужно List<<int><int><string><date><time><date><time><string><string>>
	 */
	ExampleConnection exmpCon = new ExampleConnection();

	public void insertQuerySQL() {
		try (Connection connection = exmpCon.getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(insertSQL)) {

				ExReadExcelColums exread = new ExReadExcelColums();
//				exread.buildingTable();
//				ReadExcelData read = new ReadExcelData();
//				List<String> list = read.getDataStringIntegerDate(0);
				List<Object> listPr = exread.getDataStringDate(14);
				List<Object> listGr = exread.getDataStringDate(3);

				Iterator itProg = listPr.iterator();
				Iterator itCode = listGr.iterator();

				for (Object s : listPr) {
					s = itProg.next().toString();
					if (itProg.hasNext()) {
						stm.setString(1, (String) s);
					}
				}
				for (Object n : listGr) {
					n = itCode.next().toString();
					if (itCode.hasNext()) {
						stm.setString(2, (String) n);
					}
					stm.addBatch();
					stm.executeUpdate();
				}

//				for (String value : list) {
//					stm.setString(1, value1, value2);  // вставка значений
//					stm.addBatch();
//					stm.executeUpdate();
//				}
			}
		} catch (Exception e) {
			System.out.println("Данные не занесены, ошибка при выполнении....!!!");
			e.printStackTrace();
		} finally {
			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
			System.out.println("Закрыли соединение с БД после внесения данных...");
		}
	}


	/*
	    метод для внесения данных в таблицу выбранных с таблицы Excel
	 */
//	public void insertQuerySQL() {
//		try (Connection connection = exmpCon.getPostConnection()) {
//			try (PreparedStatement stm = connection.prepareStatement(insertSQL)) {
//
//				ReadExcelData read = new ReadExcelData();
//				List<String> list = read.getDataStringIntegerDate(0);
//
//				for (String value : list) {
//					stm.setString(1, value);  // вставка значений
//					stm.addBatch();
//					stm.executeUpdate();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
//			System.out.println("Закрыли соединение с БД после внесения данных...");
//		}
//	}

	/*
	метод для просмотра внесённых данных в таблицу
	*/
//	public void viewData() throws SQLException {
//		try (Connection connection = exmpCon.getPostConnection()) {
//			try (PreparedStatement stm = connection.prepareStatement(selectSQL)) {
//				ResultSet result =
//					stm.executeQuery(selectSQL);
//				while (result.next()) {
//					String value = result.getString("value");
//					System.out.println(value);
//				}
//
//				System.out.println("Закрыли соединение с БД после просмотра данных...");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("ВСТАВИЛИ ДАННЫЕ в БД");
//			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
//			System.out.println("Закрыли соединение с БД после удаления данных...");
//		}
//	}

	/*
	метод для удаления внесённых данных в таблице
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