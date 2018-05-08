package com.ly.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.dao.RealDataMapper;
import com.ly.entity.RealData;
import com.ly.service.RealDataService;
import com.ly.util.EUDataGridResult;


@Service("RealDataService")
public class RealDataServiceImpl implements RealDataService {
	   @Resource
	   private RealDataMapper RealDatamapper;
	   private List<RealData> RealData;
	public List<RealData> getAllRealData() {
		// TODO Auto-generated method stub
		RealData= RealDatamapper.getRealData();
		if(RealData!=null)
			return RealData;
		return null;
	}

	public List<String> selectAllMN() {
		// TODO Auto-generated method stub
		List<String> allMN=RealDatamapper.selectAllMN();
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
	public List<RealData> selectByTime(Map<String, String> map) {
		RealData=RealDatamapper.selectByTime(map);
		if(RealData!=null)
			return RealData;
		return null;
	}
	public List<Map<String, Object>> selectAllData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>>  alldata=RealDatamapper.selectAllData();
		if(alldata!=null)
			return alldata;
		return null;
	}
	
	public List<RealData> selectBySiteMN(String MN) {		
		if(MN.equals("1"))
			MN="88888880000001";
		else if (MN.equals("2")) {
			MN="88888880000002";
		}
		else if (MN.equals("3")) {
			MN="88888880000003";
		}
		else if (MN.equals("4")) {
			MN="88888880000004";
		}
		RealData=RealDatamapper.selectInfoByMN(MN);
		if(RealData!=null)
			return RealData;
		return null;	
	}

	
	public EUDataGridResult getList(int page, int rows,	RealData RealData) throws Exception {		
				//鍒嗛〉澶勭悊
				PageHelper.startPage(page, rows);
				List<RealData> list = RealDatamapper.getRealData();
				//鍒涘缓涓�涓繑鍥炲�煎璞�
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				//鍙栬褰曟�绘潯鏁�
				PageInfo<RealData> pageInfo = new PageInfo<RealData>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
	}

	public List<com.ly.entity.RealData> selectByTimeAndMN(Map<String, String> map) {
		if(map.get("mn").equals("1"))
			map.put("mn", "88888880000001");
		else if(map.get("mn").equals("2"))
			map.put("mn", "88888880000002");
		else if(map.get("mn").equals("3"))
			map.put("mn", "88888880000003");
		else if(map.get("mn").equals("4"))
			map.put("mn", "88888880000004");
		RealData=RealDatamapper.queryByTimeAndMN(map);
		if(RealData!=null)
			return RealData;
		// TODO Auto-generated method stub
		return null;
	}



}
