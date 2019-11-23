package com.tcp.mozzi.back.mapper.storage;

import com.tcp.mozzi.back.domain.storage.Storage;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StorageMapper {

    void insertStorage(Storage storage);
    void insertDirectory(Storage storage);
    Storage selectStorageByFileNameAndLocation(@Param("fileName") String fileName,@Param("location") int location);
    List<Storage> selectStorageByLocationAndUserId(@Param("location") int location,@Param("userId") int userId);
}
