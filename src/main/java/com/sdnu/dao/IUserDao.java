package com.sdnu.dao;

import com.sdnu.domain.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {


    /**
     * 检查用户名账号是否匹配，如果匹配  返回userid
     * @param phone
     * @param pwd
     * @return
     */
    @Select("select IFNULL(MAX(userid),0) as userid from userinfo where loginphone=#{phone} and pwd=#{pwd}")
    public int findUserIdByPhoneAndPwd(@Param("phone") String phone,@Param("pwd") String pwd);

    /**
     * 通过登录账号查找用户编号
     * @param phone
     * @return
     */
    @Select("select IFNULL(MAX(userid),0) as userid from userinfo where loginphone=#{phone}")
    public int findUserIdByPhone( String phone);


    /**
     * 查看注册的手机号是不是已经存在了
     * @param phone
     * @return
     */
    @Select("select count(*) from userinfo where loginphone=#{phone}")
    public int findCountByPhone(String phone);


    /**
     * 添加新用户
     * @param user
     */
    @Select("insert into userinfo(loginphone,pwd) value(#{loginPhone},#{pwd})")
    @Options(useGeneratedKeys=true, keyProperty="userid", keyColumn="userid")
    public void saveUser(User user);


    /**
     * 向用户信息详情里增加新用户的记录
     * @param userid
     */
    @Select("insert info userDetail(userid) value(#{userid})")
    public void saveUserDetail(int userid);


    /**
     * 修改登陆信息
     * @param user
     */
    @Update("UPDATE userinfo set " +
            "lastip=nowip,nowip=#{nowIp},lastaddress=nowaddress,nowaddress=#{nowAddress},lastlogintime=nowlogintime,nowlogintime=now() " +
            "where userid=#{userid}")
    public void updateLoginInfo(User user);


    /**
     * 获取用户详细信息
     * @param userid
     * @return
     */
    @Select("select * from userdetail where userid=#{userid}")
    public User findUserDetailInfoByUserid(int userid);


    /**
     * 修改用户信息
     * @param user
     */
    @Update("update userdetail set email=#{email} ,gender=#{gender},name=#{name} " +
            "WHERE userid=#{userid} ")
    public void updateUserDetailInfo(User user);


    /**
     * 修改用户头像
     * @param userid
     * @param header
     */
    @Select("update userdetail set header=#{header} WHERE userid=#{userid} ")
    public void updateUserHeader(@Param("userid") int userid,@Param("header") String header);


    /**
     * 修改密码
     * @param userid
     * @param pwd
     */
    @Update("update userinfo set pwd=#{pwd} where userid=#{userid}")
    public void updateUserForPwd(@Param("userid") int userid,@Param("pwd") String pwd);


    /**
     * 修改手机号码
     * @param userid
     * @param phone
     */
    @Update("update userinfo set loginphone=#{phone} where userid=#{userid}")
    public void updateUserForPhone(@Param("userid") int userid,@Param("phone") String phone);

    /**
     * 验证密码是否正确
     * @param userid
     * @param passpwd
     * @return
     */
    @Select("select count(*) from userinfo where userid=#{userid} and loginphone=#{phone}and pwd=#{pwd}")
    public int findCountByPassPwd(@Param("userid") int userid,@Param("phone")String phone,@Param("pwd") String passpwd);


    /***
     * 查询用户的登陆信息
     * @param userid
     * @return
     */
    @Select("select lastip,lastaddress,lastlogintime from userinfo where userid=#{userid}")
    public User findLastLoginInfoByUserid(int userid);



}
