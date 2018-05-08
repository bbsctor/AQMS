package com.ly.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.entity.HistoryData;
import com.ly.service.HistoryDataService;


@Controller
@Scope(value="prototype")  //@Scope(“prototype”)表示将Action的范围声明为原型

@RequestMapping("/HistoryData")
public class HistoryDataController {
	private static Logger logger = Logger.getLogger(HistoryDataController.class);  
	
	@Resource
	private HistoryDataService hisorydataservice;
	private List<String> AllMN;

	 @RequestMapping(value="/AllHistoryData")
	 public String QueryDataBySiteName(@RequestParam(required=true,defaultValue="1") Integer page,
			 @RequestParam(required=false,defaultValue="20")String num,
				HttpServletRequest request, Model model) {
			 int i=Integer.parseInt(num, 10);
			 PageHelper.startPage(page, i);
	         List<HistoryData> HistoryData=hisorydataservice.selectInfo();
	         PageInfo<HistoryData> p=new PageInfo<HistoryData>(HistoryData);
	         model.addAttribute("page", p);
	         model.addAttribute("num", i);
	         model.addAttribute("HistoryData",HistoryData);
		     model.addAttribute("AllMN",AllMN);
	         return "data/history_data";  	
	    }

	 @RequestMapping(value="/QueryDataByMN")
	 public String QueryDataBySiteName(@RequestParam(required=true,defaultValue="1") Integer page,@RequestParam(required=true,defaultValue="1")String MN ,
				@RequestParam(required=false,defaultValue="20")String num,
				HttpServletRequest request, Model model) {
			 int i=Integer.parseInt(num, 10);
			 PageHelper.startPage(page, i);
	    	 List<HistoryData> HistoryData=hisorydataservice.selectByMN(MN);
	         PageInfo<HistoryData> p=new PageInfo<HistoryData>(HistoryData);
	         model.addAttribute("page", p);
	         model.addAttribute("num", i);
	         model.addAttribute("HistoryData",HistoryData);
	         AllMN=hisorydataservice.selectAllMN();
	         model.addAttribute("AllMN",AllMN);
	         return "data/history_monitor";  	
	    }
	 
	 @RequestMapping(value="/HistoryDataByTimeInit")
	 public String HistoryDataByTimeInit(@RequestParam(required=true,defaultValue="1") Integer page,
			 @RequestParam(required=false,defaultValue="20")String num,
				HttpServletRequest request, Model model) {
			  int i=Integer.parseInt(num, 10);
			  PageHelper.startPage(page, i);
	        List<HistoryData> HistoryData=hisorydataservice.selectInfo();      
	         logger.info(JSON.toJSONString(HistoryData));  
	        PageInfo<HistoryData> p=new PageInfo<HistoryData>(HistoryData);
	         model.addAttribute("page", p);
	         model.addAttribute("num", i);
	         model.addAttribute("HistoryData",HistoryData);
	         
	         return "data/history_time";  	
	    }
	 
