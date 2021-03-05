package com.sdnu.dao;

import com.sdnu.domain.Doc;
import com.sdnu.utils.ChartData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDocDao {

    /**
     * 查找所有文献
     * @return
     */
    @Select("select * from docInfo where userid=#{id}")
    public List<Doc> findAll(int id);


    /**
     * 组合条件 查找文献
     * @param doc
     * @return
     */
    @Select(value = {" <script>" +
            " SELECT * FROM docinfo " +
                " <where> 1=1 " +
                    " <if test=\"docname != null and docname !='' \" >  AND docname like CONCAT('%',#{docname},'%') </if> " +
                    " <if test=\"tag!=null\"> AND tagid =#{tag.tagid} </if> " +
                    " <if test=\"periname != null and periname !='' \" >  AND periname like CONCAT('%',#{periname},'%')</if> " +
                    " <if test=\"publictime != null and publictime !='' \" > AND publictime =#{publictime}</if> " +
                    " <if test=\"keywords!=null and keywords.size()>0\" >"+
                            "<foreach collection=\"keywords\" open=\"and keyword like \" close=\"\" item=\"keyword\" separator=\" and keyword like \">"+
                                "<if test=\"keyword!=null and keyword !='' \" >"+
                                    "CONCAT('%',#{keyword},'%')"+
                                "</if>"+
                            "</foreach>" +
                    " </if>"+
                    " <if test=\"authornames!=null and authornames.size()>0\">"+
                        " <foreach collection=\"authornames\" open=\"and authornames like \" close=\"\" item=\"authorname\" separator=\" and authorname like \">" +
                            " <if test=\"authorname!=null and authorname !=''\" >"+
                                "CONCAT('%',#{authorname},'%')"+
                            " </if>"+
                        "</foreach>" +
                    " </if>"+
                " <if test=\"user!=null\"> AND userid =#{user.userid} </if> " +
             " </where>" +
            " </script>"})
    public List<Doc> findDocInfoByNeed(Doc doc);


    /**
     * 通过文献编号查找文献
     * @param docid
     * @return
     */
    @Select(" select * from docinfo d " +
            " inner join noteinfo n on d.docid=n.docid " +
            " where d.docid=#{docid}")
    //需要查到tag的信息
    @Results(
            id="resultMap" ,
            value = {
                    @Result(
                            property = "tag",
                            column = "tagid",
                            one=@One(select = "com.sdnu.dao.ITagDao.findTagByTagId",fetchType = FetchType.EAGER)
                    )
            }
    )
    public Doc findDocByDocId(int docid);


    /**
     * 保存文献
     * @return
     */
    @Insert("insert into docinfo(" +
            "docname,authorname,publictime,periname,keyword," +
            "tagid,userid,uploadtime,filepath) " +
            "values(#{docname},#{authorname},#{publictime}," +
            "#{periname},#{keyword},#{tag.tagid},#{user.id}," +
            "now(),#{filepath})")
    @Options(useGeneratedKeys=true, keyProperty="docid", keyColumn="docid")
    public void saveDoc(Doc doc);


    /**
     * 添加文献笔记
     * @param docid
     */
    @Insert("insert noteinfo(docid) value(#{note})")
    public void saveNote(int docid);


    /**
     * 根据文献编号删除一个文献
     * @param id
     */
    @Delete("delete from docinfo where docid=#{id}")
    public void deleteDoc(int id);


    /**
     * 修改文献的基本信息
     * @param doc
     */
    @Update("update docinfo set docname=#{docname},authorname=#{authorname}," +
            " publictime=#{publictime},periname=#{periname},keyword=#{keyword}," +
            " tagid=#{tag.tagid} where docid=#{docid}")
    public void updateDocBaseInfo(Doc doc);


    /**
     * 修改文献的笔记内容
     * @param docid
     * @param
     */
    @Update("update noteinfo set note=#{note}  where docid=#{docid}")
    public void updateDocNoteInfo(@Param("note") String note,@Param("docid")int docid);


    /***
     * 修改文献的最后一次浏览时间
     * @param docid
     */
    @Update("update docinfo set viewtime=now() where docid=#{docid}")
    public void updateDocForViewtime(int docid);


    /***
     * 查看近半年的历史记录
     * @return
     */
    @Select("select * from docinfo where userid=#{userid} and viewtime between date_sub(now(),interval 6 month)  and now() ORDER BY viewtime DESC;")
    public List<Doc> findDocByTime(int userid);


    /**
     * 查看稍后再看的内容
     * @return
     */
    @Select("select * from docinfo where userid=#{userid} and remindflag=0")
    public List<Doc> findDocByFlag(int userid);


    /***
     * 设置稍后再看
     * @param userid
     * @param docid
     */
    @Update("update docinfo set remindflag=#{flag} where userid=#{userid} and docid=#{docid}")
    public void updateDocForFlag(@Param("userid") int userid,@Param("docid") int docid,@Param("flag") int flag);



    /***
     * 查询一共有多少文献
     * @param userid
     * @return
     */
    @Select("select count(*) from docinfo where userid=#{userid}")
    public int findDocAllCountByUserid(int userid);


    /**
     * 查看稍后再看的数量
     * @param userid
     * @return
     */
    @Select("select count(*) from docinfo where userid=#{userid} and remindflag=0")
    public  int findCountOfLaterView(int userid);


    /***
     * 查询某月的上传数量
     * @param year
     * @param month
     * @param userid
     * @return
     */
    @Select("select count(*) from docinfo where year(uploadtime)=#{year} and month(uploadtime)=#{month} and userid=#{userid}")
    public int findUploadCountOfMonthByUser(@Param("year") int year,@Param("month") int month,@Param("userid") int userid);


    /**
     * 查询某月浏览数量
     * @param year
     * @param month
     * @param userid
     * @return
     */
    @Select("select count(*) from docinfo where year(viewtime)=#{year} and month(viewtime)=#{month} and userid=#{userid}")
    public int findViewCountOfMonthByUser(@Param("year") int year,@Param("month") int month,@Param("userid") int userid);

    /**
     * 查找最近一年每个月的阅读量
     * @param userid
     * @return
     */
    @Select("select count(*) as count,left(viewtime,7) as month from docinfo " +
            "where DATE_FORMAT(docinfo.viewtime,'%Y-%m') > DATE_FORMAT(date_sub(curdate(), interval 12 month),'%Y-%m') " +
            "and userid=#{userid} " +
            "group by month desc")
    public List<ChartData> findViewCountOfEveryMonthOfYearByUser(int userid);


    /**
     * 查找最近一年每个月的上传量
     * @param userid
     * @return
     */
    @Select("select count(*) as count,left(uploadtime,7) as month from docinfo " +
            "where DATE_FORMAT(docinfo.uploadtime,'%Y-%m') > DATE_FORMAT(date_sub(curdate(), interval 12 month),'%Y-%m') " +
            "and userid=#{userid} " +
            "group by month desc")
    public  List<ChartData> findUploadCountOfEveryMonthOfYearByUser(int userid);



}
