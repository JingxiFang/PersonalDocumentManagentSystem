package com.sdnu.service;

import com.sdnu.domain.User;
import org.springframework.stereotype.Service;


public interface IUserService {


    /**
     * 登陆
     * @param phone
     * @param pwd
     * @return
     */
     public int checkLogin(String phone,String pwd);




    /**
     * 修改登陆ip和登陆时间，和登陆地址
     * @param user
     */
    public void updateLoginInfo(User user);


    /**
     * 获取用户详细信息
     * @param userid
     * @return
     */
   public User findUserDetailInfoByUserid(int userid);


    /**
     * 修改用户信息
     * @param user
     */
    public void updateUserDetailInfo(User user);


    /**
     * 修改密码
     * @param userid
     * @param pwd
     */
    public void updateUserForPwd(int userid,String pwd,String phone);


    /**
     * 修改手机号
     * @param userid
     * @param phone
     */
    public void updateUserForPhone(int userid,String phone);
    /**
     * 注册
     * @param phone
     * @param pwd
     */
    public void resgiter(String phone,String pwd);

    /**
     * 验证旧密码是否正确
     * @param userid
     * @param passpwd
     * @param phone
     * @return
     */
    public int findCountByPassPwd( int userid,String phone,String passpwd);

    /***
     * 查询用户的登陆信息
     * @param userid
     * @return
     */
    public User findLastLoginInfoByUserid(int userid);


    /**
     * 修改用户的头像
     * @param userid
     * @param header
     */
    public void updateUserHeader(int userid,String header);

    /**
     * 查看数据库中是已经存在某个手机号
     * @param phone
     * @return
     */
    public int findCountByPhone(String phone);

}

