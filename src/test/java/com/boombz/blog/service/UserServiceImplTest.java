package com.boombz.blog.service;

import com.boombz.blog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void addGroup() throws Exception {
        User user=new User();

        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setId(71);
        user.setRole("1");
        user.setStatus("1");
        user.setGroupid(100);
        user.setUsername("测试用户");
        user.setPassword("密码");
        userService.addGroup(user,"aaa");

    }

}