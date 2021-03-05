package com.sdnu.domain;

import com.sdnu.utils.MD5;
import org.springframework.util.DigestUtils;

public class User {

    /**
     * 用户编号
     */
    private int userid;

    /**
     * 登录电话号码
     */
    private String loginPhone;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 上次登录ip
     */
    private String lastIp;

    /**
     * 上次登录地址
     */
    private String lastAddress;


    /**
     * 最后一次登录时间
     */
    private String lastLoginTime;

    /**
     * 上次登录ip
     */
    private String nowIp;

    /**
     * 上次登录地址
     */
    private String nowAddress;


    /**
     * 最后一次登录时间
     */
    private String nowLoginTime;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像存放地址
     */
    private String header;




    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        pwd=MD5.encryption("pdms"+pwd);
        this.pwd = pwd;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNowIp() {
        return nowIp;
    }

    public void setNowIp(String nowIp) {
        this.nowIp = nowIp;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getNowLoginTime() {
        return nowLoginTime;
    }

    public void setNowLoginTime(String nowLoginTime) {
        this.nowLoginTime = nowLoginTime;
    }

    @Override
    public String toString() {
        return "用户基本信息{" +
                "  上次登陆ip='" + lastIp + '\'' +
                ", 上次登陆地址='" + lastAddress + '\'' +
                ", 上次登陆时间='" + lastLoginTime + '\'' +
                ", 姓名='" + name + '\'' +
                ", 性别='" + gender + '\'' +
                ", 邮箱='" + email + '\'' +
                ", 头像='" + header + '\'' +
                '}';
    }

}
