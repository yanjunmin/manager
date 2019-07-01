package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.PageElement;

public interface PageElementMapper {
    int deleteByPrimaryKey(String id);

    int insert(PageElement record);

    int insertSelective(PageElement record);

    PageElement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PageElement record);

    int updateByPrimaryKey(PageElement record);
}