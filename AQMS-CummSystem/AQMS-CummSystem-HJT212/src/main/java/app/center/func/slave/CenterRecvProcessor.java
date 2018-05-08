package app.center.func.slave;

import app.util.LocalInfo;
import core.structure.DataPackage;

public class CenterRecvProcessor extends RecvProcessor {
	
	RegistHandler regHandler;
	LocalInfo localInfo;
	
	
	public CenterRecvProcessor(LocalInfo localInfo) {
		super();
		this.localInfo = localInfo;
		regHandler = new RegistHandler(localInfo);
	}

	@Override
	public void process(DataPackage in, DataPackage out) {
		if(localInfo.MN == null || localInfo.PW == null || localInfo.ST == null)
		{
			regHandler.handle(in, out);
		}
		else
		{
			
		}
	}
	
}
