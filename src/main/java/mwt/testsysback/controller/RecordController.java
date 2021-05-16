package mwt.testsysback.controller;

import mwt.testsysback.common.CommonResult;
import mwt.testsysback.entity.Records;
import mwt.testsysback.service.JudgeService;
import mwt.testsysback.service.RecordService;
import mwt.testsysback.service.UserService;
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

    @Autowired
    UserService userService;

    @Autowired
    JudgeService judgeService;

    // 根据作业
    @RequestMapping(value = "/record/assign",method = RequestMethod.GET)
    public CommonResult getRecordsByAssign(@RequestParam("assign_id") int assign_id) {
        Records record = new Records();
        record.setAssign_id(assign_id);
        List<Records> records = recordService.getRecords(record);
        if (records != null) {
            return CommonResult.success(records);
        } else {
            return CommonResult.failed();
        }
    }

    // 根据用户
    @RequestMapping(value = "/record/user",method = RequestMethod.GET)
    public CommonResult getRecordsByUser(@RequestParam("username") String username) {
        Records record = new Records();
        record.setUsername(username);
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
        return CommonResult.success(records);
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

    ///  更新源码
    @RequestMapping(value = "/record/source")
    public CommonResult updateSource(@RequestBody Records record) {
        String info = judgeService.executeCode(record.getFile_source(),1,1);
        record.setInfo(info);
        if (recordService.getOneRecord(record) != null) {
            if (recordService.updateSource(record)) {
                return CommonResult.success("更新提交记录成功");
            }
            return CommonResult.failed();
        } else {
            if (recordService.insertRecord(record)) {
                if (recordService.updateSource(record)) {
                    return CommonResult.success("更新提交记录成功");
                }
                return CommonResult.failed();
            }
            return CommonResult.failed();
        }
    }

    ///  更新成绩
    @RequestMapping(value = "/record/score")
    public CommonResult updateScore(@RequestBody Records record) {
        if (recordService.updateScore(record)) {
            List<Records> records = recordService.getMyRecord(record);
            double sum = 0.0;
            for(int i=0; i<records.size(); i++) {
                Records single = records.get(i);
                int score = single.getScore();
                int weight = single.getWeight();
                sum += score * weight/100.0;
            }
            System.out.println(record.getUsername());
            userService.updateUserScore(sum,record.getUsername().toString());
            return CommonResult.success(recordService.getOneRecord(record));
        }
        return CommonResult.failed();
    }



}
