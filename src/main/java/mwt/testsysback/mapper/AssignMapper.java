package mwt.testsysback.mapper;

import mwt.testsysback.entity.Assign;
import org.apache.ibatis.annotations.*;

public interface AssignMapper {
    //查询作业详情
    @Select("select * from assign where assign_id=#{assign_id}")
    public Assign getAssign(Assign assign);

    //新建作业
    @Insert("insert into assign (title,detail,weight,text_example,date_end) " +
            "values(#{title},#{detail},#{weight}),#{text_example},date_end)")
    public void insertAssign(Assign assign);

    //删除作业
    @Delete("delete from assign where assign_id=#{assign_id}")
    public boolean deleteAssign(Assign assign);

    //修改作业-教师
    @Update("update assign set title=#{title} detail=#{detail} weight=#{weight} score=#{score}" +
            "date_end=#{date_end} text_example=#{text_example} where assign_id=#{assign_id}")
    public Assign updateAssign(Assign assign);

    //提交作业-学生
    @Update("update assign set file_source=#{file_source} file_report=#{file_report} where assign_id=#{assign_id}")
    public Assign uploadAssign(Assign assign);

}
