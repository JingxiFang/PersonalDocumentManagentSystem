package com.sdnu.domain;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 文献信息
 */

@Component("doc")
public class Doc {
    /**
     * 文献编号
     */
    private Integer docid;

    public Integer getDocid() {
        return docid;
    }
    public void setDocid(Integer docid) {
        this.docid = docid;
    }
    /**
     * 文献存放地址
     */
    private String filepath;

    /**
     * 文献名称
     */
    private String docname ;

    /**
     * 作者名称
     */
    private List<String> authornames;
    /**
     * 作者名称
     */
    private String authorname ;

    /**
     * 发布时间
     */
    private String publictime;
    /**
     * 期刊名称
     */
    private String periname;

    /**
     * 关键字
     */
    private List<String> keywords;
    private String keyword ;

    /**
     * 标签信息
     */
    private Tag tag;

    /**
     * 上传者
     */
    private User user;

    /**
     * 上传时间
     */
    private String uploadtime;

    /**
     * 最后一次阅览时间
     */
    private String viewtime;

    /**
     * 稍后再看标记
     */
    private Integer remindflag;



    /**
     * 笔记
     */
    private String note;



    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDocname() {
         return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public List<String> getAuthornames() {
        return authornames;
    }

    public void setAuthornames(List<String> authornames) {
        this.authornames = authornames;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getPublictime() {
        return publictime;
    }

    public void setPublictime(String publictime) {
        this.publictime = publictime;
    }

    public String getPeriname() {
        return periname;
    }

    public void setPeriname(String periname) {
        this.periname = periname;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getViewtime() {
        return viewtime;
    }

    public void setViewtime(String viewtime) {
        this.viewtime = viewtime;
    }

    public Integer getRemindflag() {
        return remindflag;
    }

    public void setRemindflag(Integer remindflag) {
        this.remindflag = remindflag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "文献信息{" +
                "文献编号=" + docid +
                ", 文献名称='" + docname + '\'' +
                ", 作者名称=" + authorname +
                ", 发布时间=" + publictime +
                ", 期刊名称='" + periname + '\'' +
                ", 关键字='" + keyword + '\'' +
                ", 标签=" + tag.getTagname() +
                ", 上传时间=" + uploadtime +
                ", 最后一次阅览时间=" + viewtime +
                '}';
    }

}
