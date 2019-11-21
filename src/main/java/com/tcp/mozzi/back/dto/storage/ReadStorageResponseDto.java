package com.tcp.mozzi.back.dto.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.domain.storage.Storage;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadStorageResponseDto extends DefaultResponseDto {

    @JsonProperty("storages")
    private List<Storage> storages;
}
