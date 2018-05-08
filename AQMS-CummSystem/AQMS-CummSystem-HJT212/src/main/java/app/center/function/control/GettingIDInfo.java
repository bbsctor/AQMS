package app.center.function.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.GettingProcessorCenter;
import core.structure.DataPackage;

public class GettingIDInfo extends GettingProcessorCenter {
	boolean registFlag=false;
	Timer regisTimer;
	public GettingIDInfo(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory){
		super(cBase, proxy, reqFactory);
		setCurCN("3019");
	}
	
	@Override
	public void handle(DataPackage resp) {
		// TODO Auto-generated method stub
		if(registFlag)
		{
			boolean ret;
			// TODO Auto-generated method stub						
			switch (curState) {
			case REPLY:
				//DO REPLY
				ret = handleReply(resp);
				if (ret) 
					setCurState(STATE.RESPONSE);
				break;
				
			case RESPONSE:
				ret=handleResponseData(resp);
				if (ret) 
					setCurState(STATE.RESULT);
		
				break;
				
			case RESULT:
				ret = handleExeResult(resp);
				if(ret)
				{
					detach();
					proxy.loadingRTData(true);
					//endHandleToResp(0);//retType int类型    值含义：0-正常返回   1-出错
				}
				
				break;

			default:
				break;
			}
		}
		else {
			super.handle(resp);
		}

	}
	
	
	
	public void executeRegist() {
		// TODO Auto-generated method stub	
		registFlag=true;
		regisTimer = new Timer();
		regisTimer.schedule(new RegistTimerTask(), 20*1000,20*1000);		
		sendRegistReq();
		setCurState(STATE.REPLY);
		attach();
	}
	
	public void sendRegistReq()
	{
		DataPackage req = reqFactory.buildRegistReq();
		try {
			cBase.sendPck(req);
			
		}catch(SocketException eSocketException){
			//close socket
			regisTimer.cancel();
			try {
				cBase.sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("GettingIDInfo.sendRegistReq()........"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected boolean handleResponseData(DataPackage pck) {
		// TODO Auto-generated method stub		
		boolean ret=false;	
		try {
			if (pck.dataSeg.CN.compareTo(curCN) == 0)
			{
				System.out.println("Getting MN!!!!");
				System.out.println(pck.build());	
				regisTimer.cancel();
				//直接使用之前的注册现场机的代码
				proxy.handleRegist(pck);								
				
				ret = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}						
		return ret;
		
	}
	
	
	
	public class RegistTimerTask extends TimerTask{
		public void run(){
			//timeoutFlag = true;
			//timer.cancel();
			sendRegistReq();
			
		}
	}
}
