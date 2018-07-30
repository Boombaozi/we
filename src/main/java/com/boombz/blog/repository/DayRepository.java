package com.boombz.blog.repository;

import com.boombz.blog.domain.Day;
import com.boombz.blog.domain.Group2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05-01 14
 **/
public interface DayRepository extends JpaRepository<Day,Integer> {

    Page<Day> findAllBygroupidAndStatusOrderByTimeDesc(Integer groupid,String Status,Pageable pageable);

    Page<Day> findAllByOrderByIdDesc(Pageable pageable);
}
