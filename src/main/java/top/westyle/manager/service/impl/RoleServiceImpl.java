package top.westyle.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.westyle.manager.dao.common.RoleMapper;
import top.westyle.manager.entity.common.Role;
import top.westyle.manager.entity.common.UserGroupRole;
import top.westyle.manager.service.RoleService;

import java.util.List;
//@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateById(Role role) {
        return 0;
    }

    @Override
    public List<Role> findByCondition(Role role, Page page) {
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
    public List<UserGroupRole> findUserGroupRole(UserGroupRole userGroupRole, Page page) {
        return null;
    }
}
