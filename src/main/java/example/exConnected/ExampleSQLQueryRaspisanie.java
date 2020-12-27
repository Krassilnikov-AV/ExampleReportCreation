/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */
package example.exConnected;

import example.exRead.ExReadExcelColums;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	// выбрать столбец для чтения данных (для проверки/тестировниая)
	final static int code = 0;    // код (строка)
	final static int divID = 1;   // ID подразделения (число)
	final static int gpoupID = 2;   // ID группы  (число)
	private int codeGroup = 3;   //+1 код группы  (число)
	final static int group = 4;   // название группы (строка)
	private int dateStart = 5;   // +3 дата начала (дата)
	final static int timeStart = 6;   // +4 время начала (время)
	final static int dateEnd = 7;   // +5 дата завершения (дата)
	final static int timeEnd = 8;   // +6 время завершения (время)
	final static int classID = 9;   // ID аудитории (число)
	private int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	final static int typeLearn = 11;   // +8 тип занятия (строка)
	final static int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	final static int courseID = 13;   // +2.1 ID курса (число) -
	private int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	final static int period = 15;   // период (число)
	final static int teacherID = 16;   // ID преподавателя (число)
	final static int teacher = 17;   // +9 преподаватель (строка)
	final static int periodDay = 18;   // период дней(число)
	final static int academHour = 19;   // академических часов (число)
	final static int academRecord = 20;   // академических записей (число)

	/**
	 * Команда INSERT INTO <table_name> в SQL отвечает за добавление данных в таблицу:
	 * <p>
	 * INSERT INTO <table_name> (<col_name1>, <col_name2>, <col_name3>, …)
	 * VALUES (<value1>, <value2>, <value3>, …);
	 */
	String insertSQL = "INSERT INTO schedule(program, codgroup, auditorium, datestart) VALUES(?, ?, ?, ?)";

	//	String insertSQL = "INSERT INTO schedule(program) VALUES(?)";
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

				LinkedList<String> listPr = (LinkedList<String>) exread.getDataStringDate(discipline);
				LinkedList<String> listGr = (LinkedList<String>) exread.getDataStringDate(codeGroup);
				LinkedList<String> listAudit = (LinkedList<String>) exread.getDataStringDate(clasRum);
				LinkedList<String> listDateSt = (LinkedList<String>) exread.getDataStringDate(dateStart);

				for (String prog : listPr) {
					stm.setString(1, prog);  // вставка программы
					for (String code : listGr) {
						stm.setString(2, code);  // вставка кола группы
						for (String clas : listAudit) {
							stm.setString(3, clas);  // вставка номера аудитории
						}
						for (String ds : listDateSt) {
//							Date date=new SimpleDateFormat("dd.MM.yyyy").parse(ds);
////							ds = date.toString();
							stm.setString(4, ds);
						}
					}
					stm.addBatch();
					stm.executeUpdate();
				}

			} catch (Exception e) {
				System.out.println("Данные не занесены, ошибка при выполнении....!!!");
				e.printStackTrace();
			} finally {
				System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
				System.out.println("Закрыли соединение с БД после внесения данных...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static java.sql.Date getCurrentDate() {
		java.util.Date date = new java.util.Date();
		return new java.sql.Date(date.getTime());
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