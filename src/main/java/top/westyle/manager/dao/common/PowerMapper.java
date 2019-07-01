package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.Power;

public interface PowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
}