package app.center.func.master;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import app.util.HJT212Communicate;
import core.interact.center.ReqCommandSessionCenter;
import core.structure.DataPackage;

public class GetSystemtime extends ReqCommandSessionCenter {

	public GetSystemtime(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue){
		super(cBase,queue);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean handleCustomNextResponse() {
		// TODO Auto-generated method stub
		boolean ret=false;
		try {					    
				DataPackage pck = (DataPackage)queue.poll(10, TimeUnit.SECONDS);			
				if(null!= pck)
				{
					ret=handleResponse(pck);
				}
				if(ret)
				{
					//System.out.println("qian--------queue.size()="+queue.size());
//				    DataPackage haderq1=(DataPackage)queue.peek();
//				    try {
//				    	System.out.println("queue[header]="+haderq1.build());
//					} catch (UnsupportedEncodingException e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
					DataPackage pck2 = (DataPackage)queue.poll(10, TimeUnit.SECONDS);
					//System.out.println("hou---------queue.size()="+queue.size());
					if(null!= pck2)
					{
						ret=handleExeResult(pck2);
					}
				}												
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return ret;
	}
	

	
	protected boolean handleResponse(DataPackage pck) {
			System.out.println(" GetSystemtime  handleResponse!!!!");
			boolean ret=false;
			try {			
				System.out.println(pck.build());
				ret = true;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return ret;

	}
}
