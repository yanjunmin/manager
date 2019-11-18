package top.westyle.manager.controller;

import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.westyle.manager.entity.common.Role;
import top.westyle.manager.entity.common.User;
import top.westyle.manager.service.RoleService;
import top.westyle.manager.service.UserService;
import top.westyle.manager.utils.ResponseCode;
import top.westyle.manager.utils.Result;

import javax.servlet.http.HttpServletRequest;
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
            user.setUserName("yanjdfsddugfg7ns"+i);
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
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
       try {
           subject.login(token);
           Map<String, String> res = new HashMap<>();
           res.put("token", subject.getSession().getId().toString());
           return Result.success(res);
       } catch (IncorrectCredentialsException e) {
           return new Result(ResponseCode.password_incorrect.getCode(), ResponseCode.password_incorrect.getMsg());
       }catch (LockedAccountException e){
           return new Result(ResponseCode.forbidden_account.getCode(), ResponseCode.forbidden_account.getMsg());
       }catch (AuthenticationException e){
           return new Result(ResponseCode.unknown_account.getCode(), ResponseCode.unknown_account.getMsg());
       }catch (Exception e){
          return Result.error();
       }
    }

    @GetMapping("logout")
    public Result logout(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return new Result(ResponseCode.success.getCode(), ResponseCode.success.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResponseCode.error.getCode(), ResponseCode.error.getMsg());
        }

    }
}
