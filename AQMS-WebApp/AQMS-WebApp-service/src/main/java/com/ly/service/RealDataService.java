package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.RealData;
import com.ly.util.EUDataGridResult;


public interface  RealDataService {

	 List<RealData> getAllRealData(); //获取所有实时表数据
	 
	 List<RealData> selectBySiteMN(String mn); //根据MN获取对应实时数据
	 
	 List<String> selectAllMN(); // 获取所有的MN号
	 
	 List<RealData> selectByTime(Map<String, String> map);//时间段查询
	  
	  
	  List<Map<String, Object>> selectAllData(); //导出数据时，将所有数据放到map中
	  
	  List<RealData> selectByTimeAndMN(Map<String, String> map);
	  
	  EUDataGridResult getList(int page, int rows, RealData RealData) throws Exception;

}
