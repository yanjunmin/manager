package top.westyle.manager.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.westyle.manager.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param user
     * @return
     */
   int addUser(User user);

    /**
     * 查找用户
     * @param id
     * @return
     */
    User findUserById(String id);

    User findUserByUserId(String userId);

    /**
     * 根据条件查询多个用户信息
     * @param map
     * @return
     */
    List<User> findUserBycondition(Map<String, String> map);

}
