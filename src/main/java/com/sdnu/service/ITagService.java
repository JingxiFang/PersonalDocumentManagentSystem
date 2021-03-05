package com.sdnu.service;

import com.sdnu.domain.Tag;

import java.util.List;

public interface ITagService {

    /**
     * 根据用户编号查找标签
     * @param id 用户编号
     * @return 该用户创建的所有的标签
     */
    public List<Tag> findTagByUserId(int id);

    /**
     * 新增一个标签
     * @param tag 新增标签信息
     */
    public void saveTag(Tag tag);

}
