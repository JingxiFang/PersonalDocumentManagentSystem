package com.sdnu.controller;

import com.sdnu.domain.Doc;
import com.sdnu.domain.User;
import com.sdnu.service.IDocService;

import com.sdnu.utils.ChartData;
import com.sdnu.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("/doc")
public class DocContorller {

    @Autowired
    private IDocService docService;

    @Autowired
    private JsonMsg jsonMsg;

    /**
     * 多条件查询文献
     *
     * @return
     */
    @RequestMapping("/finddocbyuserneed")
    public  @ResponseBody List<Doc> findDocByUserNeed(Doc doc, ModelMap modelMap){
        System.out.println("findNeed");

        doc.setAuthornames(splitMul(doc.getAuthorname()));
        doc.setKeywords(splitMul(doc.getKeyword()));
        if(doc.getTag()!=null){
            if(doc.getTag().getTagid()==0){
                doc.setTag(null);
            }
        }
        //查找用户的id
        User user= (User) modelMap.get("user");
        doc.setUser(user);

        List<Doc> docs= docService.findDocInfoByNeed(doc);

        return docs;
    }

    /**
     * 分割
     * @param str
     * @return
     */
    private List<String> splitMul(String str){
        if(!"".equals(str) &&str!=null){
            String[] strings=str.split(";|；");
            return Arrays.asList(strings);
        }
        else{
            return null;
        }

    }

    /**
     * 根据文献编号删除一个文献
     * @param docid
     * @return
     */
    @RequestMapping("/deletedocbydocid")
    public @ResponseBody JsonMsg deleteDocByDocID(int docid){
        System.out.println("delete");

        docService.deleteDocByDocId(docid);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }

    /**
     * 通过id查看某一个文献
     * @param docid
     * @return
     */
    @RequestMapping("/finddocbydocid")
    public @ResponseBody Doc findDocByDocId(int docid){
        System.out.println("findViewByDocId");

        //查找某个文献
        Doc doc = docService.findDocByDocId(docid);
        //设置该文献的最后查阅日期
        docService.updateDocForViewtime(docid);

        return doc;
    }


    /**
     * 修改文献（各类基本信息）
     * @param doc
     * @return
     */
    @RequestMapping("/updatedocinfo")
    public @ResponseBody JsonMsg updateDoc(Doc doc){
        System.out.println("updateDoc");

        docService.updateDoc(doc);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }


    /**
     * 修改文献的最后查阅时间
     * @param docid
     * @return
     */
    @RequestMapping("/updatedocforviewtime")
    public @ResponseBody JsonMsg updateDocForViewtime(int docid){
        System.out.println("updateDocForViewtime");

        docService.updateDocForViewtime(docid);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }

    /***
     * 查看近半年的历史记录
     * @return
     */
    @RequestMapping("/finddocbytime")
    public @ResponseBody List<Doc> findDocByTime(ModelMap modelMap){
        System.out.println("findDocByTime");

        //获取用户编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        List<Doc> docs=docService.findDocByTime(userid);
        return docs;
    }


    /**
     * 查看稍后再看的内容
     * @return
     */
    @RequestMapping("/finddocbyflag")
    public @ResponseBody List<Doc> findDocByFlag(ModelMap modelMap){
        System.out.println("findDocByFlag");

        //获取用户编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();


        return docService.findDocByFlag(userid);
    }


    /**
     * 获取某个用户上传的所有的文献数量
     * @return
     */
    @RequestMapping("/finddoccountbyuserid")
    public @ResponseBody JsonMsg findDocCountByUserid(ModelMap modelMap){
        System.out.println("findDocCountByUserid");

        //获取当前日期
        Calendar cal = Calendar.getInstance();
        //int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        //获取用户编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        //获取全部文献数量
        int allCount=docService.findDocAllCountByUserid(userid);
        jsonMsg.setAllCount(allCount);
        //获取本月上传的文献数量
        int uploadCountOfMonth=docService.findUploadCountOfMonthByUser(year,month,userid);
        jsonMsg.setNowMonthUpload(uploadCountOfMonth);
        //获取本月浏览数量
        int nowMonthView=docService.findViewCountOfMonthByUser(year,month,userid);
        jsonMsg.setNowMonthView(nowMonthView);
        //获取稍后再看的数量
        int  laterCount=docService.findCountOfLaterView(userid);
        jsonMsg.setLaterview(laterCount);

        List<ChartData>  nowMonthViewList=docService.findViewCountOfEveryMonthOfYearByUser(userid);
        jsonMsg.setMonthViewOfYear(nowMonthViewList);

        List<ChartData> nowMonthUploadList=docService.findUploadCountOfEveryMonthOfYearByUser(userid);
        jsonMsg.setMonthUpladeOfYear(nowMonthUploadList);


        //传送到前台
        return jsonMsg;
    }

    /**
     * 设置稍后再看
     * @param docid
     * @return
     */
    @RequestMapping("updatedocforflag")
    public @ResponseBody JsonMsg updateDocForFlag(int docid,int flag,ModelMap modelMap){
        //获取用户编号
        User user= (User) modelMap.get("user");
        int userid=user.getUserid();

        docService.updateDocForFlag(userid,docid,flag);

        jsonMsg.setMsg("success");
        return jsonMsg;
    }


}
