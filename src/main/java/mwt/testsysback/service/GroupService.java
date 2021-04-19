package mwt.testsysback.service;

import mwt.testsysback.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface GroupService {
    //新建班级
    public Group insertGroup(Group group);

    //删除班级
    public boolean deleteGroup(Group group);

    //更新班级信息
    public Group updateGroup(Group group);
}
