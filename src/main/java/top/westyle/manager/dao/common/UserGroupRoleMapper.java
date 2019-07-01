package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.UserGroupRole;

public interface UserGroupRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserGroupRole record);

    int insertSelective(UserGroupRole record);

    UserGroupRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserGroupRole record);

    int updateByPrimaryKey(UserGroupRole record);
}