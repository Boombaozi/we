package com.boombz.blog.repository;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.User;
import com.boombz.blog.vo.CommentVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer> {


    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findAllByOrderByIdDesc(Pageable pageable);


    Page<Comment> findAllBygroupid(Integer group_id, Pageable pageable);


    Page<Comment> findAllBygroupidAndStatusOrderByIdDesc(Integer groupid,String Status,Pageable pageable);


    @Query("select a,b.username,b.image,b.email from Comment a join User b where a.authorid=b.id")
//
    List<CommentVo> find_All();


}
