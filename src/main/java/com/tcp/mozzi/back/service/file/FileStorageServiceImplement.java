package com.tcp.mozzi.back.service.file;

import com.tcp.mozzi.back.config.FileStorageConfig;
import com.tcp.mozzi.back.exception.FileNotFoundException;
import com.tcp.mozzi.back.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImplement implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImplement(FileStorageConfig fileStorageConfig){
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored. " + e);
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Filename contains invalid path sequence : " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        }catch(IOException e){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again.", e);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else
                throw new FileNotFoundException("File not found " + fileName);
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not Found" + fileName, e);
        }
    }
}