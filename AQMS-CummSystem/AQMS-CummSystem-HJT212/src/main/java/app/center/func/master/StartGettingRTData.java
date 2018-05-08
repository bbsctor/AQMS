package app.center.func.master;

import java.util.concurrent.LinkedBlockingQueue;

import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.ReqCommandSessionCenter;


public class StartGettingRTData extends ReqCommandSessionCenter {

	public RequestFactory reqFactory;
	
	public StartGettingRTData(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue) {
		super(cBase,queue);
		// TODO Auto-generated constructor stub
	}
	



}
