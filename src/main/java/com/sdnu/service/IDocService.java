package com.sdnu.service;

import com.sdnu.domain.Doc;
import com.sdnu.utils.ChartData;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IDocService {

    /**
     * 保存文献到数据库
     * @param doc
     * @return
     */
    public void saveDoc(Doc doc);


    /**
     * 组合条件 查找文献
     * @param doc
     * @return
     */
    public List<Doc> findDocInfoByNeed(Doc doc);

    /**
     * 通过文献编号查找文献
     * @param docId
     * @return
     */
    public Doc findDocByDocId(int docId);


    /**
     * 根据文献编号删除文献
     * @param id  文献编号
     */
    public void deleteDocByDocId(int id);

    /**
     * 修改文献
     * @param doc
     */
    public void updateDoc(Doc doc);


    /***
     * 修改文献的最后一次浏览时间
     * @param docid
     */
    public void updateDocForViewtime(int docid);


    /***
     * 查询稍后阅读
     * @param userid
     * @return
     */
    public List<Doc> findDocByFlag(int userid);


    /***
     * 查询近六个月的浏览记录
     * @param userid
     * @return
     */
    public List<Doc> findDocByTime(int userid);


    /**
     * 设置稍后再看
     * @param userid
     * @param docid
     * @param flag
     */
    public void updateDocForFlag(int userid,int docid,int flag);



    /***
     * 查询一共有多少文献
     * @param userid
     * @return
     */
   public int findDocAllCountByUserid(int userid);

    /***
     * 查看一共某个有多少稍后阅读的文献
     * @param userid
     * @return
     */
    public  int findCountOfLaterView(int userid);


    /***
     * 查询某月的上传数量
     * @param year
     * @param month
     * @param userid
     * @return
     */
    public int findUploadCountOfMonthByUser(int year,int month, int userid);


    /**
     * 查询本月浏览数量
     * @param year
     * @param month
     * @param userid
     * @return
     */
    public int findViewCountOfMonthByUser(int year,int month,int userid);


    /**
     * 查找最近一年每个月的阅读量
     * @param userid
     * @return
     */
    public List<ChartData> findViewCountOfEveryMonthOfYearByUser(int userid);


    /**
     * 查找最近一年每个月的上传量
     * @param userid
     * @return
     */
    public  List<ChartData> findUploadCountOfEveryMonthOfYearByUser(int userid);

}
