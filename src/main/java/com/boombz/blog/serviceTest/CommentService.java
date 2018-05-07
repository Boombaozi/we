package com.boombz.blog.serviceTest;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.util.ServerResponse;
import com.boombz.blog.vo.CommentVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: we
 * @description: 定义对留言的一些操作
 * @author: boombaozi.com
 * @create: 2018-04-06
 **/
public interface CommentService {

   ServerResponse<Comment> addComment(Comment comment , HttpSession session,HttpServletRequest request);

   ServerResponse<Page<Comment>>findAllComment(Pageable pageable);

   //ServerResponse<Page<CommentVo>>findAllCommentVoByGroupId(HttpSession session,Pageable pageable);

   ServerResponse<Comment> deleteCommentById(Integer id);

   ServerResponse<Comment> realdeleteCommentById(Integer id);

   ServerResponse<Page<Comment>> findCommentByGroupId(Integer integer,Pageable pageable);

   ServerResponse<Page<Comment>> findCommentByGroupId2(Integer integer,Pageable pageable);

   ServerResponse<Comment> recoverComment(Integer integer);

}
