/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package example.exRead;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

/**
 * Класс ExReadExcelData
 */
public class ExReadExcelData {

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


	String fileName = "Primer_raspisania1.xlsx";
	//String fileName = "fileToRead";
//	private LinkedList<String> columnStrData;


	/**
	 * * имя столбцов д/л group: groupid, groupcode,
	 * *    programm, datestart, timestart, dateend,
	 * *    timeend, classrum, typelessons
	 */

	static int columnIndex = 6;
	static int columnInt = 1;

	// основной метод класса для проверки считывания данных с таблицы
	public static void main(String[] args) throws IOException {
//		ExReadExcelColums exr = new ExReadExcelColums();

		ExReadExcelData exr = new ExReadExcelData();
//		exr.buildingTable();
//		exr.getDate(columnIndex);
//		exr.getTime(columnIndex);
	}

	private LinkedList<Integer> groupid;
	//	private LinkedList<String> groupcode;
	private LinkedList<String> programm;
	private LinkedList<Date> datestart;
	private LinkedList<Time> timestart;
	private LinkedList<Date> dateend;
	private LinkedList<Time> timeend;
	private LinkedList<String> classrum;
	private LinkedList<String> typelessons;


	private LinkedList<Date> columndata;
	private List<Long> columnTime;

	/**
	 * метод для построения таблицы из прчитанных данных и просмотра данных
	 * Вопросы:
	 * 1. формат даты считывается как ссылка
	 */

//	public void buildingTable() {
//		ExReadExcelColums exr = new ExReadExcelColums();
//
//		columnIndex = codeGroup;
//		List<?> colCodeGroup = exr.getDataStringDate(columnIndex);
//
//		columnIndex = discipline;
//		List<?> colProgram = exr.getDataStringDate(columnIndex);
//
//		columnIndex = dateStart;
//		List<?> colDataStart = exr.getDataStringDate(columnIndex);
////		String val = "dd.MM.yyyy";
////		SimpleDateFormat sdfDate = new SimpleDateFormat(val);
//
//		Iterator itCode = colCodeGroup.iterator();
//		Iterator itProg = colProgram.iterator();
//		Iterator itDateSt = colDataStart.iterator();
//
//		while (itCode.hasNext() &&
//			itProg.hasNext() &&
//			itDateSt.hasNext()) {
//			System.out.println(itCode.next() +
//				"   " + itProg.next() + "     " + itDateSt);
//		}
//	}

	/**
	 * метод должен получить определённые номера колонок, вызвать метод, который обработает тип ячейки
	 * и вернуть считанные данные
	 */
	Cell cell = null;

	public List<Date> getDate(int columnIndex) {

		try {
			File f = new File(fileName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columndata = new LinkedList<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца
							switch (cell.getCellType()) {
								case Cell.CELL_TYPE_NUMERIC:
									Date date = cell.getDateCellValue();
									columndata.add(date);
							}
							break;
						}
					}
				}
			}
			ios.close();
			Iterator it = columndata.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndata;
	}

//	public List<Long> getTime(int columnIndex) {
//
//		try {
//			File f = new File(fileName);
//			FileInputStream ios = new FileInputStream(f);
//			XSSFWorkbook workbook = new XSSFWorkbook(ios);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//			Iterator<Row> rowIterator = sheet.iterator();
//			columnTime = new LinkedList<>();
//
//			while (rowIterator.hasNext()) {
//				Row row = rowIterator.next();
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//
//					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
//						if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца
//							switch (cell.getCellType()) {
//								case Cell.CELL_TYPE_NUMERIC:
//									Date date = cell.getDateCellValue();
//									long time = cell.getDateCellValue().getTime();
//									columnTime.add(time);
//							}
//							break;
//						}
//					}
//				}
//			}
//			ios.close();
//			Iterator it = columnTime.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return columnTime;
//	}

//	private void selectTypeData(int columnIndex) {
//
//		if (cell.getColumnIndex() == columnIndex) {
//			// соответствие индекса столбца
//			switch (cell.getCellType()) {
//				case Cell.CELL_TYPE_NUMERIC:
//					if (DateUtil.isCellDateFormatted(cell)) {  // п
//						String val = "dd.MM.yyyy";
//
//						SimpleDateFormat sdfDate = new SimpleDateFormat(val);
//						columnStrData.add(sdfDate.format(cell.getDateCellValue()));
//
//					} else { // получение целочисленных данных
//						columnStrData.add((int) cell.getNumericCellValue() + "");
//					}
//					break;
//				case Cell.CELL_TYPE_STRING: // получение строчных данных
//					columnStrData.add(cell.getStringCellValue());
//					break;
//			}
//		}
//	}
}
