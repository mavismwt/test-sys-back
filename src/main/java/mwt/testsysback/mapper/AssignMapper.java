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
    @Select("select * from assign where students like CONCAT('%',#{username,jdbcType=VARCHAR},'%') " +
            "and title like CONCAT('%',#{title,jdbcType=VARCHAR},'%')")
    public List<Assign> getAssignStudent(String username,String title);

    //条件查询(教师)
    @Select("select * from assign where teachers like CONCAT('%',#{username,jdbcType=VARCHAR},'%') " +
            "and title like CONCAT('%',#{title,jdbcType=VARCHAR},'%')")
    public List<Assign> getAssignTeacher(String username,String title);

    //新建作业-教师
    @Insert("insert into assign (title,detail,weight,teachers,test_example,date_start,date_end) " +
            "values(#{title},#{detail},#{weight},#{teachers},#{test_example},#{date_start},#{date_end})")
    public int insertAssign(Assign assign);

    //删除作业-教师
    @Delete("delete from assign where assign_id=#{assign_id}")
    public int deleteAssign(int assign_id);

    //批量删除作业-教师
    @Delete("delete from assign where assign_id in ${assign_id}")
    public int deleteAssigns(String assign_id);

    //修改作业-教师
    @Update("update assign set title=#{title},detail=#{detail},weight=#{weight},date_end=#{date_end},test_example=#{test_example} where assign_id=#{assign_id}")
    public int updateAssign(Assign assign);

    //分发作业
    @Update("update assign set students=#{students} where assign_id=#{assign_id}")
    public int dispatchAssign(int assign_id, String students);

    //添加助教
    @Update("update assign set teachers=#{teachers} where assign_id=#{assign_id}")
    public int assistAssign(int assign_id, String teachers);


}
