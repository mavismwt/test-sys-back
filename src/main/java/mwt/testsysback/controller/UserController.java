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

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    @ResponseBody
    /*
     * List 里的对象是Map对象，而Map对象是
     * 由一个String类型的键和Object类型的值组成
     * */
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @RequestMapping(value = "/login")
    public CommonResult login2(@RequestBody User user) {
        if (userService.login(user))
            return CommonResult.success(user);
        else
            return CommonResult.validateFailed();
    }

}
