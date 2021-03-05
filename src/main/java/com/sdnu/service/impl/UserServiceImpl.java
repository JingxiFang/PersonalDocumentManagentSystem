package com.sdnu.service.impl;

import com.sdnu.dao.IUserDao;
import com.sdnu.domain.User;
import com.sdnu.service.IUserService;
import com.sdnu.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 登陆
     * @param phone
     * @param pwd
     * @return
     */
    @Override
    public int checkLogin(String phone, String pwd) {
        pwd=MD5.encryption(pwd);
        //获取到用户编号
        int userid=userDao.findUserIdByPhoneAndPwd(phone,pwd);

        return userid;
    }

    /**
     * 修改登陆ip和登陆时间，和登陆地址
     *
     * @param user
     */
    @Override
    public void updateLoginInfo(User user) {
        userDao.updateLoginInfo(user);
    }

    /**
     * 获取用户详细信息
     *
     * @param userid
     * @return
     */
    @Override
    public User findUserDetailInfoByUserid(int userid) {
        return userDao.findUserDetailInfoByUserid(userid);
    }

    /**
     * 修改用户信息
     *
     * @param user
     */
    @Override
    public void updateUserDetailInfo(User user) {
        userDao.updateUserDetailInfo(user);
    }

    /**
     * 修改密码
     *
     * @param userid
     * @param pwd
     */
    @Override
    public void updateUserForPwd(int userid, String pwd,String phone) {
        if(userid==0){
            //获取用户编号
            userid=userDao.findUserIdByPhone(phone);
        }
        pwd=MD5.encryption(pwd);
        userDao.updateUserForPwd(userid,pwd);
    }

    /**
     * 修改手机号
     *
     * @param userid
     * @param phone
     */
    @Override
    public void updateUserForPhone(int userid, String phone) {
        userDao.updateUserForPhone(userid,phone);
    }

    /**
     * 注册
     *
     * @param phone
     * @param pwd
     */
    @Override
    public void resgiter(String phone, String pwd) {
        User user=new User();
        user.setLoginPhone(phone);
        user.setPwd(pwd);

        if(userDao.findCountByPhone(phone)!=0){
            return ;
        }
        userDao.saveUser(user);

        //这个地方有错误  (什么错误？)
        userDao.saveUserDetail(user.getUserid());
    }

    /**
     * 查看数据库中是已经存在某个手机号
     * @param phone
     * @return
     */
    public int findCountByPhone(String phone){
        return userDao.findCountByPhone(phone);
    }


    /**
     * 验证旧密码是否正确
     *
     * @param userid
     * @param passpwd
     * @param phone
     * @return
     */
    @Override
    public int findCountByPassPwd(int userid, String phone, String passpwd) {
        passpwd=MD5.encryption(passpwd);
        return userDao.findCountByPassPwd(userid,phone,passpwd);
    }

    /***
     * 查询用户的登陆信息
     * @param userid
     * @return
     */
    @Override
    public User findLastLoginInfoByUserid(int userid){
        return userDao.findLastLoginInfoByUserid(userid);
    }

    /**
     * 修改用户的头像
     *
     * @param userid
     * @param header
     */
    @Override
    public void updateUserHeader(int userid, String header) {
        userDao.updateUserHeader(userid,header);
    }

}
