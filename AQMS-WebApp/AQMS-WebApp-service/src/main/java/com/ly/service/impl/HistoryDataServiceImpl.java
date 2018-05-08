package com.ly.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ly.dao.HistoryDataMapper;
import com.ly.entity.HistoryData;
import com.ly.service.HistoryDataService;


@Service("HistoryDataService")
public class HistoryDataServiceImpl implements HistoryDataService{
	 @Resource
	   private HistoryDataMapper HistoryDatamapper;
	   private List<HistoryData> HistoryData;
	

	public List<HistoryData> selectInfo() {
		// TODO Auto-generated method stub
				HistoryData= HistoryDatamapper.getHistoryData();						
				if(HistoryData!=null)
				{
					return HistoryData;
				}					
				return null;
	}

	public List<HistoryData> selectByTime(Map<String, String> time) {
		HistoryData=HistoryDatamapper.queryByTime(time);
		if(HistoryData!=null)
			return HistoryData;
		// TODO Auto-generated method stub
		return null;
	}

	public List<HistoryData> selectByMN(String MN) {
		if(MN.equals("1"))
			MN="88888880000001";
		else if (MN.equals("2")) {
			MN="88888880000002";
		}
		else if (MN.equals("3")) {
			MN="88888880000003";
		}
		else if (MN.equals("4")) {
			MN="88888880000003";
		}
		HistoryData=HistoryDatamapper.selectInfoByMN(MN);
		if(HistoryData!=null)
			return HistoryData;
		return null;
	}

	public List<String> selectAllMN() {
		List<String> allMN=HistoryDatamapper.selectAllMN();
		
		if(allMN!=null)
		{
			for (int i = 0; i < allMN.size(); i++) {
				if(allMN.get(i).equals("88888880000001"))
					allMN.set(i, "1");
				else if(allMN.get(i).equals("88888880000002"))
					allMN.set(i, "2");
				else if(allMN.get(i).equals("88888880000003"))
					allMN.set(i, "3");	
				else if(allMN.get(i).equals("88888880000004"))
					allMN.set(i, "4");	
			    }
			
			return allMN;
		}
		return null;
	}

	public List<HistoryData> selectByTimeAndMN(	Map<String, String> map) {
		if(map.get("mn").equals("1"))
			map.put("mn", "88888880000001");
		else if(map.get("mn").equals("2"))
			map.put("mn", "88888880000002");
		else if(map.get("mn").equals("3"))
			map.put("mn", "88888880000003");
		else if(map.get("mn").equals("4"))
			map.put("mn", "88888880000004");
		HistoryData=HistoryDatamapper.queryByTimeAndMN(map);
		if(HistoryData!=null)
			return HistoryData;
		// TODO Auto-generated method stub
		return null;
	}

}

//
//@Override
//public List<HistoryData> selectBySiteName(String siteName) {
//	// TODO Auto-generated method stub
//	HistoryData=HistoryDatamapper.selectInfoBySiteName(siteName);
//	if(HistoryData!=null)
//		return HistoryData;
//	return null;
//}
