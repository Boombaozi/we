package com.boombz.blog.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04
 **/
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