	@RequestMapping(value = "/HistoryDataByTime")
	public String HistoryDataByTime(
			@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam("StartTime")String StartTime, @RequestParam("EndTime")String EndTime, 
			@RequestParam(required=false,defaultValue="20")String num,
			HttpServletRequest request, Model model) {
		  int i=Integer.parseInt(num, 10);
		  PageHelper.startPage(page, i);
		Map<String, String> map = new HashMap<String, String>();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("startTime", StartTime);
		map.put("endTime", EndTime);
		List<HistoryData> HistoryData = hisorydataservice.selectByTime(map);
		logger.info(JSON.toJSONString(HistoryData));
		PageInfo<HistoryData> p = new PageInfo<HistoryData>(HistoryData);
		model.addAttribute("page", p);
		model.addAttribute("num", i);
		model.addAttribute("HistoryData", HistoryData);
		model.addAttribute("StartTime",StartTime);
		model.addAttribute("EndTime",EndTime);
		return "data/history_time";
	}
	
	
	 @RequestMapping(value="/QueryByTimeAndMNInit")
	 public String QueryByTimeAndMNInit(@RequestParam(required=true,defaultValue="1") Integer page,
			 @RequestParam(required=false,defaultValue="20")String num,
				HttpServletRequest request, Model model) {
			 int i=Integer.parseInt(num, 10);
			 PageHelper.startPage(page, i);
	        List<HistoryData> HistoryData=hisorydataservice.selectInfo();      
	         logger.info(JSON.toJSONString(HistoryData));  
	         PageInfo<HistoryData> p=new PageInfo<HistoryData>(HistoryData);
	         AllMN=hisorydataservice.selectAllMN();
	         model.addAttribute("AllMN",AllMN);
	         model.addAttribute("page", p);
	         model.addAttribute("num", i);
	         model.addAttribute("HistoryData",HistoryData);
	         
	         return "data/history_time_MN";  	
	    }
	@RequestMapping(value = "/QueryByTimeAndMN")
	public String TimeAndMN(
			@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam("StartTime")String StartTime, @RequestParam("EndTime")String EndTime, @RequestParam(required=true,defaultValue="1")String MN,
			@RequestParam(required=false,defaultValue="20")String num,
			HttpServletRequest request, Model model) {
		 int i=Integer.parseInt(num, 10);
		 PageHelper.startPage(page, i);
		Map<String, String> map = new HashMap<String, String>();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("startTime", StartTime);
		map.put("endTime", EndTime);
		map.put("mn", MN);
		List<HistoryData> HistoryData = hisorydataservice.selectByTimeAndMN(map);
		logger.info(JSON.toJSONString(HistoryData));
		PageInfo<HistoryData> p = new PageInfo<HistoryData>(HistoryData);
		model.addAttribute("page", p);
		model.addAttribute("num", i);
		model.addAttribute("HistoryData", HistoryData);
		model.addAttribute("StartTime",StartTime);
		model.addAttribute("EndTime",EndTime);
		return "data/history_time_MN";
	}

	@RequestMapping(value ="/exportALL")
    public void export(HttpServletResponse response){

		 List<HistoryData> HistoryData=hisorydataservice.selectInfo();    

		 ExportParams ep=new ExportParams("历史数据表","历史");
		 Workbook book= ExcelExportUtil.exportExcel(ep, HistoryData.class, HistoryData);
			try {
				response.setHeader("Content-Type", "application/vnd.ms-excel");	
				response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode("历史数据"+".xls", "UTF-8"));
				response.setCharacterEncoding("UTF-8");
				book.write(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@RequestMapping(value ="/exportMN")
    public void exportMN(HttpServletResponse response,@RequestParam(required=true,defaultValue="1")String MN){

		 List<HistoryData> HistoryData=hisorydataservice.selectByMN(MN) ;   

		 ExportParams ep=new ExportParams("历史数据表","历史");
		 Workbook book= ExcelExportUtil.exportExcel(ep, HistoryData.class, HistoryData);	
		try {
			response.setHeader("Content-Type", "application/vnd.ms-excel");	
			response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(MN+"站点的历史数据"+".xls", "UTF-8"));
			response.setCharacterEncoding("UTF-8");
			book.write(response.getOutputStream());
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@RequestMapping(value ="/exportTimeAndMN")
    public void exportTimeAndMN(HttpServletResponse response,@RequestParam("StartTime")String StartTime, @RequestParam("EndTime")String EndTime,@RequestParam(required=true,defaultValue="1")String MN){

		Map<String, String> map = new HashMap<String, String>();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("startTime", StartTime);
		map.put("endTime", EndTime);
		map.put("mn", MN);
		 List<HistoryData> HistoryData=hisorydataservice.selectByTimeAndMN(map) ;

		 ExportParams ep=new ExportParams("历史数据表","历史");
		 Workbook book= ExcelExportUtil.exportExcel(ep, HistoryData.class, HistoryData);	
		try {
			response.setHeader("Content-Type", "application/vnd.ms-excel");	
			response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(StartTime+"到"+EndTime+"之间"+"站点"+MN+"的数据"+".xls", "UTF-8"));
			response.setCharacterEncoding("UTF-8");
			book.write(response.getOutputStream());
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
