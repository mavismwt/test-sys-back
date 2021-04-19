package mwt.testsysback.mapper;

import mwt.testsysback.entity.Group;
import org.apache.ibatis.annotations.*;

public interface GroupMapper {
    //查找全部班级
    @Select("select * from group")
    public Group getAllGroup(Group group);

    //查找班级
    @Select("select * from group where group_id=#{group_id}")
    public Group getGroupById(Group group);

    //新建班级
    @Insert("insert into group (group_name,teachers,students) values(#{group_name},#{teachers},#{students})")
    public int insertGroup(Group group);

    //删除班级
    @Delete("delete from assign where group_id=#{group_id}")
    public int deleteGroup(Group group);

    //更新班级信息
    @Update("update group set group_name=#{group_name} teachers=#{teacher} students=#{students} " +
            "where group_id=#{group_id}")
    public int updateGroup(Group group);
}
