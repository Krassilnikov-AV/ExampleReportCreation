/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import doc.ReadExcelData;

import java.sql.Time;
import java.util.*;

/**
 * Класс ExScheduleToTable - класс для выбора определенных столбцов
 * и подготовки к считыванию в классе  ExReadExcelColums
 */
public class ExScheduleToTable<A, B> {
	/**
	 * // выбрать столбец для чтения данных (для проверки/тестировниая)
	 * final static int code = 0;    // код (строка)
	 * final static int divID = 1;   // ID подразделения (число)
	 * final static int gpoupID = 2;   // ID группы  (число)
	 * final static int codeGroup = 3;   //+1 код группы  (число)
	 * final static int group = 4;   // название группы (строка)
	 * final static int dateStart = 5;   // +3 дата начала (дата)
	 * final static int timeStart = 6;   // +4 время начала (время)
	 * final static int dateEnd = 7;   // +5 дата завершения (дата)
	 * final static int timeEnd = 8;   // +6 время завершения (время)
	 * final static int classID = 9;   // ID аудитории (число)
	 * final static int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	 * final static int typeLearn = 11;   // +8 тип занятия (строка)
	 * final static int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	 * final static int courseID = 13;   // +2.1 ID курса (число) -
	 * final static int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	 * final static int period = 15;   // период (число)
	 * final static int teacherID = 16;   // ID преподавателя (число)
	 * final static int teacher = 17;   // +9 преподаватель (строка)
	 * final static int periodDay = 18;   // период дней(число)
	 * final static int academHour = 19;   // академических часов (число)
	 * final static int academRecord = 20;   // академических записей (число)
	 */

	public ExScheduleToTable(List<String> program) {
		this.program = program;
	}


	private List<Integer> groupid;
	private List<Integer> groupcode;

	List<String> program;

	public List<?> addData() {

	}

//	List<Date> datestart;
//
//	private List<Time> timestart;
//	private List<Date> dateend;
//	private List<Time> timeend;
//	private List<String> classrum;
//	private List<String> typelessons;

}