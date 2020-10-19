package com.domgo.commons.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * DateUtils be used to operate time contains jdk1.8
 * @author Mr.Domgo
 * @Date 2020-9-4
 * @Since 1.0
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

	/**
	 * " 00:00:00"
	 */
	public static final String BEGINTIME = " 00:00:00";
	
	/**
	 * " 23:59:59"
	 */
	public static final String ENDTIME = " 23:59:59";
	
	/**
	 * <pre>
	 * 20200904
	 * </pre>
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	
	/**
	 * <pre>
	 * 2020-09-04
	 * </pre>
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * <pre>
	 * 202009
	 * </pre>
	 */
	public static final String YYYYMM = "yyyyMM";
	
	/**
	 * <pre>
	 * 2020-09
	 * </pre>
	 */
	public static final String YYYY_MM = "yyyy-MM";
	
	/**
	 * <pre>
	 * 20200904053010
	 * </pre>
	 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/**
	 * <pre>
	 * 2020-09-04 05:30:10
	 * </pre>
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * <pre>
	 * 20200904T053010
	 * </pre>
	 */
	public static final String YYYYMMDDTHHMMSS = "yyyyMMdd'T'HHmmss";
	
	/**
	 * <pre>
	 * 2020-09-04T05:30:10.241Z
	 * </pre>
	 */
	public static final String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/**
	 * <p>This method convert character to date.</p>
	 * @param date
	 * @return
	 */
	public static LocalDate parseLocalDate(String date){
		return parseLocalDate(date, YYYY_MM_DD);
	}
	
	/**
	 * <p>This method convert character to date.</p>
	 * @param date
	 * @return
	 */
	public static LocalDate parseLocalDate(String date, String parsePattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parsePattern);
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	
	/**
	 * <p>This method convert character to date-time.</p>
	 * @param datetimecs
	 * @return
	 */
	public static LocalDateTime parseLocalDateTime(String datetimecs){
		return parseLocalDateTime(datetimecs, YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * <p>This method convert character to date-time.</p>
	 * @param datetimecs
	 * @param parsePattern
	 * @return
	 */
	public static LocalDateTime parseLocalDateTime(String datetimecs, String parsePattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parsePattern);
		LocalDateTime localDateTime = LocalDateTime.parse(datetimecs, formatter);
		return localDateTime;
	}
	
	/**
	 * <p>This method convert date to character.</p>
	 * @param date
	 * @param formatPattern
	 * @return
	 */
	public static String formatLocalDate(LocalDate date, String formatPattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
		String datecs = date.format(formatter);
		return datecs;
	}
	
	/**
	 * <p>This method convert date to character.</p>
	 * @param date
	 * @return
	 */
	public static String formatLocalDate(LocalDate date){
		return formatLocalDate(date, YYYY_MM_DD);
	}

	/**
	 * <p>This method convert date-time to character.</p>
	 * @param datetime
	 * @param formatPattern
	 * @return
	 */
	public static String formatLocalDateTime(LocalDateTime datetime, String formatPattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
		String datetimecs = datetime.format(formatter);
		return datetimecs;
	}
	
	/**
	 * <p>This method convert date-time to character.</p>
	 * @param datetime
	 * @return
	 */
	public static String formatLocalDateTime(LocalDateTime datetime){
		return formatLocalDateTime(datetime, YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * <p>This method get first day of the previous month at the current date.</p>
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfLastMonth(LocalDate date) {
		return formatLocalDate(date.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth())) + BEGINTIME;
	}
	
	/**
	 * <p>This method get last day of the previous month at the current date.</p>
	 * @param date
	 * @return
	 */
	public static String getLastDayOfLastMonth(LocalDate date) {
		return formatLocalDate(date.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth())) + ENDTIME;
	}

	/**
	 * <p>This method get the day before current date.</p>
	 * @param date
	 * @return
	 */
	public static String getYesterDay(LocalDate date) {
		return formatLocalDate(date.minusDays(1));
	}
	
	/**
	 * <p>This method get the begin day before current date-time.</p>
	 * @param date
	 * @return
	 */
	public static String getYesterDayBegin(LocalDate date) {
		return formatLocalDate(date.minusDays(1)) + BEGINTIME;
	}
	
	/**
	 * <p>This method get the end day before current date-time.</p>
	 * @param date
	 * @return
	 */
	public static String getYesterDayEnd(LocalDate date) {
		return formatLocalDate(date.minusDays(1)) + ENDTIME;
	}
	
	/**
	 * <p>This method minus to date.</p>
	 * @param date
	 * @param years
	 * @param months
	 * @param days
	 * @return
	 */
	public static LocalDate minus(LocalDate date, long years, long months, long days) {
		return date.minusYears(years).minusMonths(months).minusDays(days);
	}
	
	/**
	 * <p>This method minus to date-time.</p>
	 * @param datetime
	 * @param years
	 * @param months
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	public static LocalDateTime minus(LocalDateTime datetime, long years, long months, long days, long hours, long minutes, long seconds) {
		return datetime.minusYears(years).minusMonths(months).minusDays(days).minusHours(hours).minusMinutes(minutes).minusSeconds(seconds);
	}
	
	/**
	 * <p>This method plus to date.</p>
	 * @param date
	 * @param years
	 * @param months
	 * @param days
	 * @return
	 */
	public static LocalDate plus(LocalDate date, long years, long months, long days) {
		return date.plusYears(years).plusMonths(months).plusDays(days);
	}
	
	/**
	 * <p>This method plus to date-time.</p>
	 * @param datetime
	 * @param years
	 * @param months
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	public static LocalDateTime plus(LocalDateTime datetime, long years, long months, long days, long hours, long minutes, long seconds) {
		return datetime.plusYears(years).plusMonths(months).plusDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
	}
	
}
