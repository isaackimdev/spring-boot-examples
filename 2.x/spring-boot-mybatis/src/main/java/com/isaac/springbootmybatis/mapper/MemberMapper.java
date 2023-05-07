package com.isaac.springbootmybatis.mapper;

import com.isaac.springbootmybatis.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    /**
     * select
     * */
    // count
    Integer getCountOfMember();
    // select All
    List<MemberDto> getMembers();



    // insert
    Integer registerMember(MemberDto memberDto);


}
