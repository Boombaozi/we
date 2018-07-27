package com.boombz.blog.service;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.CommentRepository;
import com.boombz.blog.util.ServerResponse;
import com.boombz.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;


    @Override
    @Transactional
    public ServerResponse<Comment> addComment(Comment comment, HttpSession session, HttpServletRequest request) {

        comment.setCreatetime(new Date());
        comment.setUpdatetime(new Date());
        comment.setStatus("1");
        comment.setIschecked("1");

        //不兼容
       // comment.setAuthorinfo(infoRecord.record1(request));

        User user = (User) session.getAttribute("user");
        comment.setAuthorid(user.getId());
        comment.setGroupid(user.getGroupid());
        Comment comment1 = commentRepository.save(comment);
        if (comment1 == null) {
            return ServerResponse.createByErrorMessage("添加失败");
        } else {
            return ServerResponse.createBySuccess(comment1);
        }

    }

    @Override
    @Transactional
    public ServerResponse<Page<Comment>> findAllComment(Pageable pageable) {

        Page<Comment> comments = commentRepository.findAllByOrderByIdDesc(pageable);

        if (comments == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        } else {
            return ServerResponse.createBySuccess(comments);
        }

    }



    @Override
    @Transactional
    public ServerResponse<Comment> deleteCommentById(Integer id) {
        Comment comment = commentRepository.findOne(id);
        comment.setStatus("2");
        comment.setUpdatetime(new Date());
        commentRepository.save(comment);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Transactional
    @Override
    public ServerResponse<Comment> realdeleteCommentById(Integer id) {
        commentRepository.delete(id);
        return ServerResponse.createBySuccessMessage("真正的删除了");
    }

    @Transactional
    @Override
    public ServerResponse<Page<Comment>> findCommentByGroupId(Integer id, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllBygroupidAndStatusOrderByIdDesc(id, "1", pageable);
        if (comments == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        } else {
            return ServerResponse.createBySuccess(comments);
        }
    }

    @Override
    public ServerResponse<Page<Comment>> findCommentByGroupId2(Integer integer, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllBygroupidAndStatusOrderByIdDesc(integer, "2", pageable);
        if (comments == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        } else {
            return ServerResponse.createBySuccess(comments);
        }
    }

    @Transactional
    @Override
    public ServerResponse<Comment> recoverComment(Integer id) {
        Comment comment = commentRepository.findOne(id);
        comment.setStatus("1");
        comment.setUpdatetime(new Date());
        commentRepository.save(comment);
        return ServerResponse.createBySuccessMessage("恢复成功");
    }

    @Override
    public ServerResponse<List<CommentVo>> findAllByGroupidAndStatus(Pageable pageable, Integer groupid, String status) {
        return ServerResponse.createBySuccess(commentRepository.find_All());
    }
}
