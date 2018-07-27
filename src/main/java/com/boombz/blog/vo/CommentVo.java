package com.boombz.blog.vo;




import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//CREATE TABLE comment
//        (
//        id             INT AUTO_INCREMENT    PRIMARY KEY,
//        groupid        INT     NULL,
//        authorid       INT     NOT NULL,
//        authorinfo       VARCHAR(100)     NULL,
//        content         VARCHAR(300)         NOT NULL,
//        ischecked      VARCHAR(1) DEFAULT '1'            COMMENT '预留，1表示已检查' ,
//        status VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
//        createtime DATETIME     NOT NULL     COMMENT '创建时间',
//        updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间'
//        );

@Entity
public class CommentVo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 自增长策略
    private Integer id;
    @Column
    private Integer groupid;

    @Column
    private Integer authorid;
    @Column
    private String  authorinfo;
    @Column
    private String  content;
    @Column
    private String  ischecked;

    @Column
    private String  status;
    @Column
    private Date createtime;
    @Column
    private Date updatetime;

    @Column
    private String username;
    @Column
    private String image;
    @Column
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    protected CommentVo() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
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

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthorinfo() {
        return authorinfo;
    }

    public void setAuthorinfo(String authorinfo) {
        this.authorinfo = authorinfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIschecked() {
        return ischecked;
    }

    public void setIschecked(String ischecked) {
        this.ischecked = ischecked;
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
