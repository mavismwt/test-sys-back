package mwt.testsysback.service.impl;

import mwt.testsysback.entity.Collection;
import mwt.testsysback.mapper.CollectionMapper;
import mwt.testsysback.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    CollectionMapper CollectionMapper;

    @Override
    public Collection insertCollection(Collection collection) {
        if (CollectionMapper.insertCollection(collection) >= 1) {
            return CollectionMapper.getCollectionById(collection);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCollection(Collection collection) {
        if (CollectionMapper.deleteCollection(collection) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection updateCollection(Collection collection) {
        if (CollectionMapper.updateCollection(collection) >= 1) {
            return CollectionMapper.getCollectionById(collection);
        } else {
            return null;
        }
    }
}
