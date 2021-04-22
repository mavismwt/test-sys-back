package mwt.testsysback.mapper;

import mwt.testsysback.entity.Records;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {
    //获取所有记录
    @Select("select * from record")
    List<Records> getAllRecords();

    //获取作业下所有记录
    @Select("select * from record where assign_id=#{assign_id}")
    List<Records> getRecords(Records records);

    //获取个人所有记录
    @Select("select * from record where username=#{username}")
    List<Records> getMyRecord(Records records);

    //获取指定记录
    @Select("select * from record where assign_id=#{assign_id} and username=#{username}")
    Records getOneRecord(Records records);

    //新增数据
    @Insert("insert into record (assign_id,title,weight,username,nickname,date) " +
            "values(#{assign_id},#{title},#{weight},#{username},#{nickname},#{date})")
    public int insertRecord(Records records);

    //更新评分
    @Update("update record set score=#{score},date=#{date} " +
            "where assign_id=#{assign_id} and username=#{username}")
    public int updateScore(int score,String date,int assign_id,String username);

    //上传报告文件
    @Update("update record set file_report=#{file_report},date=#{date} " +
            "where assign_id=#{assign_id} and username=#{username}")
    public int uploadReport(String file_report,String date,int assign_id,String username);

    //上传源码文件
    @Update("update record set file_source=#{file_source},date=#{date} " +
            "where assign_id=#{assign_id} and username=#{username}")
    public int uploadSource(String file_source,String date,int assign_id,String username);

    //删除数据
    @Delete("delete from record where record_id=#{record_id}")
    public int deleteRecord(String record_id);
}
