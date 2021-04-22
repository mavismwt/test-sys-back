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
    public boolean updateRecord(Records records) {
        if (recordMapper.updateRecord(records) >= 1) {
            return true;
        } else {
            return false;
        }
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
