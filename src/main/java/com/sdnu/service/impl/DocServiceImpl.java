package com.sdnu.service.impl;

import com.sdnu.dao.IDocDao;
import com.sdnu.domain.Doc;
import com.sdnu.service.IDocService;
import com.sdnu.utils.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service("docService")
public class DocServiceImpl implements IDocService {


    @Autowired
    private IDocDao docDao;

    /**
     * 保存文献到数据库
     *
     * @param doc
     * @return
     */
    @Override
    public void saveDoc(Doc doc) {

        //保存到文献表中
        docDao.saveDoc(doc);
       //得到文献编号插入到noteinfo中
        docDao.saveNote(doc.getDocid());
    }

    /**
     * 组合条件 查找文献
     *
     * @param doc
     * @return
     */
    @Override
    public  List<Doc> findDocInfoByNeed(Doc doc) {

        return docDao.findDocInfoByNeed(doc);
    }

    /**
     * 通过文献编号查找文献
     *
     * @param docId
     * @return
     */
    @Override
    public Doc findDocByDocId(int docId) {

        return docDao.findDocByDocId(docId);
    }

    @Override
    public void deleteDocByDocId(int id) {

        docDao.deleteDoc(id);
    }

    /**
     * 修改文献
     *
     * @param doc
     */
    @Override
    public void updateDoc(Doc doc) {

        //修改文献的基本信息
        docDao.updateDocBaseInfo(doc);
        //修改文献的笔记信息
        int docId=doc.getDocid();
        String note=doc.getNote();
        docDao.updateDocNoteInfo(note,docId);
    }

    /***
     * 修改文献的最后一次浏览时间
     * @param docid
     */
    @Override
    public void updateDocForViewtime(int docid) {

        docDao.updateDocForViewtime(docid);
    }

    /***
     * 查询稍后阅读
     * @param userid
     * @return
     */
    @Override
    public List<Doc> findDocByFlag(int userid) {

        return docDao.findDocByFlag(userid);
    }

    /***
     * 查询近六个月的浏览记录
     * @param userid
     * @return
     */
    @Override
    public List<Doc> findDocByTime(int userid) {

        return docDao.findDocByTime(userid);
    }

    /***
     * 设置稍后再看
     * @param userid
     * @param docid
     */
    @Override
    public void updateDocForFlag(int userid, int docid,int flag) {
        docDao.updateDocForFlag(userid,docid,flag);
    }

    /***
     * 查询一共有多少文献
     * @param userid
     * @return
     */
    @Override
    public int findDocAllCountByUserid(int userid) {
        int count=docDao.findDocAllCountByUserid(userid);
        return count;
    }

    /***
     * 查看一共某个有多少稍后阅读的文献
     * @param userid
     * @return
     */
    @Override
    public int findCountOfLaterView(int userid) {
        return docDao.findCountOfLaterView(userid);
    }

    /***
     * 查询某月的上传数量
     * @param year
     * @param month
     * @param userid
     * @return
     */
    @Override
    public int findUploadCountOfMonthByUser(int year, int month, int userid) {
        int count=docDao.findUploadCountOfMonthByUser(year,month,userid);
        return count;
    }

    /**
     * 查询本月浏览数量
     * @param year
     * @param month
     * @param userid
     * @return
     */
    @Override
    public int findViewCountOfMonthByUser(int year, int month, int userid) {
        int count=docDao.findViewCountOfMonthByUser(year,month,userid);
        return count;
    }


    /**
     * 查找最近一年每个月的阅读量
     *
     * @param userid
     * @return
     */
    @Override
    public List<ChartData> findViewCountOfEveryMonthOfYearByUser(int userid) {
        return docDao.findViewCountOfEveryMonthOfYearByUser(userid);
    }

    /**
     * 查找最近一年每个月的上传量
     *
     * @param userid
     * @return
     */
    @Override
    public List<ChartData> findUploadCountOfEveryMonthOfYearByUser(int userid) {
        return docDao.findUploadCountOfEveryMonthOfYearByUser(userid);
    }


}
