package app.center.function.control;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.RequestFactory;
import core.interact.center.SettingProcessorCenter;

public class TeleCalibration extends SettingProcessorCenter {
	public TeleCalibration(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory){
		super(cBase, proxy, reqFactory);
		setCurCN("3011");
	}

}
