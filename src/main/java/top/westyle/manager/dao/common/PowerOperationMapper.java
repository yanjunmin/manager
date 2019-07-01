package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.PowerOperation;

public interface PowerOperationMapper {
    int deleteByPrimaryKey(String id);

    int insert(PowerOperation record);

    int insertSelective(PowerOperation record);

    PowerOperation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PowerOperation record);

    int updateByPrimaryKey(PowerOperation record);
}