package top.westyle.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.westyle.manager.entity.common.Role;
import top.westyle.manager.entity.common.UserGroupRole;

import java.util.List;

/**
 * 角色信息管理服务
 */
public interface RoleService {
    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 根据角色ID修改角色信息
     * @param role
     * @return
     */
    int updateById(Role role);

    /**
     * 根据条件查询角色信息
     * @param role
     * @return
     */
    List<Role> findByCondition(Role role, Page<Role> page);

    /**
     * 批量插入角色信息
     * @param roles
     * @return
     */
    int insertBatchRole(List<Role> roles);

    /**
     * 添加用户组角色关联信息
     * @param userGroupRole
     * @return
     */
    int addUserGroupRole(UserGroupRole userGroupRole);

    /**
     * 更新用户组角色关联信息
     * @param userGroupRole
     * @return
     */
    int updateUserGroupRole(UserGroupRole userGroupRole);

    /**
     * 查询用户组角色关联信息
     * @param userGroupRole
     * @return
     */
    List<UserGroupRole> findUserGroupRole(UserGroupRole userGroupRole, Page<UserGroupRole> page);
}
