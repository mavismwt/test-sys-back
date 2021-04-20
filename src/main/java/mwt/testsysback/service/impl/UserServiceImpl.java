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
    public User getUserById(int user_id){
        System.out.println("业务层：用id查找用户");
        return UserMapper.getUserById(user_id);
    }

    @Override
    public List<User> getUsers(User user) {
        System.out.println("业务层：条件查询用户");
        return UserMapper.getUsers(user);
    }

    @Override
    public void insertUser(User user){
        System.out.println("业务层：注册用户");
        UserMapper.insertUser(user);
    }

    @Override
    public User login(User user) {
        System.out.println("业务层：用户登录");
        return UserMapper.login(user);
    }

    @Override
    public int updateUser(User user) {
        System.out.println("业务层：修改用户信息");
        return UserMapper.updateUser(user);
    }
}

