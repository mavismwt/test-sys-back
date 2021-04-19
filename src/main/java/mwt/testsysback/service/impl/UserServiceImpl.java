package mwt.testsysback.service.impl;

import mwt.testsysback.mapper.UserMapper;
import mwt.testsysback.entity.User;
import mwt.testsysback.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper UserMapper;

    @Override
    public List<User> getAllUser() {
        System.out.println("业务层：查询用户");
        return UserMapper.getAllUser();
    }

    @Override
    public User getUserById(User user){
        System.out.println("业务层：用id查找用户");
        return UserMapper.getUserById(user);
    }

    @Override
    public void insertUser(User user){
        System.out.println("业务层：注册用户");
        UserMapper.insertUser(user);
    }

    @Override
    public boolean login(User user) {
        System.out.println("业务层：用户登录");
        if(UserMapper.login(user) == null){
            System.out.println("false");
            return false;
        }else{
            System.out.println("true");
            return true;
        }
    }
}

