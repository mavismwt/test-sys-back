package mwt.testsysback.mapper;

import mwt.testsysback.entity.Group;
import org.apache.ibatis.annotations.*;

public interface GroupMapper {
    //新建班级
    @Insert("insert into group (group_name,teachers,students) values(#{group_name},#{teachers},#{students})")
    public void insertGroup(Group group);

    //删除班级
    @Delete("delete from assign where group_id=#{group_id}")
    public boolean deleteGroup(Group group);

    //更新班级信息
    @Update("update group set group_name=#{group_name} teachers=#{teacher} students=#{students} " +
            "where group_id=#{group_id}")
    public Group updateGroup(Group group);
}
