package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.RolePower;

public interface RolePowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(RolePower record);

    int insertSelective(RolePower record);

    RolePower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePower record);

    int updateByPrimaryKey(RolePower record);
}