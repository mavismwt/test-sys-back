package mwt.testsysback.controller;

import mwt.testsysback.entity.User;
import mwt.testsysback.common.CommonResult;
import mwt.testsysback.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public CommonResult getUser(@RequestParam("user_id") int user_id){
        if (userService.getUserById(user_id) != null) {
            return CommonResult.success(userService.getUserById(user_id));
        } else {
            return CommonResult.failed();
        }
    }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult login2(@RequestBody User user) {
        if (userService.login(user) != null) {
            return CommonResult.success(userService.login(user));
        } else {
            return CommonResult.validateFailed();
        }
    }

}
