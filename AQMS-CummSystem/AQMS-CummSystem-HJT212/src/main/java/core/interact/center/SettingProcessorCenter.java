package core.interact.center;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.structure.CommandPara;
import core.structure.DataPackage;
import core.structure.DataZone;

public class SettingProcessorCenter extends ProcessorCenter {

	public SettingProcessorCenter(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory)
	{
		super(cBase, proxy,reqFactory);
	}
	@Override
	public void sendData(String para) {
		// TODO Auto-generated method stub
		DataZone dZone = new DataZone(para);
		DataPackage req = reqFactory.buildRequest(curCN, new CommandPara(dZone));
		
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
				setCurState(STATE.RESULT);
	
			break;
			
		case RESULT:
			ret = handleExeResult(resp);
			if(ret)
			{
				timer.cancel();
				endHandleToResp(0);
			}
			
			break;

		default:
			break;
		}
	}

}
