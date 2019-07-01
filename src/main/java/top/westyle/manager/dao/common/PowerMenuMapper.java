package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.PowerMenu;

public interface PowerMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(PowerMenu record);

    int insertSelective(PowerMenu record);

    PowerMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PowerMenu record);

    int updateByPrimaryKey(PowerMenu record);
}