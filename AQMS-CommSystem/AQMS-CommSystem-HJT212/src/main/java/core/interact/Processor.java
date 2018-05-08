package core.interact;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import core.structure.DataPackage;

public class Processor implements Observer {

	protected Timer timer;
	protected HJT212Communicate cBase;
	protected HJT212LocalProxy proxy;
	//protected String curCN="";
	public Processor(HJT212Communicate cBase,HJT212LocalProxy proxy)
	{
		this.cBase = cBase;
		this.proxy = proxy;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		handle((DataPackage)arg);
	}
	
	public void execute(String para)
	{
		
	}
	
	public void handle(DataPackage resp) {
		
		
	}
	
//	public void setCurCN(String cn)
//	{
//		curCN = cn;
//	}
}
