package core.interact.center;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import app.center.custom.CustomResponse;
import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.Processor;
import core.interact.center.ReqCommandSessionCenter.TimeoutTimerTask;
import core.structure.CommandPara;
import core.structure.DataPackage;
import core.structure.item.SimpleItem;

public class ProcessorCenter extends Processor {

	protected enum STATE{REPLY,RESPONSE,RESULT};
	final protected String replyCN="9011";
	final protected String exeRtnCN="9012";
	final protected String notifyCN="9013";
	final protected String dataCN="9014";
	protected String curCN="";
	protected STATE curState;
	public RequestFactory reqFactory;
	
	public CustomResponse cResponse = new CustomResponse();

	public boolean timeoutFlag;//超时标志	
	public int repeatcount=0;//超时重发次数
	
	public ProcessorCenter(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory)
	{
		super(cBase, proxy);
		this.reqFactory = reqFactory;
	}

	public void setCurCN(String cn)
	{
		curCN = cn;
	}
	
	@Override
	public void execute(String para) {
		// TODO Auto-generated method stub
		if(!cBase.sock.isClosed())
		{
			sendData(para);
			setCurState(STATE.REPLY);
			attach();
		}

	}
	
	@Override
	public void handle(DataPackage resp) {
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
				timer.cancel();
				endHandleToResp(0);//retType int类型    值含义：0-正常返回   1-出错
			}
			
			break;

		default:
			break;
		}
	}
	
	public void endHandleToResp(int retType)
	{
		detach();
		cResponse.retType =retType;				
		addResponse(cResponse);
		
	}
	
	public void setCurState(STATE state) {
		curState = state;		
	}
	
	
	public void sendData(String para)
	{
		timer=new Timer();
		timer.schedule(new TimeoutTimerTask(timer), 30*1000);
	}
	public void attach()
	{
		proxy.addObserver(this);
		//System.out.println("ProcessorCenter.attach().......................");
	}
	
	public void detach()
	{
		
		proxy.deleteObserver(this);
		//System.out.println("ProcessorCenter.detach()......................");
	}
	
	public void addResponse(CustomResponse resp) {
		proxy.addResp(resp);
	}
	
	protected boolean handleReply(DataPackage pck) {
		System.out.println("handlereply!!!!");
		boolean ret=false;		
//		try {
			if (pck.dataSeg.CN.compareTo(replyCN) == 0)
			{				
				//System.out.println(pck.build());
				
				CommandPara cp=(CommandPara)pck.dataSeg.CP;
				int index = cp.dataZone.itemList.size();
				SimpleItem qnRtn =(SimpleItem) cp.dataZone.itemList.get(index-1);
				
				if(qnRtn.value.compareTo("1")==0)
				{
					ret=true;
				}
				else {
					/////////detach();/////后面应添加超时重发，重发失败后才能detach()
					timer.cancel();
					endHandleToResp(2);
				}
			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}						
		return ret;
	}
	
	protected boolean handleResponseData(DataPackage pck) {
		System.out.println("handleResponseData!!!!");
		boolean ret=false;
//		try {			
//			System.out.println(pck.build());
//			ret = true;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		ret = true;
		return ret;
	}
	
	protected boolean handleExeResult(DataPackage pck) {
		//System.out.println("processorcenter handleExeResult!!!!");
		boolean ret=false;		
//		try {
			if (pck.dataSeg.CN.compareTo(exeRtnCN) == 0)
			{	System.out.println("handleExeResult!!!!");			
				//System.out.println(pck.build());
				
				CommandPara cp=(CommandPara)pck.dataSeg.CP;
				int index = cp.dataZone.itemList.size();
				SimpleItem exeRtn =(SimpleItem) cp.dataZone.itemList.get(index-1);
				
				if(exeRtn.value.compareTo("1")==0)
				{
					ret=true;
				}
				else {
					timer.cancel();
					endHandleToResp(2);
				}

			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}						
		return ret;
	}
	
	
	public class TimeoutTimerTask extends TimerTask{
		public Timer timer;
		public TimeoutTimerTask(Timer timer) {
			// TODO Auto-generated constructor stub
			this.timer = timer;
		}
		public void run(){
			//timeoutFlag = true;
			timer.cancel();
			endHandleToResp(3);
		}
	}

}
