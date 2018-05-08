package app.center.function.parameter;

import core.interact.center.SettingProcessorCenter;
import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;

public class SettingSystemTime extends SettingProcessorCenter{
	

	public SettingSystemTime(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory) {
		// TODO Auto-generated constructor stub
		super(cBase, proxy, reqFactory);
		setCurCN("1012");
		
	}
	
	
	
	
		
}
