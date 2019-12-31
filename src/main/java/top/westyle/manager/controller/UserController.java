package top.westyle.manager.controller;

import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.westyle.manager.config.shiro.ShiroSessionListener;
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

    @PostMapping("add")
    public Result testinsert(){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString().replaceAll("-",""));
            user.setUserName("yanjunminggg"+i);
            user.setPassword("yanjunmingg" + i);
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
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
       subject.login(token);
       System.out.println("当前登录人数：" +ShiroSessionListener.getSessionCount());
       Map<String, String> res = new HashMap<>();
       res.put("token", subject.getSession().getId().toString());
       User loginUser =(User)subject.getPrincipal();
       System.out.println(loginUser.getPassword());
       return Result.success(res);
    }

    @GetMapping("logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        User loginUser =(User)subject.getPrincipal();
        System.out.println("loginUser: " + loginUser.getUserName());
        subject.logout();

        return new Result(ResponseCode.success.getCode(), ResponseCode.success.getMsg());
    }

    /**
     * 当用户被踢出后给用户反馈提示信息
     * @return
     */
    @GetMapping("kickOut")
    public Result kickOut() {
        return new Result(ResponseCode.force_logout.getCode(), ResponseCode.force_logout.getMsg());
    }
}
