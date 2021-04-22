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
    public CommonResult getOneRecords(@RequestParam Records record) {
        Records records = recordService.getOneRecord(record);
        if (records != null) {
            return CommonResult.success(records);
        } else {
            return CommonResult.failed();
        }
    }

    //提交记录
    @RequestMapping(value = "/record/update",method = RequestMethod.POST)
    public CommonResult updateRecords(@RequestBody Records record) {
        Records records = recordService.getOneRecord(record);
        if (records == null) {
            if (recordService.insertRecord(record)) {
                return CommonResult.success("添加成功");
            } else {
                return CommonResult.failed("添加失败");
            }
        } else {
            if (recordService.updateRecord(record)) {
                return CommonResult.success("更新成功");
            } else {
                return CommonResult.failed("更新失败");
            }
        }
    }


}
