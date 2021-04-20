package mwt.testsysback.mapper;

import mwt.testsysback.entity.Assign;
import mwt.testsysback.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AssignMapper {
    //查询作业详情
    @Select("select * from assign where assign_id=#{assign_id}")
    public Assign getAssignByID(int assign_id);

    //条件查询(标题)
    @Select("select * from assign where title like CONCAT('%',#{title,jdbcType=VARCHAR},'%')")
    public List<Assign> getAssignsByTitle(Assign assign);

    //条件查询(学生)
    @Select("select * from assign where students like CONCAT('%',#{username,jdbcType=VARCHAR},'%')")
    public List<Assign> getAssignStudent(String username);

    //条件查询(教师)
    @Select("select * from assign where teachers like CONCAT('%',#{username,jdbcType=VARCHAR},'%')")
    public List<Assign> getAssignTeacher(String username);

    //新建作业-教师
    @Insert("insert into assign (title,detail,weight,teachers,text_example,date_start,date_end) " +
            "values(#{title},#{detail},#{weight},#{teachers},#{text_example},#{date_end},#{date_end})")
    public int insertAssign(Assign assign);

    //删除作业-教师
    @Delete("delete from assign where assign_id=#{assign_id}")
    public int deleteAssign(int assign_id);

    //修改作业-教师
    @Update("update assign set title=#{title} detail=#{detail} weight=#{weight} score=#{score}" +
            "date_end=#{date_end} text_example=#{text_example} where assign_id=#{assign_id}")
    public int updateAssign(Assign assign);

    //提交作业-学生
    @Update("update assign set file_source=#{file_source} file_report=#{file_report} where assign_id=#{assign_id}")
    public int uploadAssign(Assign assign);

}
