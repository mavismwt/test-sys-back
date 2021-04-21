package mwt.testsysback.controller;

import mwt.testsysback.entity.User;
import mwt.testsysback.common.CommonResult;
import mwt.testsysback.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    public CommonResult getUsers(@RequestParam Map map) {
        List<User> users = userService.getUsers(map.get("nickname").toString(),map.get("collection").toString(),map.get("identity").toString());
        if (users != null) {
            return CommonResult.success(users);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public CommonResult getUser(@RequestParam("user_id") int user_id){
        User user = userService.getUserById(user_id);
        if (user != null) {
            return CommonResult.success(user);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public CommonResult updateUser(@RequestBody User user) {
        if (userService.updateUser(user) >= 1) {
            User user1 = userService.getUserById(user.getUser_id());
            return CommonResult.success(user);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult login2(@RequestBody User user) {
        User user1 = userService.login(user);
        if (user1 != null) {
            return CommonResult.success(user1);
        } else {
            return CommonResult.validateFailed();
        }
    }

}
