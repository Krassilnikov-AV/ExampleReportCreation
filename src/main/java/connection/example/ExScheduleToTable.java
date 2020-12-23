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
public class ExScheduleToTable {
/**
	// выбрать столбец для чтения данных (для проверки/тестировниая)
	final static int code = 0;    // код (строка)
	final static int divID = 1;   // ID подразделения (число)
	final static int gpoupID = 2;   // ID группы  (число)
	final static int codeGroup = 3;   //+1 код группы  (число)
	final static int group = 4;   // название группы (строка)
	final static int dateStart = 5;   // +3 дата начала (дата)
	final static int timeStart = 6;   // +4 время начала (время)
	final static int dateEnd = 7;   // +5 дата завершения (дата)
	final static int timeEnd = 8;   // +6 время завершения (время)
	final static int classID = 9;   // ID аудитории (число)
	final static int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	final static int typeLearn = 11;   // +8 тип занятия (строка)
	final static int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	final static int courseID = 13;   // +2.1 ID курса (число) -
	final static int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	final static int period = 15;   // период (число)
	final static int teacherID = 16;   // ID преподавателя (число)
	final static int teacher = 17;   // +9 преподаватель (строка)
	final static int periodDay = 18;   // период дней(число)
	final static int academHour = 19;   // академических часов (число)
	final static int academRecord = 20;   // академических записей (число)
*/
	private List<Integer> groupid;
	private List<Integer> groupcode;
	private List<Integer> valInt;
	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Integer> getvalInt() {
		return valInt;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='Int', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Integer>', type='java.util.List<java.lang.Integer>', typeQualifiedName='java.util.List<java.lang.Integer>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setvalInt(List<Integer> anInt) {
		valInt = anInt;
	}

	private List<String> programm;
	private List<Date> datestart;

	private List<Time> timestart;
	private List<Date> dateend;
	private List<Time> timeend;
	private List<String> classrum;
	private List<String> typelessons;


	/**
	 * Возвращает
	 *
	 * @return
	 */

	public List<Integer> getGroupid() {
		return groupid;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='groupid', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Integer>', type='java.util.List<java.lang.Integer>', typeQualifiedName='java.util.List<java.lang.Integer>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setGroupid(List<Integer> groupid) {
		this.groupid = groupid;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Integer> getGroupcode() {
		return groupcode;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='groupcode', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Integer>', type='java.util.List<java.lang.Integer>', typeQualifiedName='java.util.List<java.lang.Integer>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setGroupcode(List<Integer> groupcode) {
		this.groupcode = groupcode;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<String> getProgramm() {
		return programm;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='programm', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String>', type='java.util.List<java.lang.String>', typeQualifiedName='java.util.List<java.lang.String>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setProgramm(List<String> programm) {
		this.programm = programm;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Date> getDatestart() {
		return datestart;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='datestart', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Date>', type='java.util.List<java.util.Date>', typeQualifiedName='java.util.List<java.util.Date>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setDatestart(List<Date> datestart) {
		this.datestart = datestart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Time> getTimestart() {
		return timestart;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='timestart', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Time>', type='java.util.List<java.sql.Time>', typeQualifiedName='java.util.List<java.sql.Time>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setTimestart(List<Time> timestart) {
		this.timestart = timestart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Date> getDateend() {
		return dateend;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='dateend', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Date>', type='java.util.List<java.util.Date>', typeQualifiedName='java.util.List<java.util.Date>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setDateend(List<Date> dateend) {
		this.dateend = dateend;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Time> getTimeend() {
		return timeend;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='timeend', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='Time>', type='java.util.List<java.sql.Time>', typeQualifiedName='java.util.List<java.sql.Time>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setTimeend(List<Time> timeend) {
		this.timeend = timeend;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<String> getClassrum() {
		return classrum;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='classrum', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String>', type='java.util.List<java.lang.String>', typeQualifiedName='java.util.List<java.lang.String>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setClassrum(List<String> classrum) {
		this.classrum = classrum;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<String> getTypelessons() {
		return typelessons;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='typelessons', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String>', type='java.util.List<java.lang.String>', typeQualifiedName='java.util.List<java.lang.String>', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setTypelessons(List<String> typelessons) {
		this.typelessons = typelessons;
	}

	@Override
	public String toString() {
		return "ExScheduleToTable{" +
			"groupid=" + groupid +
			", groupcode=" + groupcode +
			", programm=" + programm +
			", datestart=" + datestart +
			", timestart=" + timestart +
			", dateend=" + dateend +
			", timeend=" + timeend +
			", classrum=" + classrum +
			", typelessons=" + typelessons +
			'}';
	}
}