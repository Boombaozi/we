package com.boombz.blog.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: we
 * @description:记录一些浏览器信息，因为兼容问题暂无法使用
 * @author: boombaozi.com
 * @create: 2018-04
 **/
public class infoRecord {

    public static String record(HttpServletRequest request) {
        String a = "";
        if (request.getRemoteAddr() != null) {
            a = a + request.getRemoteAddr() + ";";
        }
        if (request.getHeader("Accept-Language") != null) {
            a = request.getHeader("Accept-Language") + ";";
        }
        if (request.getHeader("User-Agent") != null) {
            a = a + request.getHeader("User-Agent") + ";";
        }
        return a;
    }
    public static String record1(HttpServletRequest request) {
        String a = "";
        if (request.getHeader("User-Agent") != null) {
            a = a + request.getHeader("User-Agent");
        }
        return a;
    }
}
