package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.PowerPage;

public interface PowerPageMapper {
    int deleteByPrimaryKey(String id);

    int insert(PowerPage record);

    int insertSelective(PowerPage record);

    PowerPage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PowerPage record);

    int updateByPrimaryKey(PowerPage record);
}