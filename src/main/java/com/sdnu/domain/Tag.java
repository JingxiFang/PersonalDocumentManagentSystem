package com.sdnu.domain;

import java.util.Date;

/**
 * 标签信息
 */
public class Tag {

    /**
     * 标签编号
     */
    private Integer tagid;

    //private int test=66;

    /**
     * 标签名称
     */
    private String tagname;

    /**
     * 父标签
     */
    private Integer parentid;

    /**
     * 创建者信息
     */
    private User user;

    /**
     * 创建时间
     */
    private Date createtime;


    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "标签{" +
                "标签编号=" + tagid +
                ", 标签名称='" + tagname + '\'' +
                ", 创建用户=" + user +
                '}';
    }
}
