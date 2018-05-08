package app.center.function.data;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.GettingProcessorCenter;
import core.interact.center.LoadingProcessorCenter;
import core.interact.center.NotifyProcessorCenter;
import core.structure.DataPackage;

public class StopRTData extends NotifyProcessorCenter{
	LoadingProcessorCenter loadingProcessor;
	GettingProcessorCenter gettingProcessor;
	
	public StopRTData(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory,LoadingProcessorCenter loadingProcessor) {
		// TODO Auto-generated constructor stub
		super(cBase, proxy, reqFactory);
		this.loadingProcessor = loadingProcessor;
		//this.gettingProcessor = gettingProcessor;
		setCurCN("2012");
		
	}
	
	@Override
	public void handle(DataPackage resp) {
		// TODO Auto-generated method stub
		switch (curState) {
		case REPLY:
			//DO REPLY
				
			boolean ret = handleReply(resp);
			if(ret)
			{
				stopGettingRTData();
			}
			break;

		default:
			break;
		}
	}

	
	public void stopGettingRTData() {
		// TODO Auto-generated method stub
		timer.cancel();
		endHandleToResp(0);
		if(loadingProcessor!=null)
		{
			loadingProcessor.detach();
			loadingProcessor=null;
		}
			
//		GettingRTData aData = (GettingRTData)gettingProcessor;
//		aData.loadingProcessor.detach();
	}
	
}
