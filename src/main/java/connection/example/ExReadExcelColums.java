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


	private LinkedList<Object> columnStrData;

	/**
	 * метод для построения и просмотра считанных данных
	 */

	public void buildingTable() {
		ExReadExcelColums exr = new ExReadExcelColums();

		columnIndex = codeGroup;
		List<Object> colCodeGroup = exr.getDataStringIntegerDate(columnIndex);

		columnIndex = discipline;
		List<Object> colProgram = exr.getDataStringIntegerDate(columnIndex);

		columnIndex = dateStart;
//		List<?> colDataStart = exr.getDataStringIntegerDate(columnIndex);
//		Object codGr = null;


		List<Integer> gr = new LinkedList<>();
		for (Object valInt : colCodeGroup) {
//			int i = (Integer) valInt;
			Integer i = Integer.valueOf((String) valInt);
			gr.add(i);
		}

		List<String> pr = new LinkedList<>();
		for (Object obj : colProgram) {
			String str = obj.toString();
			pr.add(str);
		}

		//		for (codGr: colCodeGroup) {
//			gr.add((Integer) codGr);
//		}
//		for (prog:colProgram) {
//			pr.add((String)prog);
//		}
//				for (Object DataStart : colDataStart)

//					System.out.println(iter+"Группа: " + codGr + " Программа: " + prog + " Начало: " + DataStart);

		System.out.println("Группа: " + gr + " Программа: " + pr);
//		System.out.println(" Программа: " + pr);
//		columnIndex = twoColumn;
//		List<?> colProgram = exr.getDataStringIntegerDate(columnIndex);
//		System.out.println(colCodeGroup, colProgram);
	}

	/**
	 * метод должен получить определённые номера колонок, вызвать метод, который обработает тип ячейки
	 * и вернуть считанные данные
	 */
	Cell cell = null;

	public List<Object> getDataStringIntegerDate(int columnIndex) {

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
						SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
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