package com.boombz.blog.kafka;

import com.boombz.blog.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producter {

    @Value("${kafka.topic}")
    private String send;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    //记录成功登录的消息
    public void sendSuccessLogin(User user, String dest) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage("/",
                "登录",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        log.info("kafka.topic=" + send + "  " + mes2);


    }
    //登录失败的消息
    public void sendErrorLogin(User user, String dest) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendErrorMessage("/",
                "登录",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        log.info("kafka.topic=" + send + "  " + mes2);

    }

    //新用户注册
    public void sendSuccessRegister(User user, String dest) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage("/",
                "注册",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        log.info("kafka.topic=" + send + "  " + mes2);
    }

    //新增数据成功
    public void sendSuccessInsert(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage(uri,
                "新增数据",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        log.info("kafka.topic=" + send + "  " + mes2);

    }


    //修改数据成功
    public void sendSuccessUpdate(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage(uri,
                "修改数据",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        log.info("kafka.topic=" + send + "  " + mes2);

    }

    //删除数据成功
    public void sendSuccessDelete(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage(uri,
                "删除数据",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);
        log.info("kafka.topic=" + send + "  " + mes2);

    }



    //浏览数据
    public void sendSuccessAccess(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage(uri,
                "浏览",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);
        log.info("kafka.topic=" + send + "  " + mes2);
    }


    //通用错误消息
    public void sendError(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendErrorMessage(uri,
                "错误",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);
        log.info("kafka.topic=" + send + "  " + mes2);
    }

    //通用消息
    public void sendSuccess(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendErrorMessage(uri,
                "通用消息",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);
        log.info("kafka.topic=" + send + "  " + mes2);
    }

    public void sendTest() {
        Gson gson = new GsonBuilder().create();

        Event event = Event.test();

        String mes2 = gson.toJson(event);

        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        //log.info("kafka.topic=" + send + "  " + mes2);
    }


}
