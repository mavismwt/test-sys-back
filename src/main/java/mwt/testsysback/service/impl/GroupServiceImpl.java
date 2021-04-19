package mwt.testsysback.service.impl;

import mwt.testsysback.entity.Group;
import mwt.testsysback.mapper.GroupMapper;
import mwt.testsysback.service.GroupService;

import javax.annotation.Resource;

public class GroupServiceImpl implements GroupService {
    @Resource
    GroupMapper GroupMapper;

    @Override
    public Group insertGroup(Group group) {
        if (GroupMapper.insertGroup(group) >= 1) {
            return GroupMapper.getGroupById(group);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteGroup(Group group) {
        if (GroupMapper.deleteGroup(group) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Group updateGroup(Group group) {
        if (GroupMapper.updateGroup(group) >= 1) {
            return GroupMapper.getGroupById(group);
        } else {
            return null;
        }
    }
}
