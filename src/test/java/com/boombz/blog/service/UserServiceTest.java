package com.boombz.blog.service;

import com.boombz.blog.domain.User;
import com.boombz.blog.util.ServerResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;


    @Test
    public void login() throws Exception {

    }

    @Test
    public void register() throws Exception {
//        User user=new User();
//        user.setCreatetime(new Date());
//        user.setUpdatetime(new Date());
//        user.setRole("1");
//        user.setStatus("1");
//        user.setGroupid(100);
//        user.setUsername("测试用户");
//        user.setPassword("密码");
//        ServerResponse<User> a= userService.register(user);
//        Assert.assertEquals(a.isSuccess(),true);
    }

    @Test
    public void edit() throws Exception {
        User user=new User();
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setImage("sdsd");
        user.setRole("1");
        user.setStatus("1");
        user.setId(5);
        user.setGroupid(100);
        user.setUsername("测试用户");
        user.setPassword("密码");
        ServerResponse<User> a= userService.edit(user);
        Assert.assertEquals(a.isSuccess(),true);
    }

    @Test
    public void addGroup() throws Exception {

    }

    @Test
    public void userList() throws Exception {
        org.springframework.data.domain.Pageable pageable = new PageRequest(1, 6);
        ServerResponse<Page<User>> a= userService.userList(pageable);
        Assert.assertEquals(a.isSuccess(),true);
    }

    @Test
    public void findUserbygroupid() throws Exception {
        org.springframework.data.domain.Pageable pageable = new PageRequest(1, 6);
        ServerResponse<Page<User>> a= userService.findUserbygroupid(1,pageable);
        Assert.assertEquals(a.isSuccess(),true);
    }

    @Test
    public void findUserById() throws Exception {
        ServerResponse<User> a= userService.findUserById(5);
        Assert.assertEquals(a.isSuccess(),true);
    }

    @Test
    public void deleteUserById() throws Exception {
    ServerResponse<User> a= userService.deleteUserById(5);
        Assert.assertEquals(a.isSuccess(),true);
    }

}