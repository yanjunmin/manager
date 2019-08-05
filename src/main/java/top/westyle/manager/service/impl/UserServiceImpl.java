package top.westyle.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.westyle.manager.dao.common.UserMapper;
import top.westyle.manager.entity.common.*;
import top.westyle.manager.service.UserService;
import top.westyle.manager.utils.ShiroUtil;

import java.util.List;
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        user.setPasswordSalt(ShiroUtil.createSalt());//用户加密盐值
        user.setPassword(ShiroUtil.salt(user.getPassword(), user.getPasswordSalt()));//给密码加密
        return userMapper.insertSelective(user);
    }


    @Override
    public int updateUserById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User findByUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        return userMapper.selectOne(new QueryWrapper<>(user));
    }

    @Override
    public List<User> findUserByCondition(User user) {
        return user == null ? null : userMapper.selectList( new QueryWrapper<>(user));
    }

    @Override
    public int addUserDetailInfo(UserInfo userInfo) {
        return 0;
    }

    @Override
    public int updateUserDetailInfo(UserInfo userInfo) {
        return 0;
    }

    @Override
    public List<UserInfo> findUserInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    public int insertBatchUser(List<User> userlist) {
        int count = 0;
        for (User user:userlist
             ) {
            count += userMapper.insert(user);
        }
        return count;
    }

    @Override
    public int addUserRole(UserRole userRole) {
        return 0;
    }

    @Override
    public int updateUserRole(UserRole userRole) {
        return 0;
    }

    @Override
    public List<UserRole> findUserRole(String userId) {
        return null;
    }

    @Override
    public int addUserGroupUser(UserGroupUser userGroupUser) {
        return 0;
    }

    @Override
    public int updateUserGroupUser(UserGroupUser userGroupUser) {
        return 0;
    }

    @Override
    public List<UserGroupUser> findUserGroupUser(UserGroupUser userGroupUser, Page<UserGroupUser> page) {
        return null;
    }

    @Override
    public int addUserGroup(UserGroup userGroup) {
        return 0;
    }

    @Override
    public int updateUserGroup(UserGroup userGroup) {
        return 0;
    }

    @Override
    public List<UserGroup> findUserGroup(UserGroup userGroup, Page<UserGroup> page) {
        return null;
    }

}
