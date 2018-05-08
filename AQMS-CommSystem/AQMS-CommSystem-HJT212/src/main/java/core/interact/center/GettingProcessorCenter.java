package core.interact.center;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import core.structure.CommandPara;
import core.structure.DataPackage;
import core.structure.item.SimpleItem;
import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;

public class GettingProcessorCenter extends ProcessorCenter {

	public GettingProcessorCenter(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory)
	{
		super(cBase, proxy,reqFactory);
	}
	
	@Override
	public void sendData(String para) {
		// TODO Auto-generated method stub
		DataPackage req = reqFactory.buildRequest(curCN, new CommandPara());
		
		try {
			cBase.sendPck(req);
			super.sendData(null);
		}catch(SocketException eSocketException){
			//close socket
			try {
				cBase.sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	@Override
	protected boolean handleResponseData(DataPackage pck) {
		boolean ret=false;	
	/////后面应添加超时重发，重发失败后才能返回失败
		System.out.println("Getting handleResponseData!!!!");
//		try {
			if (pck.dataSeg.CN.compareTo(curCN) == 0)
			{
				
				//System.out.println(pck.build());	
				
				//将CP内容解析出来取出数据 赋值respString 
				CommandPara cp=(CommandPara)pck.dataSeg.CP;
				
				//cResponse.respString = cp.dataZone.build();	
				
				SimpleItem sItem = (SimpleItem)cp.dataZone.itemList.get(cp.dataZone.itemList.size()-1);
				//cResponse.respString = sItem.build();
				cResponse.respString = sItem.value;
				ret = true;
			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}						
		return ret;
	}
	
//	
//	@Override
//	protected boolean handleExeResult(DataPackage pck) {
//		// TODO Auto-generated method stub
//		System.out.println("getting handleExeResult!!!!11111111");
//		boolean ret=false;		
//		try {
//			if (pck.dataSeg.CN.compareTo(exeRtnCN) == 0)
//			{	System.out.println("getting handleExeResult!!!!2222222");			
//				System.out.println(pck.build());
//				
//				CommandPara cp=(CommandPara)pck.dataSeg.CP;
//				int index = cp.dataZone.itemList.size();
//				SimpleItem exeRtn =(SimpleItem) cp.dataZone.itemList.get(index-1);
//				
//				if(exeRtn.value.compareTo("1")==0)
//				{
//					ret=true;
//				}
//				else {
//					timer.cancel();
//					endHandleToResp(2);
//				}
//
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}						
//		return ret;
//	}
}
