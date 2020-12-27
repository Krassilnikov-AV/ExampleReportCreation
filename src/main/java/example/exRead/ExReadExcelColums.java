/*
 * Copyright (c) 2020 Tander, All Rights Reserved.
 */

package example.exRead;

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


	String fileName = "Primer_raspisania1.xlsx";
	//String fileName = "fileToRead";
//	private LinkedList<String> columnStrData;


	/**
	 * * имя столбцов д/л group: groupid, groupcode,
	 * *    programm, datestart, timestart, dateend,
	 * *    timeend, classrum, typelessons
	 */
//
//	private LinkedList<<Integer><Integer><String><Date><Time><Date><Time><String><String>> dates;

	static int columnIndex = 5;
	static int columnInt = 1;

	// основной метод класса для проверки считывания данных с таблицы
	public static void main(String[] args) throws IOException {
		ExReadExcelColums exr = new ExReadExcelColums();
		exr.buildingTable();
//		exr.getDataStringDate(5);
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


	private LinkedList<String> columnStrData;

	/**
	 * метод для построения таблицы из прчитанных данных и просмотра данных
	 * Вопросы:
	 * 1. формат даты считывается как ссылка
	 */

	public void buildingTable() {
		ExReadExcelColums exr = new ExReadExcelColums();

		columnIndex = codeGroup;
		List<?> colCodeGroup = exr.getDataStringDate(columnIndex);

		columnIndex = discipline;
		List<?> colProgram = exr.getDataStringDate(columnIndex);

		columnIndex = dateStart;
		List<?> colDataStart = exr.getDataStringDate(columnIndex);
//		String val = "dd.MM.yyyy";
//		SimpleDateFormat sdfDate = new SimpleDateFormat(val);

		Iterator itCode = colCodeGroup.iterator();
		Iterator itProg = colProgram.iterator();
		Iterator itDateSt = colDataStart.iterator();

		while (itCode.hasNext() &&
			itProg.hasNext() &&
			itDateSt.hasNext()) {
			System.out.println(itCode.next() +
				"   " + itProg.next() + "     " + itDateSt);
		}
	}

	/**
	 * метод должен получить определённые номера колонок, вызвать метод, который обработает тип ячейки
	 * и вернуть считанные данные
	 */
	Cell cell = null;

	public List<String> getDataStringDate(int columnIndex) {

		try {
			File f = new File(fileName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columnStrData = new LinkedList<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();

					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						// вставить метод выбора типа данных
						selectTypeData(columnIndex);
					}
				}
			}
			ios.close();
//			Iterator it = columnStrData.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnStrData;
	}

	//метод для выбора типа данных считываемых с файла
	private void selectTypeData(int columnIndex) {

		if (cell.getColumnIndex() == columnIndex) {
			// соответствие индекса столбца
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {  // получение данных даты
						String val = "dd.MM.yyyy";
						SimpleDateFormat sdfDate = new SimpleDateFormat(val);
						columnStrData.add(sdfDate.format(cell.getDateCellValue()));
					} else { // получение целочисленных данных
						columnStrData.add((int) cell.getNumericCellValue() + "");
					}
					break;
				case Cell.CELL_TYPE_STRING: // получение строчных данных
					columnStrData.add(cell.getStringCellValue());
					break;
			}
		}
	}
}