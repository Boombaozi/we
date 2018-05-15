package com.boombz.blog.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//
//CREATE TABLE user
//        (
//        id          INT AUTO_INCREMENT     PRIMARY KEY,
//        groupid     INT  NULL,
//        username    VARCHAR(50)  NOT NULL,
//        password    VARCHAR(50)  NOT NULL,
//        image  VARCHAR(500) NULL                  COMMENT '预留',
//        email       VARCHAR(50)  NULL              COMMENT '预留',
//        phone       VARCHAR(20)  NULL             COMMENT '预留',
//        question    VARCHAR(100) NULL             COMMENT '预留',
//        answer      VARCHAR(100) NULL             COMMENT '预留',
//        role        VARCHAR(1)   DEFAULT '1'      COMMENT '角色0-管理员,1-普通用户',
//        status VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
//        createtime DATETIME     NOT NULL     COMMENT '创建时间',
//        updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间',
//
//        UNIQUE (username)
//        );
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer groupid;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String image;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String question;
    @Column
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column
    private String answer;
    @Column
    private String role;
    @Column
    private String status;
    @Column
    private Date createtime;
    @Column
    private Date updatetime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", groupid=" + groupid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }

    public User() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
