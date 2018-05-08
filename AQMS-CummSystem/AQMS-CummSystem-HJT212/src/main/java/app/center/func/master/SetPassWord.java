package app.center.func.master;

import java.util.concurrent.LinkedBlockingQueue;

import app.util.HJT212Communicate;
import core.interact.center.ReqCommandSessionCenter;

public class SetPassWord extends ReqCommandSessionCenter {

	public SetPassWord(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue){
		super(cBase,queue);		
	}
}
