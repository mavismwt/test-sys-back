package mwt.testsysback.controller;

import mwt.testsysback.entity.Assign;
import mwt.testsysback.service.AssignService;
import mwt.testsysback.entity.User;
import mwt.testsysback.service.UserService;
import mwt.testsysback.common.CommonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.*;

@RestController
public class AssignController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AssignService assignService;

    @Autowired
    UserService userService;

    //查询作业详情
    @RequestMapping(value = "/assign",method = RequestMethod.GET)
    public CommonResult getAssign(@RequestParam("assign_id") Integer assign_id) {
        if (assignService.getAssign(assign_id) != null) {
            return CommonResult.success(assignService.getAssign(assign_id));
        } else {
            return CommonResult.failed();
        }
    }

    //条件查询(标题)
    @RequestMapping(value = "/assign/title",method = RequestMethod.GET)
    public CommonResult getAssignsByTitle(@RequestParam Assign assign) {
        List<Assign> assigns = assignService.getAssignByTitle(assign);
        if (assigns != null) {
            return CommonResult.success(assigns);
        } else {
            return CommonResult.failed();
        }
    }

    //条件查询(学生)
    @RequestMapping(value = "/assign/student",method = RequestMethod.GET)
    public CommonResult getAssignsStudent(@RequestParam Map map) {
        List<Assign> assigns = assignService.getAssignStudent(map.get("username").toString(),map.get("title").toString());
        if (assigns != null) {
            return CommonResult.success(assigns);
        } else {
            return CommonResult.failed();
        }
    }

    //条件查询(教师)
    @RequestMapping(value = "/assign/teacher",method = RequestMethod.GET)
    public CommonResult getAssignTeacher(@RequestParam Map map) {
        List<Assign> assigns = assignService.getAssignTeacher(map.get("username").toString(),map.get("title").toString());
        if (assigns != null) {
            return CommonResult.success(assigns);
        } else {
            return CommonResult.failed();
        }
    }

    //新建作业
    @RequestMapping(value = "/assign/add",method = RequestMethod.POST)
    public CommonResult insertAssign(@RequestBody Assign assign) {
        if (assignService.insertAssign(assign)) {
            return CommonResult.success("success");
        } else {
            return CommonResult.failed();
        }
    }

    //修改作业
    @RequestMapping(value = "/assign/update",method = RequestMethod.POST)
    public CommonResult updateAssign(@RequestBody Assign assign) {
        Assign assign1 = assignService.updateAssign(assign);
        if (assign1 != null) {
            return CommonResult.success(assign1);
        } else {
            return CommonResult.failed();
        }
    }
    //分发作业
    @RequestMapping(value = "/assign/update/student",method = RequestMethod.POST)
//    public Object dispatchAssign(@RequestBody Assign assign) {
    public CommonResult dispatchAssign(@RequestBody Assign assign) {
        int assign_id = assign.getAssign_id();

        String oriStr = assignService.getAssign(assign_id).getStudents();
        String studentStr = assign.getStudents();
        if (oriStr != "" && oriStr != null) {
             studentStr = studentStr+ "," + oriStr;
        }
        String[] students = studentStr.split(",");
        String newStr = getSingle(students);

        if (assignService.dispatchAssign(assign_id,newStr) >= 1) {
            return CommonResult.success("true");
        } else {
            return CommonResult.failed();
        }
    }

    public static String getSingle(String[] strings) {
        List<String> list0 = Arrays.asList(strings);
        ArrayList<String> list = new ArrayList<>(list0);
        ArrayList tempList = new ArrayList();          //1,创建新集合
        Iterator it = list.iterator();              //2,根据传入的集合(老集合)获取迭代器
        while(it.hasNext()) {                  //3,遍历老集合
            Object obj = it.next();                //记录住每一个元素
            if(!tempList.contains(obj)) {            //如果新集合中不包含老集合中的元素
                tempList.add(obj);                //将该元素添加
            }
        }
        String string =  Arrays.toString(tempList.toArray()).replaceAll("\\[|\\]| ", "");
        return string;
    }

    //助教
    @RequestMapping(value = "/assign/update/teacher",method = RequestMethod.POST)
    public CommonResult assistAssign(@RequestBody Assign assign) {
        int assign_id = assign.getAssign_id();

        String oriTea = assignService.getAssign(assign_id).getTeachers();
        String teacherStr = oriTea;
        String addTea = assign.getTeachers();
        if ( addTea != "" && addTea != null) {
            teacherStr = teacherStr + "," + addTea;
        }

        String[] teachers = teacherStr.split(",");
        String newStr = getSingle(teachers);

        if (assignService.assistAssign(assign_id, newStr) >= 1) {
            return CommonResult.success("true");
        } else {
            return CommonResult.failed();
        }
    }

    //删除作业
    @RequestMapping(value = "/assign/delete",method = RequestMethod.POST)
    public CommonResult deleteAssigns(@RequestParam("assign_id") String assign_id ) {
        return CommonResult.success(assignService.deleteAssigns(assign_id));
    }

}
