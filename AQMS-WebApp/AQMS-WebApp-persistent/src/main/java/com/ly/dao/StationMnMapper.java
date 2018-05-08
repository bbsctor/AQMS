package com.ly.dao;

import java.util.List;
import com.ly.entity.StationMn;

public interface StationMnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StationMn record);

    int insertSelective(StationMn record);

    StationMn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StationMn record);

    int updateByPrimaryKey(StationMn record);
    
    List<String> selectALLMNName();
     
    
}