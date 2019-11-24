package com.tcp.mozzi.back.service.log;

import com.tcp.mozzi.back.domain.log.Log;
import com.tcp.mozzi.back.mapper.log.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private LogMapper logMapper;

    @Autowired
    public void setLogMapper(LogMapper logMapper){this.logMapper = logMapper;}

    @Override
    public void createLog(Log log) {
        logMapper.insertLog(log);
    }

    @Override
    public List<Log> readLog() {
        return logMapper.selectLog();
    }

    @Override
    public List<Log> readLog(int userId) {
        return logMapper.selectLogByUserId(userId);
    }
}
