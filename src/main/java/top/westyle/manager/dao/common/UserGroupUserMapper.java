package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.UserGroupUser;

public interface UserGroupUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserGroupUser record);

    int insertSelective(UserGroupUser record);

    UserGroupUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserGroupUser record);

    int updateByPrimaryKey(UserGroupUser record);
}