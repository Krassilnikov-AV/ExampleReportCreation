/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package example;

import java.io.IOException;
import java.nio.file.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Класс ExDateClass
 */
public class ExDateClass {

	public static void main(String[] args) throws ParseException, IOException {
		/*
		преобразование строкового апрдставоепния датф в формат даты
		 */

		String dateString = "160120201115";
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		System.out.println(dateFormat.parse(dateString));

		/*
		текущая дата и время в определенном формате
		 */

		DateFormat dateSystemFormat = new SimpleDateFormat("yyMMddHHmm");
		System.out.print("представление в системном формате текущей даты: ");
		System.out.println(dateSystemFormat.format(System.currentTimeMillis()));
		/*

		 */
		Files.lines(Paths.get("file.txt")).forEach(line -> {
				String[] lineParts = line.split(" ");

				if (isDateAfter12December(lineParts[0])) {
					System.out.println(lineParts[1]);
				}
			}
		);
	}

	public static boolean isDateAfter12December(String dataString) {
		return LocalDateTime.parse(dataString, DateTimeFormatter.ofPattern("yyMMddHHmm")).isAfter(LocalDateTime.of(2016, 12, 12, 0, 0));
	}
}