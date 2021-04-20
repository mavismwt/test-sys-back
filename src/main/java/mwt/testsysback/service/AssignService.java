package mwt.testsysback.service;

import mwt.testsysback.entity.Assign;
import mwt.testsysback.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AssignService {
    //查询作业详情
    public Assign getAssign(int assign_id);

    //条件查询(标题)
    public List<Assign> getAssignByTitle(Assign assign);

    //条件查询(学生)
    public List<Assign> getAssignStudent(String username);

    //条件查询(教师)
    public List<Assign> getAssignTeacher(String username);

    //新建作业
    public boolean insertAssign(Assign assign);

    //删除作业
    public boolean deleteAssign(int assign_id);

    //修改作业-教师
    public Assign updateAssign(Assign assign);

    //提交作业-学生
    public Assign uploadAssign(Assign assign);
}
