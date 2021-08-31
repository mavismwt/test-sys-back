package mwt.testsysback.mapper;

import mwt.testsysback.entity.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {
    //查找全部班级
    @Select("select * from collection")
    public List<Collection> getAllCollection();

    //查找班级
    @Select("select * from collection where collection_id=#{collection_id}")
    public Collection getCollectionById(Collection collection);

    //新建班级
    @Insert("insert into collection (collection_name,teachers,students) values(#{collection_name},#{teachers},#{students})")
    public int insertCollection(Collection collection);

    //删除班级
    @Delete("delete from assign where collection_id=#{collection_id}")
    public int deleteCollection(Collection collection);

    //更新班级信息
    @Update("update collection set collection_name=#{collection_name} teachers=#{teacher} students=#{students} " +
            "where collection_id=#{collection_id}")
    public int updateCollection(Collection collection);
}
