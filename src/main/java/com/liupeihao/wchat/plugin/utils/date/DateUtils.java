package com.liupeihao.wchat.plugin.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LPH
 *
 * 日期工具类
 *
 */
public class DateUtils {

    public static final  String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final  String YYYY_MM_DD_HH_MM_SS_NO_SPACE = "yyyy-MM-ddHH:mm:ss";

    public static final  String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final  String YYYYMMDD = "yyyyMMdd";

    public static final  String HHmmss = "HHmmss";

    public static final  String YYYYMM = "yyyyMM";

    public static final  String YYYY_MM_DD = "yyyy-MM-dd";



    private static final SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentTimeStr(){

        return sdf.format(new Date());
    }
    public static String getCurrentTimeStr2(){
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_NO_SPACE).format(new Date());
    }
    /**
     * 字符串转日期
     */
    public static Date dateStrToDate(String s)  {
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 字符串转日期
     */
    public static Date dateStrToDate2(String s,String pattern)  {
        try {
            return new SimpleDateFormat(pattern).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getCurrentDate(){
        SimpleDateFormat df=new SimpleDateFormat(YYYYMMDD);
        return df.format(new Date());
    }

    public static String getTheCurrentTime(){
        SimpleDateFormat df=new SimpleDateFormat(YYYYMMDDHHMMSS);
        return df.format(new Date());
    }
    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    public static String stringFormat(String dateTime,String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(dateStrToDate(dateTime));
    }
    public static String dateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        return dateFormat.format(new Date());
    }



    /**
    * @Author hankangli
    * @Description 获取前一天的日期
    * @Date 2019/4/26 0026  14:33
    * @Param []
    * @return java.lang.String
    **/
    public static String getYestedayDate(String pattern,Integer day){
        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, day);    //得到前一天
        String  yestedayDate = new SimpleDateFormat(pattern).format(calendar.getTime());
        return yestedayDate;
    }

    /**
    * @Author hankangli
    * @Description 获取当前月份
    * @Date 2019/5/16 0016  11:26
    * @Param []
    * @return java.lang.Integer
    **/
    public static Integer getMonth(){
        Calendar cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH) + 1;
        return month;
    }

}

