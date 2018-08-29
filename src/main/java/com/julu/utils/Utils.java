package com.julu.utils;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @Author: mhs
 * @Date: Created in 2018/1/30 15:39
 * @Version: V1
 * @Description: 通用工具类，此工具类要按照功能重新拆分
 */
public class Utils {
    /**
     * 去掉多余的0
     * @param s
     * @return
     */
    public static String rvZeroAndDot(String s){
        if (s.isEmpty()) {
            return null;
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String toS(BigDecimal val) {
//        BigDecimal bigDecimal = new BigDecimal(val);
        return val.toString();
    }

    public static String wget(String u) throws Exception {
        URL url = new URL(u);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                url.openStream(), "UTF-8"));
        StringBuffer content = new StringBuffer();
        String tmp = null;

        while ((tmp = br.readLine()) != null) {
            content.append(tmp);
        }
        br.close();
        return content.toString();
    }

    // 获得随机字符串
    public static String randomString(int count) {
        String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        int size = str.length();
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        while (count > 0) {
            sb.append(String.valueOf(str.charAt(random.nextInt(size))));
            count--;
        }
        return sb.toString();
    }

    public static String randomInteger(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

    public static String getRandomImageName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
        String path = simpleDateFormat.format(new Date());
        path += "_" + randomString(5);
        return path;
    }

    public static String getMD5_32_xx(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }




    public static String getCookie(Cookie[] cookies, String key)
            throws Exception {
        String value = null;
        if (cookies != null && key != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    value = cookie.getValue();
                }
            }
        }

        return value;
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static int getNumPerPage() {
        return 40;
    }

    public static synchronized String UUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // return seconds
    public static long timeMinus(Timestamp t1, Timestamp t2) {
        return (t1.getTime() - t2.getTime()) / 1000;
    }

    // 获得今天0点
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static String getCurTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String number2String(BigDecimal f) {
        DecimalFormat df = new DecimalFormat();
        String style = "0.00000000";// 定义要显示的数字的格式
        df.applyPattern(style);
        return df.format(f);
    }


    public static double getDoubleUp(double value, int scale) {
        return ((long) (value * Math.pow(10, scale))) / Math.pow(10.0, scale);
    }

    public static double getDouble(double value, int scale) {
        return new BigDecimal(String.valueOf(value)).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static double getDouble(String value, int scale) {
        return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    // TODO: 临时增加这一系列方法，以后不应该使用double，而是BigDecimal
    public static BigDecimal addDouble(double val1, double val2, int scale) {
        BigDecimal b1 = BigDecimal.valueOf(val1);
        BigDecimal b2 = BigDecimal.valueOf(val2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal subDouble(double val1, double val2, int scale) {
        BigDecimal b1 = BigDecimal.valueOf(val1);
        BigDecimal b2 = BigDecimal.valueOf(val2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal mulDouble(double val1, double val2, int scale) {
        BigDecimal b1 = BigDecimal.valueOf(val1);
        BigDecimal b2 = BigDecimal.valueOf(val2);
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal divDouble(double val1, double val2, int scale) {
        BigDecimal b1 = BigDecimal.valueOf(val1);
        BigDecimal b2 = BigDecimal.valueOf(val2);
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN);
    }

    public static double addDoubleRetD(double val1, double val2, int scale) {
        return addDouble(val1, val2, scale).doubleValue();
    }

    public static double subDoubleRetD(double val1, double val2, int scale) {
        return subDouble(val1, val2, scale).doubleValue();
    }

    public static String getDoubleS(double value, int scale) {
        value += Math.pow(10, -10);
        return new BigDecimal(String.valueOf(value)).setScale(scale, BigDecimal.ROUND_DOWN).toString();
    }

    public static String dateFormat(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        Pattern pattern = compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "01234567890123456789012345678901234567890123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    public static Hashtable getAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    public static String getAfterDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -day);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 1);
        return sdf.format(c.getTime());
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 7);
        return sdf.format(c.getTime());
    }


    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static String getPrototypeUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static double randomDouble() {
        double random = Math.random();
        DecimalFormat Dformat = new DecimalFormat("0.000000");
        String string = Dformat.format(random * 10000);
        return new Double(string);
    }

    /**
     * 从国际化资源配置文件中根据key获取valu
     *
     * @param request
     * @param key
     * @return
     */
    public static String getMessage(HttpServletRequest request, String key) {

        try {
            Locale currentLocale = null;
            if (request == null) {
                currentLocale = new Locale("zh", "CN");
            } else {
                currentLocale = RequestContextUtils.getLocale(request);
                if (currentLocale == null) {
                    currentLocale = new Locale("zh", "CN");
                }
            }
            ResourceBundle bundle = ResourceBundle.getBundle("messages_" + currentLocale, currentLocale);
            return new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Forwarded-For");
            try {
                if(ip != null && ip.trim().length() >0){
                    return ip.split(",")[0];
                }
            } catch (Exception e) {}

            try {
                ip = request.getHeader("X-Real-IP");
                if ((ip != null && ip.trim().length() >0) && (!"unKnown".equalsIgnoreCase(ip))) {
                    return ip;
                }
            } catch (Exception e) {}

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("http_client_ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            // 如果是多级代理，那么取第一个ip为客户ip
            if (ip != null && ip.indexOf(",") != -1) {
                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
            }
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
            return request.getRemoteAddr();
        }
    }
    
    
}
