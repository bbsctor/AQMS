package com.ly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ly.dao.StationMnMapper;
import com.ly.entity.StationMn;
import com.ly.service.StationMNService;

@Service("StationMnService")
public class StationMnServiceImpl implements StationMNService {
   @Resource
    private StationMnMapper MnMapper;
	public List<String> selectALLMNName() {
		// TODO Auto-generated method stub
		List<String> allMNName=MnMapper.selectALLMNName();
		if(allMNName!=null)
			return allMNName;
		return null;
	}

}
