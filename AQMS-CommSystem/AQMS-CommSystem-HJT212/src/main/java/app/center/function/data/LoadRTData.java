package app.center.function.data;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zn.db.dao.RealDataDao;
import com.zn.db.entity.RealData;

import app.center.custom.HJT212LocalProxy;
import app.com.DataToFile;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.LoadingProcessorCenter;
import core.structure.CommandPara;
import core.structure.DataPackage;
import core.structure.DataSegment;
import core.structure.DataZone;
import core.structure.item.Item;
import core.structure.item.SimpleItem;


public class LoadRTData extends LoadingProcessorCenter {

	public HashMap<String, String> dataMap;
	public String qn;
	public boolean loadRtnFlag;
	public LoadRTData(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory,boolean loadRtnFlag)
	{
		super(cBase, proxy,reqFactory);
		setCurCN("2011");
		this.loadRtnFlag = loadRtnFlag;
		dataMap = new HashMap<String, String>();
	}
	
	@Override
	public boolean handleData(DataPackage resp) {
		// TODO Auto-generated method stub
		//入库，存数据库,返回入库结果
//		try{
			//System.out.println(resp.build());
			
//			DataZone dZone= ((CommandPara)(pck.dataSeg.CP)).dataZone;
//			CommandPara cp = (CommandPara)(pck.dataSeg.CP);
			parseRTData(resp);
			boolean sqlRet=saveToMySQL();
			if(sqlRet&&loadRtnFlag)
				sendData(null);//发送返回帧
		
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

		return true;
	}
	
	@Override
	public void sendData(String para) {
		// TODO Auto-generated method stub
		DataPackage req = reqFactory.buildDataRet(dataCN, this.qn, curCN);
		try {
			cBase.sendPck(req);
//		} catch (UnsupportedEncodingException e) {
//			// TODO: handle exception
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parseRTData(DataPackage resp)
	{				
		DataSegment dataSegment = resp.dataSeg;			
		DataZone dataZone =((CommandPara)(dataSegment.CP)).dataZone;
		
		this.qn=dataSegment.QN;
		
		//生成一个 map<key,value> mapper
		//将类对象的属性一一赋值，赋值形式为 so2-rtd = mapper["so2-rtd"]
		dataMap.clear();
		dataMap.put("MN", dataSegment.MN);	
		
		List<Item> dataList = dataZone.itemList;			
		for(int i=0; i< dataList.size(); i++)
		{
			SimpleItem sItem =(SimpleItem)dataList.get(i);
			dataMap.put(sItem.key, sItem.value);
		}						
		//System.out.println("dataList.size()="+dataList.size()+".............");
	
		
		//saveTXT
		DataToFile dtToFile = new DataToFile();
		dtToFile.saveDataTxt(dataSegment.MN,dataZone.build());			
		
	}
	public boolean  saveToMySQL() {
		// TODO Auto-generated method stub
		boolean ret= false;
		
		try {
			RealData realData = new RealData();	
			
			String tempstr;
			
			SimpleDateFormat famt = new SimpleDateFormat("yyyyMMddhhmmss");
			ParsePosition pos = new ParsePosition(0);
			Date strToDatetime = famt.parse(dataMap.get("DataTime"), pos);
			realData.setTime(strToDatetime);
			

	        realData.setMN(dataMap.get("MN"));        
//	        realData.setLatitude(8.69);
//	        realData.setLongtitude(8); 
	        
	        
			realData.setAQIindex(3);
	        realData.setAQIlevel(8);
	        
	        tempstr = dataMap.get("a01002-Rtd");
	        if(tempstr != null)
	        	realData.setMoisture(Double.parseDouble(tempstr));
	        realData.setMoistrue_flag(dataMap.get("a01002-Flag")); 
	        
	        
	        tempstr = dataMap.get("a01006-Rtd");
	        if(tempstr != null)
	        	realData.setWind_direction(Double.parseDouble(tempstr));
	        realData.setWind_direction_flag(dataMap.get("a01006-Flag"));
	        
	        tempstr = dataMap.get("a01007-Rtd");
	        if(tempstr != null)
	        	realData.setWind_speed(Double.parseDouble(tempstr));
	        realData.setWind_speed_flag(dataMap.get("a01007-Flag"));       
	        
	        
	        
	        tempstr = dataMap.get("a01006-Rtd");
	        if(tempstr != null)
	        	realData.setAtm_pressure(Double.parseDouble(tempstr));
	        realData.setAtm_pressure_flag(dataMap.get("a01006-Flag"));       
	        
	        tempstr = dataMap.get("a01001-Rtd");
	        if(tempstr != null)
	        	realData.setTemperature(Double.parseDouble(tempstr));
	        realData.setTemperature_flag(dataMap.get("a01001-Flag"));
	       
	        tempstr = dataMap.get("a21005-Rtd");
	        if(tempstr != null)
	        	realData.setCO(Double.parseDouble(tempstr));
	        realData.setCO_flag(dataMap.get("a21005-Flag"));
	        
	        tempstr = dataMap.get("a21004-Rtd");
	        if(tempstr != null)
	        	realData.setNO2(Double.parseDouble(tempstr));  
	        realData.setNO2_flag(dataMap.get("a21004-Flag"));
	        
	        tempstr = dataMap.get("a05024-Rtd");
	        if(tempstr != null)
	        	realData.setO3(Double.parseDouble(tempstr));
	        realData.setO3_flag(dataMap.get("a05024-Flag"));
	        
	        tempstr = dataMap.get("a21026-Rtd");
	        if(tempstr != null)
	        	realData.setSO2(Double.parseDouble(tempstr));
	        realData.setSO2_flag(dataMap.get("a21026-Flag")); 
	        
	        tempstr = dataMap.get("a34002-Rtd");
	        if(tempstr != null)
	        	realData.setPM10(Double.parseDouble(tempstr));
	        realData.setPM10_flag(dataMap.get("a34002-Flag"));             
	        
	        tempstr = dataMap.get("a34004-Rtd");
	        if(tempstr != null)
	        	realData.setPM25(Double.parseDouble(tempstr));
	        realData.setPM25_flag(dataMap.get("a34004-Flag"));                             
	        
	        tempstr = dataMap.get("a99054-Rtd");
	        if(tempstr != null)
	        	realData.setTVOC(Double.parseDouble(tempstr));
	        realData.setTVOC_flag(dataMap.get("a99054-Flag"));
	        
	       
			ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
			RealDataDao realDataDao = (RealDataDao)cxt.getBean("realDataDao");
	        realDataDao.addRealData(realData);
	        System.out.println("realData insert done!!!");

	        ret = true;
			return ret;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	

}
