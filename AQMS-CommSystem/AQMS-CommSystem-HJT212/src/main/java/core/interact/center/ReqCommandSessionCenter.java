package core.interact.center;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import app.util.HJT212Communicate;
import core.interact.CommandSession;
import core.structure.DataPackage;

public class ReqCommandSessionCenter extends CommandSession{

	
	public boolean timeoutFlag=false;
	public ReqCommandSessionCenter(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue) {
		super(cBase,queue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(DataPackage req, DataPackage resp) {
		boolean ret=false;
		try {			
			cBase.sendPck(req);			
			//DataPackage pck = cBase.recvPck();
			//System.out.println("qian--------queue.size()="+queue.size());			
			DataPackage pck = (DataPackage)queue.poll(10, TimeUnit.SECONDS);//这里可以超时重传			
			//System.out.println("hou---------queue.size()="+queue.size());
			if(null != pck)
			{
				ret=handleReply(pck);								
			}
			if(ret){
				ret=handleCustomNextResponse();
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
		return ret;
	}

	public boolean handleCustomNextResponse() {
		boolean ret=false;
		try {
			
			DataPackage pck = (DataPackage)queue.poll(10, TimeUnit.SECONDS);
			
			if(null != pck)
			{
				ret=handleExeResult(pck);												
			}										
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	protected boolean handleExeResult(DataPackage pck) {
		System.out.println("handleExeResult!!!!");
		boolean ret=false;
		try {
			
			System.out.println(pck.build());
			ret = true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}

	protected boolean handleReply(DataPackage pck) {
		System.out.println("handlereply!!!!");
		boolean ret=false;
		try {			
			System.out.println(pck.build());
			ret = true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public class TimeoutTimerTask extends TimerTask{
		public Timer timer;
		public TimeoutTimerTask(Timer timer) {
			// TODO Auto-generated constructor stub
			this.timer = timer;
		}
		public void run(){
			timeoutFlag = true;
			timer.cancel();
		}
	}

}
