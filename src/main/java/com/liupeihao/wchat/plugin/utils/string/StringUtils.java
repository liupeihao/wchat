package com.liupeihao.wchat.plugin.utils.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author LPH
 * @Title: StringUtils
 * @ProjectName tripartite_system
 * @Description: TODO
 * @date 2018/11/6 000611:06
 */

public class StringUtils {
    private static final Pattern patternOfNum = Pattern.compile("\\d+");
    /**
     *
     * 功能：<br/>
     * 生成随机字符串
     *
     * @author LPH
     */
    public static String generateRandomCode(int verifySize, String sources) {
        if (isBlank(sources)) {
            sources = "23456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        Random random = new Random();
        int codesLen = sources.length();
        StringBuilder randomCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            randomCode.append(sources.charAt(random.nextInt(codesLen - 1)));
        }
        return randomCode.toString();
    }


    /**
     *
     * 功能：<br/>
     * 生成随机字符串
     *
     * @author LPH
     */
    public static String generateRandomCode(int verifySize) {
        String   sources = "0123456789";
        Random random = new Random();
        int codesLen = sources.length();
        StringBuilder randomCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            randomCode.append(sources.charAt(random.nextInt(codesLen - 1)));
        }
        return randomCode.toString();
    }

    public static void main(String[] args) {
        String code = generateRandomCode(7);
        System.out.println(code);
    }

    /**
     *  字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !(str == null || str.trim().length() == 0);
    }

    public static String getNumber(String str){
        Matcher matcher = patternOfNum.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     *
     * @param str
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     * 将字符串转换为十六进制
     */
    public static String strToHex(String str,String charset) throws UnsupportedEncodingException {
        String hexString = "0123456789ABCDEF";
       //根据编码获取字节数组
        byte[] bytes = str.getBytes(charset);
        StringBuilder sb = new StringBuilder(bytes.length * 2);
       //将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
                sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
               sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
             }
        return sb.toString();
    }

    /**
     * 将进制转换为字符串
     *
     * @param str
     * @return
     */
    public static String hexToString(String str,String charset) throws UnsupportedEncodingException {
        if (isBlank(str)) {
            return null;
        }
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        String s=new String(bytes,charset);
        return s;
    }


    public static boolean equals(String source, String target) {
        if(null != source){
            if(null != target){
                return source.equals(target);
            }else{
                return true;
            }
        }
        return false;
    }


    /**
     * 使用gzip进行压缩
     */
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * <p>Description:使用gzip进行解压缩</p>
     * @param compressedStr
     * @return
     */
    public static String gunzip(String compressedStr){
        if(compressedStr==null){
            return null;
        }
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        ByteArrayInputStream in=null;
        GZIPInputStream ginzip=null;
        byte[] compressed=null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in=new ByteArrayInputStream(compressed);
            ginzip=new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed=out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return decompressed;
    }
}
