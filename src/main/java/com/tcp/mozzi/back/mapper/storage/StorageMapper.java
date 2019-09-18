package com.tcp.mozzi.back.mapper.storage;

import com.tcp.mozzi.back.domain.storage.Storage;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StorageMapper {

    void insertStorage(Storage storage);
    void insertDirectory(Storage storage);
    Storage selectStorageByFileNameAndLocation(String fileName, int location);
    List<Storage> selectStorageByLocationAndUserId(int location, int userId);
}
