package top.westyle.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.westyle.manager.entity.User;
import top.westyle.manager.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("home")
    public String home() {
        return "您好，这是您第100次登录";
    }

    @RequestMapping("add")
    public int addUser(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return userService.addUser(user);
    }
}
