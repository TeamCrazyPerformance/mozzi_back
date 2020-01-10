package com.tcp.mozzi.back.dto.storage;

import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Not Used
@AllArgsConstructor
@Getter
@Setter
public class UploadFileResponseDto extends DefaultResponseDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long fileSize;
}
