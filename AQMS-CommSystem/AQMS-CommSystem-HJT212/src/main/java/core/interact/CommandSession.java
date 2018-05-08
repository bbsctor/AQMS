package core.interact;

import java.util.concurrent.LinkedBlockingQueue;

import app.util.HJT212Communicate;
import core.structure.DataPackage;

public class CommandSession {
	protected HJT212Communicate cBase;
	protected LinkedBlockingQueue<Object> queue;
	
	public CommandSession(HJT212Communicate cBase,LinkedBlockingQueue<Object> queue) {
		super();
		this.cBase = cBase;
		this.queue =queue;
	}

	public boolean execute(DataPackage req, DataPackage resp)
	{
		return true;
	}
}
