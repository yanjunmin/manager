package top.westyle.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class UserController {
    @RequestMapping("home")
    public String home() {
        return "您好，这是您第100次登录";
    }
}
