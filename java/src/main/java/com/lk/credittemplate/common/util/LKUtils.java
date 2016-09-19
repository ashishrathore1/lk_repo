package com.lk.credittemplate.common.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.common.constants.LKConstants;

/**
 * 
 * @author ramamohan
 *
 */
public class LKUtils {

	/**
	 * getSDFormat
	 * 
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSDFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		return sdf;
	}

	/**
	 * getSQLDate
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSQLDate(java.util.Date date) {
		Date sqlDate = null;
		if (date != null) {
			sqlDate = new Date(date.getTime());
		}
		return sqlDate;
	}

	/**
	 * getSQLDate
	 * 
	 * @return
	 */
	public static Date getSQLDate() {
		return new Date(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * getDate
	 * 
	 * @return
	 */
	public static java.util.Date getDate() {
		return new java.util.Date();
	}
	
	/**
	 * getDate
	 * 
	 * @return
	 */
	public static java.util.Date getDate(String dateStr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormat.parse(dateStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * getDate
	 * 
	 * @return
	 */
	public static java.util.Date getDateOnly(String dateStr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtil.notEmpty(dateStr)){
			try {
				return dateFormat.parse(dateStr);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	/**
	 * getDateStr
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getDateStrOnly(java.util.Date date) {
		if(date!=null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}
		return null;
	}
	
	/**
	 * getDateOnly
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getDateOnly(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(sdf.format(date.getTime()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.util.Date getDateWithDefaultValues(String dateStr) {
		if (StringUtil.notEmpty(dateStr)) {
			dateStr = dateStr + " 23:59:59";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return dateFormat.parse(dateStr);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return null;
	}
	
	public static java.util.Date getDateWithDefaultValuesNew(String dateStr) {
		if (StringUtil.notEmpty(dateStr)) {
			dateStr = dateStr + " 00:00:00";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return dateFormat.parse(dateStr);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return null;
	}

	/**
	 * getTimeStamp
	 * 
	 * @return
	 */
	public static Timestamp getTimeStamp() {
		return (new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}

	/**
	 * getTimeStamp
	 * 
	 * @return
	 */
	public static Timestamp getTimeStamp(long time) {
		return (new Timestamp(time));
	}

	/**
	 * getTimeStampsdf
	 * 
	 * @return
	 */
	private static String getTimeStampsdf() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(new java.util.Date());
	}

	/**
	 * getLong
	 * 
	 * @param str
	 * @return
	 */
	public static Long getLong(String str) {
		return Long.valueOf(str);
	}

	/**
	 * getUser
	 * 
	 * @param email
	 * @return
	 */
	public static String getUser(String email) {
		return email.contains("@") ? email.split("@")[0] : email;
	}

	/**
	 * getGson
	 * 
	 * @return
	 */
	public static Gson getGson() {
		return new GsonBuilder().create();
	}

	/**
	 * fromJson
	 * 
	 * @param json
	 * @param clz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clz) {
		return getGson().fromJson(json, clz);
	}

	/**
	 * mapFromJson
	 * 
	 * @param json
	 * @param t
	 * @param s
	 * @return
	 */
	public static <T, S> Map<T, S> mapFromJson(String json, Class<T> t, Class<S> s) {
		return getGson().fromJson(json, new TypeToken<Map<T, S>>() {
		}.getType());
	}

	/**
	 * toJson
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> String toJson(T entity) {
		return getGson().toJson(entity);
	}

	/**
	 * val
	 * 
	 * @param val
	 * @return
	 */
	public static double doubleRound(double val) {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(val));
	}
	
	/**
	 * getStatusNoficationFlag
	 * 
	 * @param loanMaster
	 * @return
	 */
	public static boolean getStatusNoficationFlag(LoanMaster loanMaster) {
		boolean statusNotificationFlag = loanMaster.isStatusNotificationFlag();
		if (LKConstants.LK_STATUS_LIST.contains(loanMaster.getStatusId())
				|| (loanMaster.getSubStatus() != null && loanMaster.getSubStatus().equalsIgnoreCase(LKConstants.LK_SUB_STATUS_T_AND_C_SENT))) {
			statusNotificationFlag = false;
		}
		return statusNotificationFlag;
	}

	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().getTimeInMillis());
		System.out.println(getTimeStamp());
	}
	
//	public static Date Min(Date d1,Date d2)
//	{
//		if(d1==null && d2==null)return null;
//		if(d1==null)return d2;
//		if(d2==null)return d1;
//		return d1.before(d2)?d1:d2;
//	}

	public static java.util.Date Min(java.util.Date d1, java.util.Date d2) {
		// TODO Auto-generated method stub
		if(d1==null && d2==null)return null;
		if(d1==null)return d2;
		if(d2==null)return d1;
		return d1.before(d2)?d1:d2;
	}
	
	
	public static double parseDouble(String s){
	    if(s == null || s.isEmpty()) 
	        return 0.0;
	    else
	        return Double.parseDouble(s);
	}
	
	
	public static int cycleDays(int a , int b ,int c)
	{
		int sum=a+b-c;
		return sum;
	}
	
	public static double cycleDaysLakhs(double a , double b ,double c)
	{
		double sum=a+b-c;
		return sum;
	}
	
	
	
	
//	public static int getAgeFromDateOfBirth(Date dob) {
//		Period period = null;
//		try {
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(dob);
//			int year = cal.get(Calendar.YEAR);
//			int month = cal.get(Calendar.MONTH) + 1;
//			int day = cal.get(Calendar.DATE);
//			LocalDate birthdate = new LocalDate(year, month, day);
//			LocalDate now = new LocalDate(day, day, day);
//			period = new Period(birthdate, PeriodType.yearMonthDay());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			LOGGER.error("Exception occurred : ", e);
//		}
//		int age = period.getYears();
//		return age;
//	}
//	private static int monthsBetweenIgnoreDays(LocalDate start, LocalDate end) {
//	    start = start.withDayOfMonth(1);
//	    end = end.withDayOfMonth(1);
//	    return Months.monthsBetween(start, end).getMonths();
//	}


}
