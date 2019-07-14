package top.westyle.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.westyle.manager.entity.common.Role;
import top.westyle.manager.entity.common.User;
import top.westyle.manager.service.RoleService;
import top.westyle.manager.service.UserService;
import top.westyle.manager.utils.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
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
    @PostMapping("login")
    public Response login(@RequestBody User user){
        User userinfo = userService.findUserByCondition(user).get(0);
        return new Response("0","登录成功", userinfo);
    }

    @RequestMapping("inser")
    public Response testinsert(){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString().replaceAll("-",""));
            user.setUserName("yanjdfsddugfgn"+i);
            user.setPassword("123456" + i);
            //userService.addUser(user);
            users.add(user);
        }
       int num = userService.insertBatchUser(users);
        Role role = new Role();
        role.setId("dddd");
        role.setRoleName("东风股份");
        roleService.addRole(role);
        System.err.println(num);
        return new Response("0","登录成功", null);
    }
}
