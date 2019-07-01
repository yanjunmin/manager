package top.westyle.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.westyle.manager.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
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

    /**
     * 根据用户对象查询
     * @return
     */
    User findByUser(User user);
}
