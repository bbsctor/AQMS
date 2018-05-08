package app.center.custom;

import java.util.concurrent.TimeUnit;

import core.interact.center.LoadingProcessorCenter;
import app.center.function.control.GettingIDInfo;
import app.center.function.control.TeleCalibration;
import app.center.function.data.GettingRTData;
import app.center.function.data.LoadRTData;
import app.center.function.data.StopRTData;
import app.center.function.parameter.GettingSystemTime;
import app.center.function.parameter.SettingPassWord;
import app.center.function.parameter.SettingRtdInterval;
import app.center.function.parameter.SettingSystemTime;
import app.util.HJT212Communicate;

public class HJT212QueueLocalProxy extends HJT212LocalProxy {

	LoadingProcessorCenter loadingProcessor;
	//GettingProcessorCenter gettingProcessor;
	
	public HJT212QueueLocalProxy(HJT212Communicate cBase,LocalProxyManager manager/*,LinkedBlockingQueue<CustomResponse> repList*/) {
		//super(cBase, manager,repList);
		super(cBase, manager);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void handleReq() {
		// TODO Auto-generated method stub
		
//		CustomRequest req = new CustomRequest("startGettingRTData", null);		
//		list.add(req);
		
		while(isRunning)
		{
			try {
				//CustomRequest req= list.take();
				CustomRequest req = list.poll(10, TimeUnit.SECONDS);
				if(null != req)
					processCustomReq(req);				
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}


private void processCustomReq(CustomRequest req) {
		//对请求进行处理 new processor 并attach
	
		if(req.action.equals("startGettingRTData"))
		{
			startGettingRTData();
			//startAndStopGettingRTData();
		}
		else if(req.action.equals("stopGettingRTData"))
		{
			stopGettingRTData();
		}
		else if(req.action.equals("setSystemTime"))
		{
			setSystemTime(req.paras[0]);
		}
		else if(req.action.equals("getSystemTime"))
		{
			getSystemTime();
		}
		else if (req.action.equals("setPassWord")) 
		{
			setPassWord(req.paras[0]);
		}
		else if (req.action.equals("setRtdInterval")) 
		{
			setRtdInterval(req.paras[0]);
		}
		else if (req.action.equals("registReq")) {
			registReq();
		}
		else if (req.action.equals("teleCalibration")) {
			teleCalibration(req.paras);
			
		}
	}
public synchronized void teleCalibration(String[] strings)
{
	TeleCalibration processor = new TeleCalibration(cBase, this, reqFactory);
	String polIDCode=null;
	switch (strings[0]) {
	case "SO2":
		polIDCode = "a21026";		
		break;
	case "NO2":
		polIDCode = "a21004";		
		break;
	case "CO":
		polIDCode = "a21005";		
		break;
	case "O3":
		polIDCode = "a05024";		
		break;
	case "TVOC":
		polIDCode = "a99054";		
		break;

	default:
		break;
	}
	String calibrateString="PolId="+polIDCode+","+polIDCode+"="+strings[1];
	processor.execute(calibrateString);
}


public synchronized void registReq() {
	// TODO Auto-generated method stub
	GettingIDInfo processor = new GettingIDInfo(cBase, this, reqFactory);
	processor.executeRegist();
}


@Override
public void loadingRTData(boolean loadRtnFlag) {
	// TODO Auto-generated method stub
	if(loadingProcessor!=null)
	{
		loadingProcessor.detach();
	}	
	LoadRTData processor = new LoadRTData(cBase, this, reqFactory,loadRtnFlag);
	loadingProcessor=processor;
	processor.execute(null);
}

public synchronized void startGettingRTData()
{
	GettingRTData processor = new GettingRTData(cBase, this, reqFactory);
	//gettingProcessor=processor;
	processor.execute(null);
}
public synchronized void stopGettingRTData()
{
	StopRTData processor = new StopRTData(cBase, this, reqFactory, loadingProcessor);
	processor.execute(null);
	loadingProcessor=null;
}

public synchronized void setSystemTime(String  time)
{
	
	SettingSystemTime processor = new SettingSystemTime(cBase, this, reqFactory);
	String systime ="SystemTime="+time;
	processor.execute(systime);
	
}

public synchronized void getSystemTime()
{
	GettingSystemTime processor = new GettingSystemTime(cBase, this, reqFactory);	
	processor.execute(null);
}




public synchronized void setPassWord(String pw)
{
	
	SettingPassWord processor = new SettingPassWord(cBase, this, reqFactory, localInfo);
	processor.execute(pw);
	processor.setPW(pw.substring(3));
	
	
//	SetPassWord session = new SetPassWord(cBase, queue);
//	DataZone dZone = new DataZone(pw);
//	DataPackage req = reqFactory.buildRequest("1072", new CommandPara(dZone));
//	boolean ret =session.execute(req, null);
//	if(ret)
//	{
//		// "PW=654321"
//		setPW(pw.substring(3));
//	}
	
}

public synchronized void setRtdInterval(String interval) 
{	
	SettingRtdInterval processor = new SettingRtdInterval(cBase, this, reqFactory);
	String rdtinterval ="RtdInterval="+interval;
	processor.execute(rdtinterval);	
}
	
}
