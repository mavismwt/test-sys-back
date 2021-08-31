package mwt.testsysback.service.impl;

import mwt.testsysback.entity.Collection;
import mwt.testsysback.mapper.CollectionMapper;
import mwt.testsysback.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    CollectionMapper collectionMapper;

    @Override
    public Collection insertCollection(Collection collection) {
        if (collectionMapper.insertCollection(collection) >= 1) {
            return collectionMapper.getCollectionById(collection);
        } else {
            return null;
        }
    }

    @Override
    public List<Collection> getAllCollection() {
        return collectionMapper.getAllCollection();
    }

    @Override
    public boolean deleteCollection(Collection collection) {
        if (collectionMapper.deleteCollection(collection) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection updateCollection(Collection collection) {
        if (collectionMapper.updateCollection(collection) >= 1) {
            return collectionMapper.getCollectionById(collection);
        } else {
            return null;
        }
    }
}
