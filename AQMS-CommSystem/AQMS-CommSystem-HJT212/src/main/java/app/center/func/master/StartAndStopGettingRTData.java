package app.center.func.master;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.LinkedBlockingQueue;

import app.center.custom.CustomRequest;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.ReqRTCommandSessionCenter;
import core.structure.CommandPara;
import core.structure.DataPackage;

public class StartAndStopGettingRTData extends ReqRTCommandSessionCenter{
	public RequestFactory reqFactory;
	
	public StartAndStopGettingRTData(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue,LinkedBlockingQueue<CustomRequest> list){
		super(cBase, queue, list);
	}

	@Override
	public boolean stopGettingRTCmd() {
		// TODO Auto-generated method stub
		return stopGettingRTData();
	}
	
	@Override
	protected boolean handleReply(DataPackage pck) {
		// TODO Auto-generated method stub
		System.out.println("start handleReply!!!!");
		boolean ret=false;
		try {
			
			System.out.println(pck.build());
			ret = true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public void handleRTData(DataPackage pck) {
		// TODO Auto-generated method stub
		System.out.println("StartGettingRTData handleRTData");
		//boolean ret=false;
		try {
			
			System.out.println(pck.build());
			//ret = true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	
	public synchronized boolean stopGettingRTData()
	{			
		StopGettingRTData session = new StopGettingRTData(cBase, queue);
		DataPackage req = reqFactory.buildRequeststop("2012", new CommandPara());
		boolean ret = session.execute(req, null);	
		
		return ret;
	}	
	
	public void setReqFactory(RequestFactory reqFactory){
		this.reqFactory = reqFactory;
	}
}
