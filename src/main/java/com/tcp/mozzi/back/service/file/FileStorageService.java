package com.tcp.mozzi.back.service.file;

import com.tcp.mozzi.back.config.FileStorageConfig;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    public String storeFile(MultipartFile file);
    public Resource loadFileAsResource(String fileName);
}
