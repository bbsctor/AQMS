package app.center.function.parameter;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.GettingProcessorCenter;

public class GettingSystemTime extends GettingProcessorCenter {

	public GettingSystemTime(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory)
	{
		super(cBase, proxy,reqFactory);
		setCurCN("1011");
	}
	

			
}
