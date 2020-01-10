package com.tcp.mozzi.back.dto.exam;

import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeleteExamRequestDto extends DefaultResponseDto {
    private int examId;
    private int authorId;
}
