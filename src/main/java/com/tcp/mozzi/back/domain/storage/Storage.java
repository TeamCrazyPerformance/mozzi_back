package com.tcp.mozzi.back.domain.storage;

import lombok.*;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Integer storageId;
    private Integer authorId;
    private Integer location;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private String contentType;
    private Boolean isFile;
    private String name;
    private String hashName;

    public Storage(MultipartFile file, String hashName, int curDirId, int authorId){
        this.authorId = authorId;
        location = curDirId;
        createAt = LocalDateTime.now();
        contentType = file.getContentType();
        isFile = true;
        name = StringUtils.cleanPath(file.getOriginalFilename());
        this.hashName = hashName;
    }

    public Storage(Boolean isFile, String dirName, int curDirId, int authorId){
        this.authorId = authorId;
        this.location = curDirId;
        createAt = LocalDateTime.now();
        contentType = "directory";
        this.isFile = isFile;
        this.name = dirName;
        hashName = "directory : " + dirName;
    }
}
