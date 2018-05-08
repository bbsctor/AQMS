package app.center.func.master;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import app.util.HJT212Communicate;
import core.interact.center.ReqCommandSessionCenter;
import core.structure.DataPackage;

public class StopGettingRTData extends ReqCommandSessionCenter{

	public StopGettingRTData(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue) {
		super(cBase,queue);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean execute(DataPackage req, DataPackage resp) {
		// TODO Auto-generated method stub
		boolean ret=false;
		boolean recvFlag=false;
		DataPackage pck=null;
		int repeatcount=3;//超时重发次数
		while(repeatcount>0){
			try {
				cBase.sendPck(req);					
				Timer timeoutTimer = new Timer();
				timeoutTimer.schedule(new TimeoutTimerTask(timeoutTimer), 10*1000);
				//DataPackage pck = cBase.recvPck();
				while(!timeoutFlag)
				{
					 pck = (DataPackage)queue.poll(10, TimeUnit.SECONDS);//这里可以超时重传
					if(null != pck)
					{
						if (pck.dataSeg.CN.compareTo("9013") == 0) {
							//收到正确应答
							timeoutFlag = true;//设置循环退出条件为真，下次退出循环
							timeoutTimer.cancel();	
							recvFlag=true;
						}else {						
							System.out.println("收到数据，但不是应答！！！");
						}					
					}						
				}
				if(recvFlag && null != pck)
				{
					ret=handleReply(pck);
					repeatcount=0;
				}else {
					repeatcount--;
				}								
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}					
		return ret;
	}
	
	@Override
	protected boolean handleReply(DataPackage pck) {
		// TODO Auto-generated method stub
		System.out.println("stop handleReply!!!!");
		return true;
	}
	
//	@Override
//	public void handleCustomNextResponse() {
//		// TODO Auto-generated method stub
//		System.out.println("stop do nothing!!!!");
//	
//	}

}
