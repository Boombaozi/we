package com.boombz.blog.util;

import java.util.UUID;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
public class UUIDUtils {

    public static String getUUID(){

         return UUID.randomUUID().toString().replace("-","");

    }
}
