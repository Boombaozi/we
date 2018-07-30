package com.boombz.blog.kafka;

import com.boombz.blog.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@Slf4j
public class Event implements Serializable {

    //操作是否成功
    private Boolean issuccess;
    //时间
    private String date;
    //地点
    private String uri;
    //事件类型(浏览，新增数据，删除数据，登录，注册)
    private String type;
    //事件描述
    private String dest;
    //用户信息
    private User user;
    //附加信息
    private Object data;

    public Boolean getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Boolean issuccess) {
        this.issuccess = issuccess;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    Event() {
        String FULL_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss.SSS+0800";
        Date now = new Date();
        this.date = new SimpleDateFormat(FULL_FORMAT).format(now);


    }

    public static Event sendSuccessMessage(String uri,
                                           String type,
                                           String dest,
                                           User user,
                                           Object data) {
        //成功
        Event event = new Event();
        event.setIssuccess(true);
        //uri
        if (uri != null) {
            event.setUri(uri);
        }
        event.setType(type);
        event.setDest(dest);
        event.setUser(user);
        event.setData(data);

        return event;
    }

    public static Event sendErrorMessage(String uri,
                                         String type,
                                         String dest,
                                         User user,
                                         Object data) {
        //成功
        Event event = new Event();
        event.setIssuccess(false);
        //uri
        if (uri != null) {
            event.setUri(uri);
        }
        event.setType(type);
        event.setDest(dest);
        event.setUser(user);
        event.setData(data);

        return event;
    }


    public static Event test() {
//        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        log.info("开始：" + df.format(new Date(System.currentTimeMillis())));
        String FULL_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss.SSS+0800";
        Date now = new Date();


        User user = new User();
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setId(71);
        user.setRole("1");
        user.setStatus("1");
        user.setGroupid(100);
        user.setUsername("测试用户");
        user.setPassword("密码123");
        user.setEmail("huiyuhang123@qq.com");
        user.setPhone("22435535");

        Event event = new Event();
        event.setIssuccess(false);
        event.setDate(new SimpleDateFormat(FULL_FORMAT).format(now));
        event.setType("测试");
        event.setDest("事件记录测试");
        event.setUser(user);
        event.setUri("/wsds/wfwsfw/sfsfsf/sdsd");
        return event;
    }
}
