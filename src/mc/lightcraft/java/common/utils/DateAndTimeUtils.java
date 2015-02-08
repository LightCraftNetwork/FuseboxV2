package mc.lightcraft.java.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAndTimeUtils {

	private DateAndTimeUtils() {
	}

	private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

	/**
	 * Convent a long value into a date containing a time-stamp
	 * 
	 * @param time
	 *            the long value you wish to convent
	 * @return the time-stamped date in string form
	 */
	public static String longToTimedDate(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(Long.valueOf(time));
	}

	/**
	 * Get todays date in string form WITHOUT a time-stamp
	 * 
	 * @return todays date as a string
	 */
	public static String currentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DAY);
		return sdf.format(cal.getTime());
	}

	/**
	 * Get the current date and time in sting form
	 * 
	 * @return the current date and time as a string
	 */
	public static String currentTimedDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
