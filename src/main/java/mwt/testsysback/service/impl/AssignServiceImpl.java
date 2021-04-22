package mwt.testsysback.service.impl;

import mwt.testsysback.mapper.AssignMapper;
import mwt.testsysback.entity.Assign;
import mwt.testsysback.service.AssignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssignServiceImpl implements AssignService {
    @Resource
    AssignMapper assignMapper;

    @Override
    public Assign getAssign(int assign_id) {
        System.out.println("业务层：用id查找作业");
        return assignMapper.getAssignByID(assign_id);
    }

    @Override
    public List<Assign> getAssignByTitle(Assign assign) {
        System.out.println("业务层：用title查找作业");
        return assignMapper.getAssignsByTitle(assign);
    }

    @Override
    public List<Assign> getAssignStudent(String username,String title) {
        System.out.println("业务层：用username查找作业");
        return assignMapper.getAssignStudent(username,title);
    }

    @Override
    public List<Assign> getAssignTeacher(String username,String title) {
        System.out.println("业务层：用username查找作业");
        return assignMapper.getAssignTeacher(username,title);
    }

    @Override
    public boolean insertAssign(Assign assign) {
        if (assignMapper.insertAssign(assign) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAssign(int assign_id) {
        if (assignMapper.deleteAssign(assign_id) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAssigns(String assign_id) {
        if (assignMapper.deleteAssigns(assign_id) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Assign updateAssign(Assign assign) {
        System.out.println("业务层：修改作业");
        if (assignMapper.updateAssign(assign) >= 1) {
            return assignMapper.getAssignByID(assign.getAssign_id());
        } else {
            return null;
        }
    }

    @Override
    public int dispatchAssign(int assign_id, String students) {
        return assignMapper.dispatchAssign(assign_id,students);
    }

    @Override
    public int assistAssign(int assign_id, String teachers) {
        return assignMapper.assistAssign(assign_id, teachers);
    }
}
