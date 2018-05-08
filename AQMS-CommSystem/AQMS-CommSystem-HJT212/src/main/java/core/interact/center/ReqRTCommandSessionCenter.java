package core.interact.center;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import core.structure.DataPackage;
import app.center.custom.CustomRequest;
import app.util.HJT212Communicate;

public class ReqRTCommandSessionCenter extends ReqCommandSessionCenter {
	private boolean stopFlag = false;
	LinkedBlockingQueue<CustomRequest> reqlist;
	
	public ReqRTCommandSessionCenter(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue,LinkedBlockingQueue<CustomRequest> reqlist){
		super(cBase, queue);
		this.reqlist = reqlist;
	}

	@Override
	public boolean handleCustomNextResponse() {
		// TODO Auto-generated method stub		

		try {
			while(!stopFlag)
			{
				CustomRequest req= reqlist.poll(2,TimeUnit.SECONDS);//读取命令队列
				if(null != req)
				{
					if(req.action.equals("stopGettingRTData"))
					{
						if(stopGettingRTCmd()){
							stopFlag=true;							
						}
					}
				}else {
					DataPackage pck = (DataPackage)queue.poll(10, TimeUnit.SECONDS);//读实时数据设置超时时间
					if(null!= pck){
						handleRTData(pck);//实时数据处理函数
					}								
				}
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return true;

	}
	public boolean stopGettingRTCmd() {		
		return true;
	}
	
	public void handleRTData(DataPackage pck){
		
	}
	
}
