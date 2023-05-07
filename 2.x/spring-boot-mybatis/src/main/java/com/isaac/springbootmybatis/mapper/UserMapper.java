package com.isaac.springbootmybatis.mapper;

import com.isaac.springbootmybatis.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {

    /**
     * select
     * */
    // count
    Integer getCountOfUser();

    List<Map<String, Object>> getUsers();

    // insert
    Integer registerUser(UserDto userDto);


}
