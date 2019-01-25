package top.westyle.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.westyle.manager.entity.User;
import top.westyle.manager.mapper.UserMapper;
import top.westyle.manager.service.UserService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户操作接口实现
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {
    private static final long serialVersionUID = 1723944673037437988L;
    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public User fingUserByUserId(String userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public List<User> findUserBycondition(Map<String, String> map) {
        return null;
    }
}
