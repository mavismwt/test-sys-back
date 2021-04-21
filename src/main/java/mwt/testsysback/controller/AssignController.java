package mwt.testsysback.controller;

import mwt.testsysback.entity.Assign;
import mwt.testsysback.service.AssignService;
import mwt.testsysback.common.CommonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AssignController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AssignService assignService;

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
    public CommonResult dispatchAssign(@RequestBody Assign assign) {
        if (assignService.dispatchAssign(assign) >= 1) {
            return CommonResult.success("true");
        } else {
            return CommonResult.failed();
        }
    }

    //助教
    @RequestMapping(value = "/assign/update/teacher",method = RequestMethod.POST)
    public CommonResult assistAssign(@RequestBody Assign assign) {
        if (assignService.assistAssign(assign) >= 1) {
            return CommonResult.success("true");
        } else {
            return CommonResult.failed();
        }
    }

//    //删除作业
//    @RequestMapping(value = "/assign/delete",method = RequestMethod.POST)
//    public CommonResult deleteAssign(@RequestBody Integer assign_id) {
//        if (assignService.deleteAssign(assign_id)) {
//            return CommonResult.success("success");
//        } else {
//            return CommonResult.failed();
//        }
//    }

    //删除作业
    @RequestMapping(value = "/assign/delete",method = RequestMethod.POST)
    public CommonResult deleteAssigns(@RequestParam("assign_id") String assign_id ) {
        return CommonResult.success(assignService.deleteAssigns(assign_id));
    }

    //上传作业
    @RequestMapping(value = "/assign/upload",method = RequestMethod.POST)
    public CommonResult uploadAssign(@RequestBody Assign assign) {
        if (assignService.insertAssign(assign)) {
            return CommonResult.success("success");
        } else {
            return CommonResult.failed();
        }
    }

}
