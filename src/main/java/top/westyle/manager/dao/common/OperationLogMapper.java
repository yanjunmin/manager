package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.OperationLog;

public interface OperationLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    OperationLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OperationLog record);

    int updateByPrimaryKey(OperationLog record);
}