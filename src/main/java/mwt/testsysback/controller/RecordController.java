package mwt.testsysback.controller;

import mwt.testsysback.common.CommonResult;
import mwt.testsysback.entity.Records;
import mwt.testsysback.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RecordController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    RecordService recordService;

    //根据作业
    @RequestMapping(value = "/record/assign",method = RequestMethod.GET)
    public CommonResult getRecordsByAssign(@RequestParam Records record) {
        List<Records> records = recordService.getRecords(record);
        if (records != null) {
            return CommonResult.success(records);
        } else {
            return CommonResult.failed();
        }
    }

    //根据用户
    @RequestMapping(value = "/record/user",method = RequestMethod.GET)
    public CommonResult getRecordsByUser(@RequestParam Records record) {
        List<Records> records = recordService.getMyRecord(record);
        if (records != null) {
            return CommonResult.success(records);
        } else {
            return CommonResult.failed();
        }
    }

    //获取单条
    @RequestMapping(value = "/record/single",method = RequestMethod.GET)
    public CommonResult getOneRecords(@RequestParam("assign_id") int assign_id,@RequestParam("username") String username) {
        Records record = new Records();
        record.setAssign_id(assign_id);
        record.setUsername(username);
        Records records = recordService.getOneRecord(record);
        if (records != null) {
            return CommonResult.success(records);
        } else {
            return CommonResult.failed();
        }
    }


    ///  更新报告
    @RequestMapping(value = "/record/report")
    public CommonResult updateReport(@RequestBody Records record) {
        if (recordService.getOneRecord(record) != null) {
            if (recordService.updateReport(record)) {
                return CommonResult.success("更新提交记录成功");
            }
            return CommonResult.failed();
        } else {
            if (recordService.insertRecord(record)) {
                if (recordService.updateReport(record)) {
                    return CommonResult.success("更新提交记录成功");
                }
                return CommonResult.failed();
            }
            return CommonResult.failed();
        }
    }



}
