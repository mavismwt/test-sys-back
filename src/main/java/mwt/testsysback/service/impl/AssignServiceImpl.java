package mwt.testsysback.service.impl;

import mwt.testsysback.entity.User;
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
    public List<User> getAssigns(Assign assign) {
        System.out.println("业务层：用id查找作业");
        return assignMapper.getAssigns(assign);
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
    public Assign updateAssign(Assign assign) {
        if (assignMapper.updateAssign(assign) >= 1) {
            return assignMapper.getAssignByID(assign.getAssign_id());
        } else {
            return null;
        }
    }

    @Override
    public Assign uploadAssign(Assign assign) {
        if (assignMapper.uploadAssign(assign) >= 1) {
            return assignMapper.getAssignByID(assign.getAssign_id());
        } else {
            return null;
        }
    }
}
