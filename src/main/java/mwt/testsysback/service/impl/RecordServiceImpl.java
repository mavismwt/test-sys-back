package mwt.testsysback.service.impl;

import mwt.testsysback.entity.Records;
import mwt.testsysback.mapper.RecordMapper;
import mwt.testsysback.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    RecordMapper recordMapper;

    @Override
    public List<Records> getAllRecords() {
        return recordMapper.getAllRecords();
    }

    @Override
    public List<Records> getRecords(Records records) {
        return recordMapper.getRecords(records);
    }

    @Override
    public List<Records> getMyRecord(Records records) {
        return recordMapper.getMyRecord(records);
    }

    @Override
    public Records getOneRecord(Records records) {
        return recordMapper.getOneRecord(records);
    }

    @Override
    public boolean insertRecord(Records records) {
        if (recordMapper.insertRecord(records) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateScore(Records records) {

        int score= records.getScore();
        int assign_id = records.getAssign_id();
        String date = records.getDate();
        String username = records.getUsername();
        int res = recordMapper.updateScore(score,date,assign_id,username);
        return (res >= 1);

    }

    @Override
    public boolean updateReport(Records records) {
        String file_report = records.getFile_report();
        int assign_id = records.getAssign_id();
        String date = records.getDate();
        String username = records.getUsername();

        int res = recordMapper.uploadReport(file_report,date,assign_id,username);
        return (res >= 1);
    }

    @Override
    public boolean updateSource(Records records) {
        String file_source = records.getFile_source();
        int assign_id = records.getAssign_id();
        String info = records.getInfo();
        String date = records.getDate();
        String username = records.getUsername();

        int res = recordMapper.uploadSource(file_source,info,date,assign_id,username);
        return (res >= 1);
    }

    @Override
    public boolean deleteRecord(String record_id) {
        if (recordMapper.deleteRecord(record_id) >= 1) {
            return true;
        } else {
            return false;
        }
    }


}
