package mwt.testsysback.service;

import mwt.testsysback.entity.Assign;
import mwt.testsysback.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AssignService {
    //查询作业详情
    public Assign getAssign(int assign_id);

    //条件查询(标题)
    public List<User> getAssigns(Assign assign);

    //新建作业
    public boolean insertAssign(Assign assign);

    //删除作业
    public boolean deleteAssign(int assign_id);

    //修改作业-教师
    public Assign updateAssign(Assign assign);

    //提交作业-学生
    public Assign uploadAssign(Assign assign);
}
