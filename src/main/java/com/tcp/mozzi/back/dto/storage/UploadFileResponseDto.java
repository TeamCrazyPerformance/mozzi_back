package com.tcp.mozzi.back.dto.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Not Used
@AllArgsConstructor
@Getter
@Setter
public class UploadFileResponseDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long fileSize;
}
