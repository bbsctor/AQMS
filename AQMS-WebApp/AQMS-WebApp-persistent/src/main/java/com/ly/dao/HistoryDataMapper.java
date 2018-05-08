package com.ly.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ly.entity.HistoryData;

public interface HistoryDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HistoryData record);

    int insertSelective(HistoryData record);

    HistoryData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HistoryData record);

    int updateByPrimaryKey(HistoryData record);
    
    List<HistoryData> getHistoryData(); //得到历史表所有数据
    
    List<HistoryData> selectInfoByMN(String MN); //按照监测MN号获取对应数据
    
    List<HistoryData> selectInfoBySiteName(String SiteName);//按照监测站名获取对应数据
     
    List<String> selectAllMN();  //获取所有设备名
    
    List<Double> selectAllparam();  //获取所有参数名
    
    List< HistoryData> queryByTime(Map<String, String> time);//时间段查询
    
    List< HistoryData> queryByTimeAndMN(Map<String, String> time);//某一个站点时间段查询
    
}