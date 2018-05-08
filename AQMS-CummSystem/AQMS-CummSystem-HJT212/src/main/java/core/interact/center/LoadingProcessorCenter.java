package core.interact.center;

import java.io.UnsupportedEncodingException;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.structure.DataPackage;

public class LoadingProcessorCenter extends ProcessorCenter {

	public LoadingProcessorCenter(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory){
		super(cBase, proxy,reqFactory);
	}
	
	@Override
	public void execute(String para) {
		// TODO Auto-generated method stub
		setCurState(STATE.RESPONSE);
		attach();
	}	
	
	@Override
	public void handle(DataPackage resp) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub						
		switch (curState) {			
		case RESPONSE:
			handleResponseData(resp);
	
			break;


		default:
			break;
		}
	}
	
	@Override
	protected boolean handleResponseData(DataPackage pck) {
		// TODO Auto-generated method stub
		if (pck.dataSeg.CN.compareTo(curCN) == 0)
		{
			System.out.println("loading handleResponseData!!!!");	
			
			//对于2017版，此处发送接收应答send()。
			
			return handleData(pck);
			
		}
		else {
			return false;
		}
	}
	
	public boolean handleData(DataPackage resp)
	{
		//入库，存数据库,返回入库结果
		try{
//			DataZone dZone= ((CommandPara)(pck.dataSeg.CP)).dataZone;
//			CommandPara cp = (CommandPara)(pck.dataSeg.CP);
	
			System.out.println(resp.build());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return true;
	}
						
	
}
