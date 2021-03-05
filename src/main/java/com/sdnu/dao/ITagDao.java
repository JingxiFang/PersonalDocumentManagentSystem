package com.sdnu.dao;

import com.sdnu.domain.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagDao {

    /**
     * 查询某一个用户创建的所有的标签
     * @return
     */
    @Select("select * from tag where userid=#{userid}")
    public List<Tag> findTagByUserId(int userid);


    /**
     * 根据标签编号查找标签信息
     * @param tagid
     * @return
     */
    @Select("select * from tag where tagid=#{tagid}")
    public Tag findTagByTagId(String tagid);


    /**
     * 新增一个标签
     * @param tag
     */
    @Insert("insert into tag(tagname,userid,createtime) value(#{tagname},#{user.id},now())")
    public void saveTag(Tag tag);



}
