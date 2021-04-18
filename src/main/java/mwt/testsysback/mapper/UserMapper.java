package mwt.testsysback.mapper;
import mwt.testsysback.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    //查询所有用户
    @Select("select * from user")
    List<User> getAllUser();

    //用户注册
    @Insert("INSERT INTO USER (username,password,) VALUES(#{username},#{password})")
    public void insertUser(User user);

    //用户登录
    @Select("select * from user where username=#{username} and password=#{password}")
    public User login(User user);

}
