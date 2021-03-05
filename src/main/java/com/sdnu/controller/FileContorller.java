package com.sdnu.controller;
import com.sdnu.domain.Doc;
import com.sdnu.domain.User;
import com.sdnu.service.IDocService;
import com.sdnu.service.IUserService;
import com.sdnu.utils.JsonMsg;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@SessionAttributes(value={"user"})
@RequestMapping("/file")
@Controller
public class FileContorller {

    @Autowired
    IDocService docService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JsonMsg jsonMsg;

    /**
     * 普通文件上传
     * @param request
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/savefile")
    public @ResponseBody String saveFile(ModelMap modelMap,Doc doc, HttpServletRequest request, HttpServletResponse response, MultipartFile uploadFile, ModelMap map) throws IOException {

       System.out.println("saveFile");//测试语句

        //检测用户输入的文献属性（文献名称，用户名，上传时间）是否完善


        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        doc.setUser(user);

       if("".equals(doc.getPublictime())){
           doc.setPublictime("1990-01-01");
       }

        //检测信息是否完善
        if(uploadFile==null){
            return "必须选择文件";
        }else if("".equals(doc.getDocname())){
            return "必须填写文献姓名";
        }else if("".equals(user.getUserid())){
           return "系统错误";
        }

        //上传文件
        String filepath=uploadFile(request,uploadFile,0);
        doc.setFilepath(filepath);

        //System.out.println(doc.getTag().getId());
        //插入数据库
        docService.saveDoc(doc);

        response.sendRedirect(request.getContextPath()+"/searchfile.html");

        return "success";
    }

    /**
     * 修改用户详细信息
     * @param request
     * @param header
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/updateuserheader")
    public void updateUserHeader(HttpServletRequest request,HttpServletResponse response,MultipartFile header, ModelMap modelMap,Model model) throws IOException {
        System.out.println("updateDetailInfo");

        //获取session中的user
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        //上传文件
        String filepath=uploadFile(request,header,0);

        //存储到数据库
        userService.updateUserHeader(userid,filepath);

        //更新session信息
        user.setHeader(filepath);
        model.addAttribute("user",user);

        //跳转
        response.sendRedirect(request.getContextPath()+"/pro-profile.html");
    }


    /**
     * 上传文件
     * @param request
     * @param uploadFile
     * @param flag 上传头像是1 上传文献是0
     * @return
     * @throws IOException
     */
    private  String uploadFile( HttpServletRequest request, MultipartFile uploadFile,int flag) throws IOException {
        //文献上传
        //上传文件放入的文件夹
        String filePath="";
        if(flag==0 )
        {
            filePath="assets/upload/";
        }
        else{
            filePath= "assets/upload/header/";
        }
        String path= request.getSession().getServletContext().getRealPath("/"+filePath);
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //MVCSpring上传文件
        String fileName=uploadFile.getOriginalFilename();
        String uuid=UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        fileName=uuid+"_"+fileName;
        uploadFile.transferTo(new File(file,fileName));

        return  filePath+fileName;
    }


    /**
     * 跨服务器上传
     * @param request
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/testFile2")
    public String testFile2(HttpServletRequest request, MultipartFile uploadFile) throws IOException {

        System.out.println("testFil  e2");
        //上传文件放入的文件夹
        String path = "http://localhost:9090/uploads/";
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        String fileName=uploadFile.getOriginalFilename();
        String uuid=UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        fileName=uuid+"_"+fileName;

        //创建客户端对象
        Client client=Client.create();
        //连接到文件服务器
        WebResource resource=client.resource(path+fileName);
        //上传
        resource.put(uploadFile.getBytes());

        return  "success";
    }
}
