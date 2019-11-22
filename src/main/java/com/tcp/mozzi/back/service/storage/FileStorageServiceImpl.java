package com.tcp.mozzi.back.service.storage;

import com.tcp.mozzi.back.config.FileStorageConfig;
import com.tcp.mozzi.back.domain.storage.Storage;
import com.tcp.mozzi.back.exception.FileNotFoundException;
import com.tcp.mozzi.back.exception.FileStorageException;
import com.tcp.mozzi.back.mapper.storage.StorageMapper;
import com.tcp.mozzi.back.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private Path fileStorageLocation;

    private LocalDateTime time;

    private StorageMapper storageMapper;

    @Autowired
    public void setStorageMapper(StorageMapper storageMapper) {this.storageMapper = storageMapper;}

    @Autowired
    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig){
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored. " + e);
        }
    }

    @Override
    public void createStorage(String dirName, int location, int userId) {
        storageMapper.insertStorage(new Storage(false, dirName, location, userId));
    }

    @Override
    public List<Storage> readStorage(int location, int userId) {
        return storageMapper.selectStorageByLocationAndUserId(location, userId);
    }

    @Override
    public String storeFile(MultipartFile file, String userName, int curDirId, int authorId){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Filename contains invalid path sequence : " + fileName);
            }
            time = LocalDateTime.now();
            String hashedFileName = Util.sha256(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")).concat(userName).concat(fileName));

            Path targetLocation = this.fileStorageLocation.resolve(hashedFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            storageMapper.insertStorage(new Storage(file, hashedFileName, curDirId, authorId));

            return fileName;

        }catch(IOException e){
            throw new FileStorageException("Could not store storage " + fileName + ". Please try again. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new FileStorageException("Could not store storage " + fileName + ". Please try again. ", e);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName, int location){
        try{
            String hashName = storageMapper.selectStorageByFileNameAndLocation(fileName, location).getHashName();
            System.out.println(hashName);
            Path filePath = this.fileStorageLocation.resolve(hashName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else
                throw new FileNotFoundException("File not found " + fileName);
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not Found " + fileName, e);
        }
    }
}
