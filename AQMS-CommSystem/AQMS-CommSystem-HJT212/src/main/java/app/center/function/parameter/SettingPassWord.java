package app.center.function.parameter;

import app.center.custom.HJT212LocalProxy;
import app.util.HJT212Communicate;
import app.util.LocalInfo;
import app.util.RequestFactory;
import core.interact.center.SettingProcessorCenter;

public class SettingPassWord extends SettingProcessorCenter {

	public String pwString;
	public LocalInfo localInfo;
	public SettingPassWord(HJT212Communicate cBase,HJT212LocalProxy proxy,RequestFactory reqFactory,LocalInfo localInfo) {
		// TODO Auto-generated constructor stub
		super(cBase, proxy, reqFactory);
		this.localInfo = localInfo;
		setCurCN("1072");
		
	}

	public void setPW(String pw)
	{
		pwString = pw;
	}
	public void setLocalInfoPW()
	{
		this.localInfo.PW = pwString;
	}
	

	@Override
	public void endHandleToResp(int retType) {
		// TODO Auto-generated method stub
		super.endHandleToResp(retType);
		setLocalInfoPW();
	}
}
