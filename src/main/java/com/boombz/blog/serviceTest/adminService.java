package com.boombz.blog.serviceTest;

import com.boombz.blog.util.ServerResponse;

import java.util.List;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04-23 21
 **/
public interface adminService {

       ServerResponse<List<Long>> findallinfo();

}