package mwt.testsysback.service;

import mwt.testsysback.entity.Collection;

public interface CollectionService {
    //新建班级
    public Collection insertCollection(Collection collection);

    //删除班级
    public boolean deleteCollection(Collection collection);

    //更新班级信息
    public Collection updateCollection(Collection collection);
}
