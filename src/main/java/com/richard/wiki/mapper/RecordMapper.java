package com.richard.wiki.mapper;

import com.richard.wiki.domain.Record;
import com.richard.wiki.examples.RecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecordMapper {
    long countByExample(RecordExample example);

    int deleteByExample(RecordExample example);

    int insert(Record record);

    int insertSelective(Record record);

    List<Record> selectByExample(RecordExample example);

    int updateByExampleSelective(@Param("record") Record record, @Param("example") RecordExample example);

    int updateByExample(@Param("record") Record record, @Param("example") RecordExample example);
}