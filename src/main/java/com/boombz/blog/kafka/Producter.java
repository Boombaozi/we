package com.boombz.blog.kafka;

import com.boombz.blog.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producter {

    @Value("${kafka.topic}")
    private String send;

    @Autowired
    private KafkaTemplate kafkaTemplate;


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

       // log.info("kafka.topic=" + send + "  " + mes2);


    }

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

        //log.info("kafka.topic=" + send + "  " + mes2);

    }

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

        //log.info("kafka.topic=" + send + "  " + mes2);
    }


    public void sendSuccessInsert(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage(uri,
                "insert",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);

        //log.info("kafka.topic=" + send + "  " + mes2);

    }

    public void sendSuccessDelete(User user, String dest,String uri) {
        Gson gson = new GsonBuilder().create();
        Event event = Event.sendSuccessMessage(uri,
                "delete",
                dest,
                user, null);
        String mes2 = gson.toJson(event);
        if (kafkaTemplate == null) {
            System.out.println("kafka为空");
            return;
        }
        kafkaTemplate.send(send, mes2);
        //log.info("kafka.topic=" + send + "  " + mes2);

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
