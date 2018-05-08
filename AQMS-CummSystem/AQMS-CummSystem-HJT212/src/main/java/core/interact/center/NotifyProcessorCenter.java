package core.interact.center;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import core.structure.CommandPara;
import core.structure.DataPackage;
import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;

public class NotifyProcessorCenter extends ProcessorCenter {
	public NotifyProcessorCenter(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory) {
		// TODO Auto-generated constructor stub
		super(cBase, proxy, reqFactory);
	}
	
	
	@Override
	public void sendData(String para) {
		// TODO Auto-generated method stub
		DataPackage req = reqFactory.buildRequest(curCN, new CommandPara());
		
		try {
			cBase.sendPck(req);
			super.sendData(null);
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(DataPackage resp) {
		// TODO Auto-generated method stub
		boolean ret;
		// TODO Auto-generated method stub						
		switch (curState) {
		case REPLY:
			//DO REPLY
			ret = handleReply(resp);
			if (ret) 
			{
				timer.cancel();
				endHandleToResp(0);//retType int类型    值含义：0-正常返回   1-出错
			}
			
			break;

		default:
			break;
		}
	}
	
	@Override
	protected boolean handleReply(DataPackage pck) {
		// TODO Auto-generated method stub
		boolean ret=false;		
//		try {
			//if (pck.dataSeg.CN.compareTo(exeRtnCN) == 0)
			if (pck.dataSeg.CN.compareTo(notifyCN) == 0)			
			{
				System.out.println("notify handlereply!!!!");
				//System.out.println(pck.build());				
							
				ret = true;
			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}						
		return ret;
	}
	

	
}
