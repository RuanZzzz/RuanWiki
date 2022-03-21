package com.richard.wiki.mapper;

import com.richard.wiki.domain.MemberToken;
import com.richard.wiki.examples.MemberTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberTokenMapper {
    long countByExample(MemberTokenExample example);

    int deleteByExample(MemberTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberToken record);

    int insertSelective(MemberToken record);

    List<MemberToken> selectByExample(MemberTokenExample example);

    MemberToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberToken record, @Param("example") MemberTokenExample example);

    int updateByExample(@Param("record") MemberToken record, @Param("example") MemberTokenExample example);

    int updateByPrimaryKeySelective(MemberToken record);

    int updateByPrimaryKey(MemberToken record);
}