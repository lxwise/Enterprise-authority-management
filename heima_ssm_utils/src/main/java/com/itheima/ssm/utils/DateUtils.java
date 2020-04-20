package com.itheima.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author l-xin
 * @create 2020-03-24 18:40
 * 关于时间的工具类
 */
public class DateUtils {

    //日期转换为字符串
    public static String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }
    //字符串转换为日期
    public static Date string2Date(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }
}
