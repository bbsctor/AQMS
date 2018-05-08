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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
//import com.ly.util.Jurisdiction;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.entity.RealData;
import com.ly.service.RealDataService;

@Controller
@Scope(value = "prototype")
// @Scope(“prototype”)表示将Action的范围声明为原型
// 利用容器的scope=”prototype”来保证每一个请求有一个单独的Action来处理，避免struts中Action的线程安全问题。
@SessionAttributes(value = { "AllMN" })
@RequestMapping("/Echart")
public class EchartController {

	private static Logger logger = Logger.getLogger(EchartController.class);
	@Resource
	private RealDataService RealDataservice;
	private List<String> AllMN;

	@RequestMapping("/QueryDataByMN")
	 public @ResponseBody List<RealData> QueryDataByMN(HttpServletRequest request,@RequestParam(required=true,defaultValue="1") String MN)
	    {	   
		 List<RealData> RealData = RealDataservice.selectBySiteMN(MN);      
	         return RealData;  	
	    }
}
