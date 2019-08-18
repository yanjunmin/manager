package top.westyle.manager.controller;

import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.westyle.manager.entity.common.Role;
import top.westyle.manager.entity.common.User;
import top.westyle.manager.service.RoleService;
import top.westyle.manager.service.UserService;
import top.westyle.manager.utils.ResponseCode;
import top.westyle.manager.utils.Result;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("home")
    public int index(){
        User user = new User();
        user.setPassword("123");
        user.setId("7878");
        return userService.updateUserById(user);
    }
    @RequestMapping("test")
    public String test(){
        return "您好!这是您第一百次登录!";
    }

    @RequestMapping("add")
    public Result testinsert(){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString().replaceAll("-",""));
            user.setUserName("yanjdfsddugfgns"+i);
            user.setPassword("123456" + i);
            //userService.addUser(user);
            users.add(user);
        }
       int num = userService.insertBatchUser(users);
        Role role = new Role();
        role.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        role.setRoleName("东风股份");
        roleService.addRole(role);
        System.err.println(num);
        return new Result(ResponseCode.success.getCode(),ResponseCode.success.getMsg());
    }

   //@Encrypt
   @Decrypt
    @PostMapping("login")
    public Result login(@RequestBody User user){
        System.out.println(user.getUserName());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        subject.login(token);
        Map<String, String> res = new HashMap<>();
        res.put("token", subject.getSession().getId().toString());
        return Result.success(res);
    }
}
