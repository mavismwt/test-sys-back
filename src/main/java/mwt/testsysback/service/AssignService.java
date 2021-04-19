package mwt.testsysback.service;

import mwt.testsysback.entity.Assign;

public interface AssignService {
    //查询作业详情
    public Assign getAssign(Assign assign);

    //新建作业
    public boolean insertAssign(Assign assign);

    //删除作业
    public boolean deleteAssign(Assign assign);

    //修改作业-教师
    public Assign updateAssign(Assign assign);

    //提交作业-学生
    public Assign uploadAssign(Assign assign);
}
