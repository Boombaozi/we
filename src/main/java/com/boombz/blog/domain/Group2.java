package com.boombz.blog.domain;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//CREATE TABLE group
//        (
//        id          INT    AUTO_INCREMENT   PRIMARY KEY,
//        type         VARCHAR(1) DEFAULT '1'   COMMENT '组类型，扩展预留',
//        groupname   VARCHAR(50)  NOT NULL             COMMENT '组名称',
//        createby    INT          NOT NULL             COMMENT '创建该组的用户',
//        status      VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
//        groupcheck    VARCHAR(50)   NULL         COMMENT'验证数字',
//        createtime DATETIME     NOT NULL     COMMENT '创建时间',
//        updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间'
//        );
@Entity
public class Group2{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Character type;
    @Column
    private String groupname;
    @Column
    private Integer createby;
    @Column
    private String status;
    @Column
    private String groupcheck;
    @Column
    private Date createtime;
    @Column
    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupcheck() {
        return groupcheck;
    }

    public void setGroupcheck(String groupcheck) {
        this.groupcheck = groupcheck;
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
