package mwt.testsysback.controller;

import mwt.testsysback.entity.Assign;
import mwt.testsysback.service.AssignService;
import mwt.testsysback.common.CommonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AssignService assignService;

    //查询作业详情
    @RequestMapping(value = "/getAssign",method = RequestMethod.GET)
    public CommonResult getAssign(@RequestParam("assign_id") Integer assign_id) {
        if (assignService.getAssign(assign_id) != null) {
            return CommonResult.success(assignService.getAssign(assign_id));
        } else {
            return CommonResult.failed();
        }
    }

    //修改作业

}
