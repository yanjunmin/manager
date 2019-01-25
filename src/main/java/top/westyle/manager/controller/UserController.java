package top.westyle.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.westyle.manager.entity.User;
import top.westyle.manager.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
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

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        /**
         * 返回结果
         */
        Map<String, String> map = new HashMap<>();
        int num = 0;
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(user.toString());
        if(user != null && user.getUserId() != null) {
            User u = userService.fingUserByUserId(user.getUserId());
            if(u != null){
                map.put("state","1");
                map.put("info", "该账号已被注册");
            }else{
              num = userService.addUser(user);
                if(num > 0) {
                    map.put("state", "0");
                }else{
                    map.put("state", "1");
                    map.put("info", "数据异常,请联系管理员");
                }
            }
        }
       return map.toString();
    }
}
