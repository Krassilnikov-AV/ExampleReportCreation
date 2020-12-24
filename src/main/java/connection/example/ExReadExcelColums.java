/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package connection.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.sql.Time;
import java.text.*;
import java.util.*;

/**
 * Класс ExReadExcelColums - класс, считывающий необходимые колонки с
 * excel файла для всавки в отчёт расписание
 */
public class ExReadExcelColums {
	/**
	 * @param args
	 * @throws IOException
	 */
// выбрать столбец для чтения данных (для проверки/тестировниая)
//	final int code = 0;    // код (строка)
//	final int gpoupID = 2;   // ID группы  (число)
	final int codeGroup = 3;   //+1 код группы  (число)
	//	final int group = 4;   // название группы (строка)
	final int dateStart = 5;   // +3 дата начала (дата)
	final int timeStart = 6;   // +4 время начала (время)
	final int dateEnd = 7;   // +5 дата завершения (дата)
	final int timeEnd = 8;   // +6 время завершения (время)
	final int classID = 9;   // ID аудитории (число)
	final int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	final int typeLearn = 11;   // +8 тип занятия (строка)
	final int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	//	final int courseID = 13;   // +2.1 ID курса (число) -
	final int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	final int period = 15;   // период (число)
	final int teacherID = 16;   // ID преподавателя (число)
	final int teacher = 17;   // +9 преподаватель (строка)
	final int periodDay = 18;   // период дней(число)
	final int academHour = 19;   // академических часов (число)
	final int academRecord = 20;   // академических записей (число)


	String fileName = "Primer_raspisania.xlsx";
	//String fileName = "fileToRead";
//	private LinkedList<String> columndata;


	/**
	 * * имя столбцов д/л group: groupid, groupcode,
	 * *    programm, datestart, timestart, dateend,
	 * *    timeend, classrum, typelessons
	 */
//
//	private LinkedList<<Integer><Integer><String><Date><Time><Date><Time><String><String>> dates;

	static int columnIndex = 0;
	static int columnInt = 1;

	// основной метод класса для проверки считывания данных с таблицы
	public static void main(String[] args) throws IOException {

		//	ReadExcelData code = new ReadExcelData();
		//	code.getDataStringIntegerDate(doc.ReadExcelData.timeEnd);
		//code.getDataTime(ReadExcelData.timeEnd);
//		try {
//			Integer i1 = new Integer("20349");
//			System.out.println(i1);
//		}catch (NumberFormatException e) {
//			System.err.println("Неверный формат строки!");
//		}
//		ExReadExcelColums exr = new ExReadExcelColums();
//		exr.getDataStringIntegerDate(14);
		ExReadExcelColums exr = new ExReadExcelColums();
		exr.getDataStringIntegerDate(columnIndex, columnInt);
	}

/**
 * ЗАДАНИЕ
 * нужно List<<int><int><string><date><time><date><time><string><string>>
 */
// метод для получения строчных, целочисленных данных и даты в формате "число.месяц.год"

	/**
	 * ! добавить метод, который будет получать путь и номер колонки, который вызывается в сервлете
	 */

	private LinkedList<Integer> groupid;
//	private LinkedList<String> groupcode;
	private LinkedList<String> programm;
	private LinkedList<Date> datestart;
	private LinkedList<Time> timestart;
	private LinkedList<Date> dateend;
	private LinkedList<Time> timeend;
	private LinkedList<String> classrum;
	private LinkedList<String> typelessons;


	//	private LinkedList<String> columndata;
	List<String> prog = null;
	private List<Integer> groupcode;
	ExScheduleToTable exs = new ExScheduleToTable();

	Cell cell = null;
	// переменная, которую возвращает метод  getDataStringIntegerDate(int columnIndex, int columnInt)
	List<>

	/**
	 *
	 * метод должен получить определённые номера колонок, вызвать метод, который обработает тип ячейки
	 * и вернуть считанные данные
	 */


	public List<?> getDataStringIntegerDate(int columnIndex, int columnInt) {

		try {
			File f = new File(fileName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
//			columndata = new LinkedList<String>();
			prog = exs.getProgramm();         // пример вызова с экземпляра класса
			prog = new LinkedList<>();         // созданный в классе
			groupcode = new LinkedList<String>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();

					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						// вставить метод выбора типа данных
						selectTypeData(columnIndex, columnInt);
					}
				}
			}
			ios.close();
			Iterator it = prog.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prog;
	}

	//метод для выбора типа данных считываемых с файла
	private void selectTypeData(int columnIndex, int columnInt) {

		if (cell.getColumnIndex() == columnIndex ||
			cell.getColumnIndex() == columnInt) {
			// соответствие индекса столбца
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {  // получение данных даты
						SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
						prog.add(sdfDate.format(cell.getDateCellValue()));
					} else { // получение целочисленных данных
						groupcode.add((int) cell.getNumericCellValue() + "");
					}
					break;
				case Cell.CELL_TYPE_STRING: // получение строчных данных
					prog.add(cell.getStringCellValue());
					break;
			}
		}
	}

	//	public ExScheduleToTable getDataStringIntegerDate(int discipline,
//													  int dateStart, int timeStart, int dateEnd,
//													  int timeEnd, int clasRum, int typeLearn)
//	public ExScheduleToTable getDataStringIntegerDate(int discipline) {

//		ExScheduleToTable exsch = new ExScheduleToTable();
//
//		try {
//			File f = new File(fileName);
//			FileInputStream ios = new FileInputStream(f);
//			XSSFWorkbook workbook = new XSSFWorkbook(ios);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//			Iterator<Row> rowIterator = sheet.iterator();
//
//			while (rowIterator.hasNext()) {
//				Row row = rowIterator.next();
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//
//					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
////						try {
////							if (cell.getColumnIndex() == codeGroup) {
//////								String str = ;
////								Integer int = new Integer(cell.getStringCellValue());
////								exsch.getGroupcode().add();
////							}
////						} catch (NumberFormatException e) {
////							System.err.println("Неверный формат строки!");
////						}
//						if (cell.getColumnIndex() == discipline) {
//							exsch.getProgramm().add(cell.getStringCellValue());
//						}
//
////						if (cell.getColumnIndex() == dateStart) {
////							SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
////							exsch.getDatestart().add(sdfDate.get2DigitYearStart());
////						}
////						if (cell.getColumnIndex() == timeStart) {
////							SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm");
////							exsch.getTimestart().add((Time) sdfDate.get2DigitYearStart());
////						}
////
////						if (cell.getColumnIndex() == dateEnd) {
////							SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
////							exsch.getDatestart().add(sdfDate.get2DigitYearStart());
////						}
////						if (cell.getColumnIndex() == timeEnd) {
////							SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm");
////							exsch.getTimeend().add((Time) sdfDate.get2DigitYearStart());
////						}
//
////						if (cell.getColumnIndex() == clasRum) {
////							exsch.getClassrum().add(cell.getStringCellValue());
////						}
////						if (cell.getColumnIndex() == typeLearn) {
////							exsch.getClassrum().add(cell.getStringCellValue());
////						}
//					}
//				}
//			}
//			ios.close();
////			Iterator itGroupCode = exsch.getGroupcode().iterator();
//			Iterator itProgram = exsch.getProgramm().iterator();
////			Iterator itDateStart = exsch.getDatestart().iterator();
////			Iterator itDateEnd = exsch.getDateend().iterator();
//			while (
//				//itGroupCode.hasNext() &&
//				itProgram.hasNext()) //&&
////				itDateStart.hasNext() &&
////				itDateEnd.hasNext()) {
//				System.out.println(" " + itProgram + " ");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return exsch;
}