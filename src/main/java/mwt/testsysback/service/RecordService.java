package mwt.testsysback.service;

import mwt.testsysback.entity.Records;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RecordService {
    //获取所有记录
    List<Records> getAllRecords();

    //获取作业下所有记录
    List<Records> getRecords(Records records);

    //获取个人所有记录
    List<Records> getMyRecord(Records records);

    //获取指定记录
    Records getOneRecord(Records records);

    //新增数据
    public boolean insertRecord(Records records);

    //更新数据
    public boolean updateScore(Records records);

    //更新数据
    public boolean updateSource(Records records);

    //更新数据
    public boolean updateReport(Records records);

    //删除数据
    public boolean deleteRecord(String record_id);
}
