package com.boombz.blog.service;

import com.boombz.blog.domain.Day;
import com.boombz.blog.util.ServerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05-01 14
 **/
public interface DayService {

    public ServerResponse addDay(Day day, HttpSession session);

    public ServerResponse<Page<Day>> findAllDayByGroupId(Integer integer,Pageable pageable);

    public ServerResponse  deleteDaybyId(Integer id);

    public ServerResponse  realDeleteDaybyId(Integer id);

    public ServerResponse  recoverDay(Integer id);

    public ServerResponse<Page<Day>>  findAllDay(Pageable pageable);
}
