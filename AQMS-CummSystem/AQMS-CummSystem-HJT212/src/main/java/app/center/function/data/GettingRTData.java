package app.center.function.data;

import java.io.UnsupportedEncodingException;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.GettingProcessorCenter;
import core.interact.center.LoadingProcessorCenter;
import core.structure.CommandPara;
import core.structure.DataPackage;
import core.structure.item.SimpleItem;

public class GettingRTData  extends GettingProcessorCenter{

	//LoadingProcessorCenter loadingProcessor;
	public boolean loadRtnFlag;
	
	public	GettingRTData(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory){
		super(cBase, proxy, reqFactory);
		setCurCN("2011");
		//this.loadingProcessor=loadingProcessor;
		loadRtnFlag=false;
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
				//loadingRTData();
				proxy.loadingRTData(this.loadRtnFlag);
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
		System.out.println("GettingRTData handlereply!!!!");
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
					if(pck.dataSeg.Flag.compareTo("1") == 0)
						loadRtnFlag = true;
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
		
	
//	public void  loadingRTData() {
//		// TODO Auto-generated method stub
//		//开始接收上传数据
//		loadingProcessor = new LoadRTData(cBase, proxy, reqFactory,loadRtnFlag);
//		loadingProcessor.execute(null);
//	}
//	
	

}

