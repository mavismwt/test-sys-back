package mwt.testsysback.mapper;
import mwt.testsysback.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询所有用户
    @Select("select * from user")
    List<User> getAllUser();

    //获取指定用户
    @Select("select * from user where user_id=#{user_id}")
    public User getUserById(int user_id);

    //条件查询(人名/班级/身份)
    @Select("select * from user where nickname like concat('%',#{nickname,jdbcType=VARCHAR},'%') " +
            "and collection like CONCAT('%',#{collection,jdbcType=VARCHAR},'%') and identity=#{identity}")
    List<User> getUsers(String nickname, String collection, String identity);

    //用户注册
    @Insert("insert into user (username,nickname,password,identity) values(#{username},#{nickname},#{password}),#{identity})")
    public int insertUser(User user);

    //用户登录
    @Select("select * from user where username=#{username} and password=#{password}")
    public User login(User user);

    //更改用户信息
    @Update("update assign set password=#{password},username=#{username},nickname=#{nickname}," +
            "collection=#{collection},assign=#{assign} where user_id=#{user_id}")
    public int updateUser(User user);

}
