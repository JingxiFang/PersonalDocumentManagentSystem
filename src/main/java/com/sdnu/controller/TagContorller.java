package com.sdnu.controller;

import com.sdnu.domain.Tag;
import com.sdnu.domain.User;
import com.sdnu.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagContorller {

    @Autowired
    private ITagService tagService;
    /**
     * 添加新的标签
     * @return
     */
    @RequestMapping("/savetag")
    public @ResponseBody String saveTag(String tagname,ModelMap modelMap){
        System.out.println("saveTag");

        Tag tag=new Tag();

        //获取tagname
        tag.setTagname(tagname);

        //获取userid
        //获取用户编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        tag.setUser(user);

        tagService.saveTag(tag);

        return "success";
    }

    /**
     * 查找所有
     * @param modelMap
     * @return
     */
    @RequestMapping("/findtagbyuserid")
    public @ResponseBody List<Tag> findTagByUserId(ModelMap modelMap){
        //在session中获取该用户编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        List<Tag> tagList=tagService.findTagByUserId(userid);

        return tagList;
    }
}
