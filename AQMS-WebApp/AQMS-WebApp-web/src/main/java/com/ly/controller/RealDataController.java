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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
//import com.ly.util.Jurisdiction;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.entity.HistoryData;
import com.ly.entity.RealData;
import com.ly.service.RealDataService;

@Controller
@Scope(value = "prototype")
// @Scope(“prototype”)表示将Action的范围声明为原型
// 利用容器的scope=”prototype”来保证每一个请求有一个单独的Action来处理，避免struts中Action的线程安全问题。
@SessionAttributes(value = { "AllMN" })
@RequestMapping("/RealData")
public class RealDataController {

	private static Logger logger = Logger.getLogger(RealDataController.class);
	@Resource
	private RealDataService RealDataservice;
	private List<String> AllMN;

	
	@RequestMapping(value = "/AllRealData")
	public String DataList(
			@RequestParam(required = true, defaultValue = "1") Integer page,
			HttpServletRequest request, Model model) {
		// PageHelper.startPage(page,	pageSize);这段代码表示，程序开始分页了，page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(page, 20);
		List<RealData> RealData = RealDataservice.getAllRealData();
		logger.info(JSON.toJSONString(RealData));
		PageInfo<RealData> p = new PageInfo<RealData>(RealData);
		model.addAttribute("page", p);
		model.addAttribute("RealData", RealData);
		AllMN = RealDataservice.selectAllMN();
		model.addAttribute("AllMN", AllMN);
		return "data/real_site";	
	}
	@RequestMapping("/QueryDataByMN")
	public String QueryDataByMN(
			@RequestParam(required = true, defaultValue = "1") Integer page,@RequestParam(required=true,defaultValue="1")String MN,
			@RequestParam(required=false,defaultValue="20")String num,
			HttpServletRequest request, Model model) {
		 int i=Integer.parseInt(num, 10);
		PageHelper.startPage(page, i);
		List<RealData> RealData = RealDataservice.selectBySiteMN(MN);
		PageInfo<RealData> p = new PageInfo<RealData>(RealData);
		model.addAttribute("page", p);
		model.addAttribute("num", i);
		model.addAttribute("RealData", RealData);
		AllMN = RealDataservice.selectAllMN();
		model.addAttribute("AllMN", AllMN);
		return "data/real_site";
	}
      
	 @RequestMapping(value="/RealDataByTimeInit")
	 public String RealDataByTimeInit(@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request,Model model)
	    {
		    PageHelper.startPage(page, 20);
		    List<RealData> RealData = RealDataservice.getAllRealData();  
	         logger.info(JSON.toJSONString(RealData));  
	        PageInfo<RealData> p=new PageInfo<RealData>(RealData);
	         model.addAttribute("page", p);
	         model.addAttribute("RealData",RealData);
	         
	         return "data/real_time";  	
	    }
	@RequestMapping(value = "/QueryDataByTime")
	public String QueryDataByTime(
			@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required=false,value="StartTime")String StartTime, @RequestParam(required=false,value="EndTime")String EndTime,
			@RequestParam(required=false,defaultValue="20")String num,
			HttpServletRequest request, Model model) {
		   int i=Integer.parseInt(num, 10);
			PageHelper.startPage(page, i);
		Map<String, String> map = new HashMap<String, String>();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("startTime", StartTime);
		map.put("endTime", EndTime);
		List<RealData> RealData = RealDataservice.selectByTime(map);
		logger.info(JSON.toJSONString(RealData));
		PageInfo<RealData> p = new PageInfo<RealData>(RealData);
		model.addAttribute("page", p);
		model.addAttribute("num", i);
		model.addAttribute("RealData", RealData);
		model.addAttribute("StartTime",StartTime);
		model.addAttribute("EndTime",EndTime);
		return "data/real_time";
	}
	
	@RequestMapping(value="/QueryByTimeAndMNInit")
	 public String QueryByTimeAndMNInit(@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request,Model model)
	    {
		    PageHelper.startPage(page, 20);
	        List<RealData> RealData=RealDataservice.getAllRealData();     
	         logger.info(JSON.toJSONString(RealData));  
	         PageInfo<RealData> p = new PageInfo<RealData>(RealData);
	         AllMN=RealDataservice.selectAllMN();
	         model.addAttribute("AllMN",AllMN);
	         model.addAttribute("page", p);
	         model.addAttribute("RealData",RealData);
	         
	         return "data/real_time_MN";  	
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
		List<RealData> RealData =RealDataservice.selectByTimeAndMN(map);
		logger.info(JSON.toJSONString(RealData));
		PageInfo<RealData> p = new PageInfo<RealData>(RealData);
		model.addAttribute("page", p);
		model.addAttribute("num", i);
		model.addAttribute("RealData", RealData);
		model.addAttribute("StartTime",StartTime);
		model.addAttribute("EndTime",EndTime);
		return "data/real_time_MN";
	}
	
	@RequestMapping("/exportMN")
	public void exportMN(@RequestParam(required=true,defaultValue="1")String MN,HttpServletResponse response) {
		
		List<RealData> RealData = RealDataservice.selectBySiteMN(MN);
		 ExportParams ep=new ExportParams("实时数据表","实时");
		 Workbook book= ExcelExportUtil.exportExcel(ep, RealData.class, RealData);	
		try {
			response.setHeader("Content-Type", "application/vnd.ms-excel");	
			response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode("站点"+MN+"的数据"+".xls", "UTF-8"));
			response.setCharacterEncoding("UTF-8");
			book.write(response.getOutputStream());
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	@RequestMapping(value ="/exportTimeAndMN")
    public void exportTimeAndMN(HttpServletResponse response,@RequestParam("StartTime")String StartTime, @RequestParam("EndTime")String EndTime, @RequestParam(required=true,defaultValue="1")String MN){

		Map<String, String> map = new HashMap<String, String>();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("startTime", StartTime);
		map.put("endTime", EndTime);
		map.put("mn", MN);
		 List<RealData> RealData=RealDataservice.selectByTimeAndMN(map) ;

		 ExportParams ep=new ExportParams("实时数据表","实时");
		 Workbook book= ExcelExportUtil.exportExcel(ep, RealData.class, RealData);	
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
