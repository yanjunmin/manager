package top.westyle.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.westyle.manager.dao.common.UserMapper;
import top.westyle.manager.entity.common.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
    }

    @Test
    public void addInsertBatch(){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString().replaceAll("-",""));
            user.setUserName("yanjunin"+i);
            user.setPassword("123456" + i);
            userMapper.insertSelective(user);
            users.add(user);
        }
       //userMapper.inserBatchUser(users);
    }
}
