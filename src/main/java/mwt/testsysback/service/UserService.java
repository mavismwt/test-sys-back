package mwt.testsysback.service;
import mwt.testsysback.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    //查询所有用户
    List<User> getAllUser();

    //查找用户
    public User getUserById(int user_id);

    //条件查询
    public List<User> getUsers(String nickname, String collection, String identity);

    //用户注册
    public void insertUser(User user);

    //用户登录
    public User login(User user);

    //更改用户信息
    public int updateUser(User user);

    //导出所有用户信息
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String id);

    //导出所有用户信息
    public void exportExcelAll(HttpServletRequest request, HttpServletResponse response,User user);
}
