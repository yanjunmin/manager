package top.westyle.manager.service;

import top.westyle.manager.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作业务接口
 */
public interface UserService {
    /**
     * 添加单个用户
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    public User findUserById(String id);

    /**
     * 根据传入条件查询多个或一个用户信息
     * @param map
     * @return
     */
    public List<User> findUserBycondition(Map<String, String> map);
}
