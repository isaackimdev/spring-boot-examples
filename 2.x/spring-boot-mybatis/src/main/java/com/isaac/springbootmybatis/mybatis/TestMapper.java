package com.isaac.springbootmybatis.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {
    Integer totSize(String findStr);
}
