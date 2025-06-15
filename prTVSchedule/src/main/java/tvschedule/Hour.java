package tvschedule;

import java.util.Objects;

/**
 * Class that represents instants of time in a day, indicating hour y minute, 
 * always in the range [0..23] and [0..59], respectively.
 * 
 * @author POO
 *
 */
public class Hour implements Comparable<Hour> {
	/**
	 * Instance variable to represent the hour (it must be an integer value between 0 and 23)
	 */
	private int hh;
	
	/**
	 * Instance variable to represent the minutes (it must be an integer value between 0 and 59)
	 */
	private int mm;
	
	/**
	 * Constructor to create objects of the class from two integer values.
	 * In case that the values are out of the allowed range,
	 * the equivalent hour is obtained, taking into account hours module 24.
	 * If any of the parameters is a negative value 
	 * a TVScheduleException is thrown.
	 * 
	 * @param h	Hour (between 0 and 23)
	 * @param m	Minute (between 0 and 59)
	 */
	public Hour(int h, int m) {
		if (h<0 || m<0) throw new TVScheduleException("Hours or minutes cannot be negative");
		hh = (h + m/60) % 24;
		mm = m % 60;
	}
	
	/**
	 * Method to get the hour
	 * 
	 * @return	It returns the hours of the object Hour
	 */
	public int getHour() {
		return hh;
	}
	
	/**
	 * Method to get the minutes
	 * 
	 * @return	It returns the minutes of the object Hour
	 */
	public int getMinutes() {
		return mm;
	}
	
	/**
	 * Method to increment the hour in the minutes passed as parameter.
	 * The hour is always kept between 0 and 23, and the minute between 0 and 59.
	 * @param minutes	Minutes in which the hour is incremented.
	 */
	public void increment(int minutes) {
		hh = (hh + (minutes + mm) / 60) % 24;
		mm = (minutes + mm) % 60;
	}
	
	/** 
	 * Two time instants are considered to be equal when their hour and minutes match.
	 */
	@Override
	public boolean equals(Object o) {
		boolean res = o instanceof Hour;
		Hour hora = res ? (Hour) o : null;
		return res && hh == hora.hh && mm == hora.mm;
	}
	
	/**
	 * Redefinition of method hashCode so it is consistent to the redifinition of equals.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(hh,mm);
	}

	
	/**
	 * Natural order by which an hour is lesser than other
	 * when is earlier chronologically.
	 */
	@Override
	public int compareTo(Hour hour) {
		int res = Integer.compare(hh, hour.hh);
		if (res == 0) 
			res = Integer.compare(mm, hour.mm);
		return res;
	}
	
	/**
	 * It returns the difference in minutes between the receiver object and the hour
	 * passed as parameter.
	 * 
	 * @param hour	Hour
	 * @return	Difference in minutes
	 */
	public int differenceMinutes(Hour hour) {
		int minutes = hh * 60 + mm;
		int minutesHora = hour.hh * 60 + hour.mm;
		return Math.abs(minutes-minutesHora);
	}
	
	/**
	 * Text representation of the object with format:
	 * 		[hh:mm]
	 */
	@Override
	public String toString() {
		String h = (hh<10 ? "0" : "") + hh;
		String m = (mm<10 ? "0" : "") + mm;
		return "[" + h + ":" + m + "]";
	}

}