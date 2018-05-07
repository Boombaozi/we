package com.boombz.blog.repository;

import com.boombz.blog.domain.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Integer> {


    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findAllByOrderByIdDesc(Pageable pageable);

 //   Page<Comment> findAllByContentLike(Pageable pageable);

    Page<Comment> findAllBygroupid(Integer group_id, Pageable pageable);


    Page<Comment> findAllBygroupidAndStatusOrderByIdDesc(Integer groupid,String Status,Pageable pageable);


}
