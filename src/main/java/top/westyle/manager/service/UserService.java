package top.westyle.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.westyle.manager.entity.common.*;

import java.util.List;

/**
 * 用户管理service
 */
public interface UserService {
    /**
     * 添加单个用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户ID更新用户信息
     * @return
     */
    int updateUserById(User user);

    /**
     * 根据用户信息查询用户
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 添加用户账号关联的用户详细信息
     * @param userInfo
     * @return
     */
    int addUserDetailInfo(UserInfo userInfo);

    /**
     * 更新用户账号关联的用户详细信息
     * @param userInfo
     * @return
     */
    int updateUserDetailInfo(UserInfo userInfo);

    /**
     * 查询用户详细信息
     * @param userInfo
     * @return
     */
    List<UserInfo> findUserInfo(UserInfo userInfo);
    /**
     * 批量插入组装好的用户
     * @param users
     * @return
     */
    int insertBatchUser(List<User> users);

    /**
     * 添加用户角色信息
     * @param userRole
     * @return
     */
    int addUserRole(UserRole userRole);

    /**
     * 更新用户角色信息
     * @param userRole
     * @return
     */
    int updateUserRole(UserRole userRole);
    /**
     * 查询用户拥有的角色信息
     * @param userId
     * @return
     */
    List<UserRole> findUserRole(String userId);

    /**
     * 添加用户与用户组关联信息
     * @param userGroupUser
     * @return
     */
    int addUserGroupUser(UserGroupUser userGroupUser);

    /**
     * 更新用户组用户关联信息
     * @param userGroupUser
     * @return
     */
    int updateUserGroupUser(UserGroupUser userGroupUser);

    /**
     * 查询用户组用户信息
     * @param userGroupUser
     * @return
     */
    List<UserGroupUser> findUserGroupUser(UserGroupUser userGroupUser, Page<UserGroupUser> page);

    /**
     * 添加用户组信息
     * @param userGroup
     * @return
     */
    int addUserGroup(UserGroup userGroup);

    /**
     * 更新用户组信息
     * @param userGroup
     * @return
     */
    int updateUserGroup(UserGroup userGroup);

    /**
     * 查询用户组信息,分页
     * @param userGroup
     * @return
     */
    List<UserGroup> findUserGroup(UserGroup userGroup, Page<UserGroup> page);

}
