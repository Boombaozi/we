package com.boombz.blog.service;


import com.boombz.blog.domain.Comment;
import com.boombz.blog.util.ServerResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService=new CommentServiceImpl();

    @Test
    public void addComment() throws Exception {

    }

    @Test
    public void deleteCommentById() throws Exception {
        ServerResponse<Comment> response=commentService.deleteCommentById(Integer.valueOf(21));


        Assert.assertEquals(response.isSuccess(),true);
        System.out.println(response.getMsg());

    }

    @Test
    public void findAllComment() throws Exception {
        org.springframework.data.domain.Pageable pageable = new PageRequest(1, 6);
        ServerResponse<Page<Comment>> response=commentService.findAllComment(pageable);
        Assert.assertEquals(response.isSuccess(),true);
        System.out.println(response.getMsg());

    }

    @Test
    public void findCommentByGroupId() throws Exception {

        org.springframework.data.domain.Pageable pageable = new PageRequest(1, 6);
        ServerResponse<Page<Comment>> response= commentService.findCommentByGroupId(1,pageable);
        Assert.assertEquals(response.isSuccess(),true);
        System.out.println(response.getMsg());
    }

}