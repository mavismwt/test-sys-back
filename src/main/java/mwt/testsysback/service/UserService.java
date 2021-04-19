package mwt.testsysback.service;
import mwt.testsysback.entity.User;

import java.util.List;

public interface UserService {
    //查询所有用户
    List<User> getAllUser();

    //查找用户
    public User getUserById(int user_id);

    //用户注册
    public void insertUser(User user);

    //用户登录
    public User login(User user);
}
