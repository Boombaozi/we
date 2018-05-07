package com.boombz.blog.serviceTest;

import com.boombz.blog.util.ServerResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayServiceImplTest {

    @Autowired
    private DayServiceImpl dayServiceImpl;

    @Test
    public void addDay() throws Exception {


    }

    @Test
    public void findAllDayByGroupId() throws Exception {
        Pageable pageable = new PageRequest(1, 6);
     ServerResponse response= dayServiceImpl.findAllDayByGroupId(1, pageable);
        Assert.assertEquals(response.isSuccess(),true);
        System.out.println(response.getMsg());
    }

    @Test
    public void deleteDaybyId() throws Exception {
        ServerResponse response=dayServiceImpl.deleteDaybyId(2);
        Assert.assertEquals(response.isSuccess(),true);
    }

    @Test
    public void realDeleteDaybyId() throws Exception {
        ServerResponse response=dayServiceImpl.realDeleteDaybyId(1);
        Assert.assertEquals(response.isSuccess(),true);
    }

    @Test
    public void recoverDay() throws Exception {
        ServerResponse response=dayServiceImpl.recoverDay(2);
        Assert.assertEquals(response.isSuccess(),true);

    }

    @Test
    public void findAllDay() throws Exception {
        Pageable pageable = new PageRequest(1, 6);
        ServerResponse response=dayServiceImpl.findAllDay(pageable);
        Assert.assertEquals(response.isSuccess(),true);
    }

}