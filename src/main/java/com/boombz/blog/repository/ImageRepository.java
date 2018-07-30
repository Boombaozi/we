package com.boombz.blog.repository;

import com.boombz.blog.domain.Group2;
import com.boombz.blog.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05-05 16
 **/
public interface ImageRepository extends JpaRepository<Image,Integer> {

    Page<Image> findAllBygroupidAndStatusOrderByCreatetimeDesc(Integer groupid, String Status, Pageable pageable);
}
