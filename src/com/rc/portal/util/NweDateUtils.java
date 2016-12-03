package com.rc.portal.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * 
 * @version 1.0
 */
public class NweDateUtils {
	private static SimpleDateFormat dateformater;
	private static SimpleDateFormat timeformater;
	private static SimpleDateFormat dateTimeformater;
	private static long DAY_IN_MILLISECOND = 0x5265c00L;
	private SimpleDateFormat timestampformater;

	public NweDateUtils() {
	}

	/**
	 * 把字符型日期表达式转换成java.util.Date
	 * 
	 * @param strDate
	 *            型如 "yyyyMMdd" 的日期字符串
	 * @return 转换后的java.util.Date对象；转换失败返回null
	 */
	public Date parseDate(String strDate) {
		Date date = null;
		try {
			date = getDateFormater().parse(strDate);
		} catch (Exception ex) {
		}
		return date;
	}
	/**
	 * 把字符型时间表达式转换成java.util.Date
	 * 
	 * @param strDateTime
	 *            型如 "yyyyMMddHHmm" 的时间字符串
	 * @return 转换后的java.util.Date对象；转换失败返回null
	 */
	public Date parseDateTime(String strDateTime) {
		Date date = null;
		try {
			date = getDateTimeFormater().parse(strDateTime);
		} catch (Exception ex) {
		}
		return date;
	}
	/**
	 * 把java.util.Date转换成"yyyyMMdd"字符串
	 * 
	 * @param date
	 * @return
	 */
	public String formatDate(Date date) {
		if (date != null)
			return getDateFormater().format(date);
		else
			return "";
	}
	/**
	 * 把java.util.Date转换成"dd"字符串  只获取天
	 * 
	 * @param date
	 * @return
	 */
	public String formatDay(Date date) {
		if (date != null)
			return getDayFormater().format(date);
		else
			return "";
	}
	/**
	 * 把java.util.Date转换成"MMdd"字符串  只获取月和日
	 * 
	 * @param date
	 * @return
	 */
	public String formatMonthDay(Date date) {
		if (date != null)
			return getMonthDayFormater().format(date);
		else
			return "";
	}
	private DateFormat getDateTimeFormater() {
		if (dateTimeformater == null)
			dateTimeformater = new SimpleDateFormat("yyyyMMddHHmm");
		return dateTimeformater;
	}
	private DateFormat getDateFormater() {
		if (dateformater == null)
			dateformater = new SimpleDateFormat("yyyyMMdd");
		return dateformater;
	}
	private DateFormat getDayFormater() {
		if (dateformater == null)
			dateformater = new SimpleDateFormat("d");
		return dateformater;
	}
	private DateFormat getMonthDayFormater() {
		if (dateformater == null)
			dateformater = new SimpleDateFormat("MMdd");
		return dateformater;
	}
	/**
	 * 查询当前月与指定月之间差几个月
	 * @param endDate 指定最后日期
	 * @return
	 * @throws ParseException
	 */
	public int getMonthDiff(String endDate) throws ParseException {
		int year;
        int month;
        String date;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        date = year + "" + ( month<10 ? "0" + month : month);
        
        int monthday;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
        Date startDate1 = fmt.parse(date);

        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate1);

        int sYear  = starCal.get(Calendar.YEAR);
        int sMonth = starCal.get(Calendar.MONTH);
//        int sDay   = starCal.get(Calendar.DAY_OF_MONTH);

        Date endDate1 = fmt.parse(endDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate1);
        int eYear  = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);
//        int eDay   = endCal.get(Calendar.DAY_OF_MONTH);

        monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));
        
        //这里计算零头的情况，根据实际确定是否要加1 还是要减1
//        if (sDay < eDay) {
//            monthday = monthday + 1;
//        }
        return monthday;
    }

}
