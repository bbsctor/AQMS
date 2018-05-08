package com.ly.dao;

import java.util.List;
import java.util.Map;

import com.ly.entity.RealData;

public interface RealDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RealData record);

    int insertSelective(RealData record);  

    int updateByPrimaryKeySelective(RealData record);

    int updateByPrimaryKey(RealData record);	
	
	    RealData selectByPrimaryKey(Integer id);

	    List<RealData> getRealData(); //得到实时表所有数据
	    
	    List<RealData> selectInfoByMN(String MN); //按照设备号获取对应数据
	    
	    List<String> selectAllMN();  //获取所有设备号
	    
	    List<RealData> selectByTime(Map<String, String> map);//时间段查询
	    
	    List<Map<String, Object>> selectAllData();//导出操作
	    
	    List< RealData> queryByTimeAndMN(Map<String, String> time);//某一个站点时间段查询
}