package com.sdnu.controller;

import com.sdnu.domain.User;
import com.sdnu.service.IUserService;
import com.sdnu.utils.JsonMsg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RequestMapping("/user")
@Controller
@SessionAttributes(value={"user"})
public class UserContorller {

    @Autowired
    private IUserService userService;

    @Autowired
    private JsonMsg jsonMsg;


    /**
     * 登录
     * @param request
     * @param response
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping("/login")
    public @ResponseBody JsonMsg login(HttpServletRequest request, HttpServletResponse response,User user,Model model) throws IOException {
        System.out.println("login");

        //验证用户名密码
        int userid= 0;
        userid=userService.checkLogin(user.getLoginPhone(),user.getPwd());

        if(userid==0){
            //验证失败
            jsonMsg.setMsg("fail");
            return jsonMsg;
        }

        //验证成功

        //修改登陆信息
        userService.updateLoginInfo(user);

        //获取用户的基本信息放到session中
        User userinfo=new User();
        user.setUserid(userid);
        userinfo=userService.findUserDetailInfoByUserid(userid);
        userinfo.setLoginPhone(user.getLoginPhone());

        model.addAttribute("user",userinfo);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }


    /**
     * 注册
     * @param loginPhone
     * @param pwd
     */
    @RequestMapping("/resgiter")
    public @ResponseBody JsonMsg resgiter(String loginPhone,String pwd){
        System.out.println("pwd");

        //后台检测手机号和密码合格
        //算了太麻烦不写了

        //注册
        userService.resgiter(loginPhone,pwd);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }

    /**
     * 验证原密码
     * @param pwdpass
     * @param modelMap
     * @return
     */
    @RequestMapping("/verifyPwd")
    public @ResponseBody JsonMsg verifyPwd(String pwdpass,ModelMap modelMap){
        System.out.println("verifyPwd");
        //从session中获取用户的手机号和编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();
        String phone=user.getLoginPhone();

        //检测原来的密码是否正确
        int count = userService.findCountByPassPwd(userid,phone,pwdpass);
        if(count != 1){
            jsonMsg.setMsg("fail");
            return jsonMsg;
        }
        //如果正确，修改密码
        jsonMsg.setMsg("success");
        return jsonMsg;
    }

    /**
     * 修改密码

     * @param modelMap
     * @return
     */
    @RequestMapping("/updatepwd")
    public @ResponseBody JsonMsg updatePwd(String pwd,String loginPhone,ModelMap modelMap){
        System.out.println("updatePwd");

        int userid;

        if(!"".equals(loginPhone)){
            userid=0;
        }
        else {
            //从session中获取用户的手机号和编号
            User user= (User) modelMap.get("user");
            userid=user.getUserid();
            loginPhone=user.getLoginPhone();
        }

        //修改密码
        userService.updateUserForPwd(userid,pwd,loginPhone);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }


    /**
     * 修改用户手机号码
     *
     * @param phone
     * @param modelMap
     * @return
     */
    @RequestMapping("/updatephone")
    public  @ResponseBody JsonMsg updatePhone(String phone,ModelMap modelMap){
        System.out.println("updatePhone");
        //从session中获取用户的手机号和编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        //修改密码
        userService.updateUserForPhone(userid,phone);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }

    /**
     * 查看数据库中是已经存在某个手机号
     * @return
     */
    @RequestMapping("/checkphone")
    public @ResponseBody JsonMsg findCountByPhone(String loginPhone){

        if(userService.findCountByPhone(loginPhone)>0){
            jsonMsg.setMsg("fail");
        }
        else {
            jsonMsg.setMsg("success");
        }

        return  jsonMsg;


    }


    /**
     * 查询上次的登陆信息
     * @return
     */
    @RequestMapping("/findlastlogininfo")
    public @ResponseBody User findLastLoginInfo(ModelMap modelMap){
        System.out.println("findLastLoginInfo");
        //从session中获取userid
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();
        User userinfo=userService.findLastLoginInfoByUserid(userid);
        return userinfo;
    }


    /**
     * 忘记这是个啥
     * @param modelMap
     * @return
     */
    @RequestMapping("/finduserdetailinfo")
    public  @ResponseBody User findUserDetailInfo(ModelMap modelMap){
        System.out.println("findUserDetailInfo");

        //获取信息
        // User user = (User) modelMap.get("user");
        //假装获取到消息
        User user=userService.findUserDetailInfoByUserid(1);
        user.setLoginPhone("17854153309");

        return user;
    }

    /**
     * 修改用户详细信息
     * @param user
     * @return
     */
    @RequestMapping("/updatedetailinfo")
    public void updateDetailInfo(HttpServletResponse response,HttpServletRequest request, User user,Model model,ModelMap modelMap) throws IOException {
        System.out.println("updateDetailInfo");
        //获取session中的userid
        User userSession= (User) modelMap.get("user");
        int userid=userSession.getUserid();

        //把userid 添加到user中用于更新数据
        user.setUserid(userid);
        //修改用户信息
        userService.updateUserDetailInfo(user);

        //如果成功了 获取用户详细
        User newUser=userService.findUserDetailInfoByUserid(user.getUserid());
        //更新用户的信息
        //需要检测此处是否需要删除session中原有的数据
        model.addAttribute("user",newUser);

        //跳转
        response.sendRedirect(request.getContextPath()+"/pro-profile.html");
    }

    /**
     * 获取IP地址(没用上呢)
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        return ip;
    }



}
