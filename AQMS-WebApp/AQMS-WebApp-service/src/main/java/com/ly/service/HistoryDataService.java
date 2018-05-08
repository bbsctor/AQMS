package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.HistoryData;

public interface HistoryDataService {
	
	// List<HistoryData> queryBySiteName(String siteName);  	
	 
	 List<HistoryData> selectInfo();
	 
//	 List<HistoryData> selectBySiteName(String siteName);
	 
	 List<HistoryData> selectByMN(String MN);
	 
	// List<String> selectAllSiteName();
	 
	 List<String> selectAllMN();
	 
	 List<HistoryData> selectByTime(Map<String, String> map);
	 
	 List<HistoryData> selectByTimeAndMN(Map<String, String> map);
	 
	
	 

}
