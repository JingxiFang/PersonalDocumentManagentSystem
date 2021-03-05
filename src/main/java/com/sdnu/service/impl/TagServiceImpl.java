package com.sdnu.service.impl;

import com.sdnu.dao.ITagDao;
import com.sdnu.domain.Tag;
import com.sdnu.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl implements ITagService {

    @Autowired
    private ITagDao tagDao;


    /**
     * 根据用户编号查找标签
     *
     * @param id
     * @return
     */
    @Override
    public List<Tag> findTagByUserId(int id) {
        return tagDao.findTagByUserId(id);
    }


    /**
     * 新增一个标签
     * @param tag 新增标签信息
     */
    @Override
    public void saveTag(Tag tag) {
        tagDao.saveTag(tag);
    }
}
