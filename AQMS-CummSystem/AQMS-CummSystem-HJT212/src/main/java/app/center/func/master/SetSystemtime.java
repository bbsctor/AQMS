package app.center.func.master;
import java.util.concurrent.LinkedBlockingQueue;

import app.util.HJT212Communicate;
import core.interact.center.ReqCommandSessionCenter;

public class SetSystemtime extends ReqCommandSessionCenter {

	public SetSystemtime(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue){
		super(cBase,queue);
		// TODO Auto-generated constructor stub
	}
}

