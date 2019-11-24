package com.tcp.mozzi.back.service.log;

import com.tcp.mozzi.back.domain.log.Log;

import java.util.List;

public interface LogService {
    void createLog(Log log);
    List<Log> readLog();
    List<Log> readLog(int userId);
}
