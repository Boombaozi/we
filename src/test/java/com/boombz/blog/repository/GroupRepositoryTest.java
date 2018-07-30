//package com.boombz.blog.repository;
//
//import com.boombz.blog.domain.Group;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class GroupRepositoryTest {
//    @Autowired
//    GroupRepository repository;
//
//
//    @Test
//    public void findAll() throws Exception {
//        Pageable pageable=new PageRequest(1, 6);
//
//        repository.findAll(pageable);
//    }
//
//
//    @Test
//    public void findByGroupcheckAndStatus() throws Exception {
//      List<Group> groupList= repository.findAllByGroupcheckAndStatus("1","1");
//
//        System.out.println(groupList.size());
//        Assert.assertEquals(groupList.size(),1);
//    }
//
//}