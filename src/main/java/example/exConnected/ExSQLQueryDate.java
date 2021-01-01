/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package example.exConnected;


import example.exRead.ExReadExcelData;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.Date;
import java.util.*;

/**
 * Класс ExSQLQueryDate
 */
public class ExSQLQueryDate {


	// выбрать столбец для чтения данных (для проверки/тестировниая)
	final static int code = 0;    // код (строка)
	final static int divID = 1;   // ID подразделения (число)
	final static int gpoupID = 2;   // ID группы  (число)
	private int codeGroup = 3;   //+1 код группы  (число)
	final static int group = 4;   // название группы (строка)
	private int dateStart = 5;   // +3 дата начала (дата)
	private int timeStart = 6;   // +4 время начала (время)
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
	String insertTimeStartSQL = "INSERT INTO schedule(timestart) VALUES(?)";
	String insertDateStartSQL = "INSERT INTO schedule(datestart) VALUES(?)";

	//	String insertSQL = "INSERT INTO schedule(program) VALUES(?)";
	String deletedSQL = "DELETE FROM schedule";
//	String insertSQL = "insert into group(groupid) values (?)";
//	String selectSQL = "SELECT programm FROM raspisanie";
//	String deletedSQL = "DELETE FROM group";

	ExampleConnection exmpCon = new ExampleConnection();

	ExReadExcelData exread = new ExReadExcelData();
	List<Date> listDateSt = exread.getDate(dateStart);
	List<Date> listTime = exread.getDate(timeStart);

	public void insertStartDateSQL() throws IOException, SQLException {
		try (Connection connection = exmpCon.getPostConnection()) {
			try (PreparedStatement stm = connection.prepareStatement(insertDateStartSQL)) {
				for (Date d : listDateSt) {
					stm.setTimestamp(1, new Timestamp(d.getTime()));
					stm.addBatch();
					stm.executeUpdate();
				}
			} catch (Exception e) {
				System.out.println("Данные не занесены, ошибка при выполнении....!!!");
				e.printStackTrace();
			}
		} finally {
			System.out.println("-/*/--/*Закрыли соединение с БД после внесения (не внесения) начала даты занятий..");
		}
	}

	public void insertStartTimeSQL() throws IOException, SQLException {
		try (Connection connection = exmpCon.getPostConnection()) {
			try  {
				PreparedStatement stm = connection.prepareStatement(insertTimeStartSQL);
				for (Date t : listTime) {
					stm.setTime(1, new Time(t.getTime()));
					stm.addBatch();
				}
				stm.executeUpdate();
			} catch (Exception e) {
				System.out.println("Данные не занесены, ошибка при выполнении....!!!");
				e.printStackTrace();
			}
		} finally {
			System.out.println("-/*/--/*Закрыли соединение с БД после внесения (не внесения) начала времени занятий..");
		}
	}

//	private java.sql.Date getCurrentDate(Date d) {
//		d = new java.util.Date();
//		return new java.sql.Date(d.getTime());
//	}


//	public void insertQuerySQL() throws IOException, SQLException {
//		try (Connection connection = exmpCon.getPostConnection()) {
//			try (PreparedStatement stm = connection.prepareStatement(insertDateStartSQL)) {
//				for (Date d : listDateSt) {
//					stm.setTimestamp(1, new Timestamp(d.getTime()));
////					for (Date t : listTime)
////						stm.setTime(2, new Time(t.getTime()));
//
//					stm.addBatch();
//					stm.executeUpdate();
//				}
//			} catch (Exception e) {
//				System.out.println("Данные не занесены, ошибка при выполнении....!!!");
//				e.printStackTrace();
//			}
//			try (PreparedStatement stm = connection.prepareStatement(insertTimeStartSQL)) {
////				for (Date d : listDateSt) {
////					stm.setTimestamp(1, new Timestamp(d.getTime()));
//					for (Date t : listTime) {
//						stm.setTime(1, new Time(t.getTime()));
//
//					stm.addBatch();
//					stm.executeUpdate();
//				}
//			} catch (Exception e) {
//				System.out.println("Данные не занесены, ошибка при выполнении....!!!");
//				e.printStackTrace();
//			}
//
//			finally {
//				System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
//				System.out.println("Закрыли соединение с БД после внесения/не внесения данных...");
//			}
//		}
//	}

//	private java.sql.Date getCurrentDate(Date d) {
//		d = new java.util.Date();
//		return new java.sql.Date(d.getTime());
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

//	long tLong = 0;
//						DateFormat smdf = new SimpleDateFormat("HH:mm");
//						String tStr = smdf.format(t);
//						try {
//							tLong = Long.valueOf(tStr);
//						} catch (NumberFormatException e) {
//							System.err.println("Неверный формат строки!");
//						}