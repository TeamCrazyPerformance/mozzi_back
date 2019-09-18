package com.tcp.mozzi.back.service.storage;

import com.tcp.mozzi.back.domain.storage.Storage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    void createStorage(String dirName, int location, int userId);
    List<Storage> readStorage(int location, int userId);
    String storeFile(MultipartFile file, String userName, int curDirId, int userId);
    Resource loadFileAsResource(String fileName, int location);
}
