package mwt.testsysback.service.impl;

import mwt.testsysback.mapper.AssignMapper;
import mwt.testsysback.entity.Assign;
import mwt.testsysback.service.AssignService;

import javax.annotation.Resource;

public class AssignServiceImpl implements AssignService {
    @Resource
    AssignMapper AssignMapper;


    @Override
    public Assign getAssign(Assign assign) {
        System.out.println("业务层：用id查找作业");
        return AssignMapper.getAssignByID(assign);
    }

    @Override
    public boolean insertAssign(Assign assign) {
        if (AssignMapper.insertAssign(assign) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAssign(Assign assign) {
        if (AssignMapper.deleteAssign(assign) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Assign updateAssign(Assign assign) {
        if (AssignMapper.updateAssign(assign) >= 1) {
            return AssignMapper.getAssignByID(assign);
        } else {
            return null;
        }
    }

    @Override
    public Assign uploadAssign(Assign assign) {
        if (AssignMapper.uploadAssign(assign) >= 1) {
            return AssignMapper.getAssignByID(assign);
        } else {
            return null;
        }
    }
}
