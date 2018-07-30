package com.boombz.blog.service;

import com.boombz.blog.domain.Group2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceImplTest {

    @Autowired
    private GroupServiceImpl service;
    @Test
    public void addGroup1() throws Exception {
        Group2 group=new Group2();
        group.setGroupname("dsdsd");
    //   service.addGroup(group);

    }

    @Test
    public void findAllGroup() throws Exception {
        service.findAllGroup(new PageRequest(1,1));


    }

    @Test
    public void deleteGroupById() throws Exception {
    }

    @Test
    public void addGroup() throws Exception {
    }

    @Test
    public void updateGroup() throws Exception {
    }

    @Test
    public void realDeleteGroupById() throws Exception {
    }

    @Test
    public void recoverGroupById() throws Exception {
    }

}