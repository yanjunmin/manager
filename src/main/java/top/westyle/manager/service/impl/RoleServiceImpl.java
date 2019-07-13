package top.westyle.manager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.westyle.manager.entity.common.Role;
import top.westyle.manager.entity.common.UserGroupRole;
import top.westyle.manager.service.RoleService;

import java.util.List;
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public int addRole(Role role) {
        return 0;
    }

    @Override
    public int updateById(Role role) {
        return 0;
    }

    @Override
    public List<Role> findByCondition(Role role) {
        return null;
    }

    @Override
    public int insertBatchRole(List<Role> roles) {
        return 0;
    }

    @Override
    public int addUserGroupRole(UserGroupRole userGroupRole) {
        return 0;
    }

    @Override
    public int updateUserGroupRole(UserGroupRole userGroupRole) {
        return 0;
    }

    @Override
    public List<UserGroupRole> findUserGroupRole(UserGroupRole userGroupRole) {
        return null;
    }
}
