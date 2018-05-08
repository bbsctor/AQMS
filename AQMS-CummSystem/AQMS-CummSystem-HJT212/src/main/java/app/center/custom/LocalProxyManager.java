package app.center.custom;

import java.util.HashMap;
import java.util.Map;

public class LocalProxyManager{
	private Map<String, HJT212LocalProxy> proxyMapper;

	public LocalProxyManager() {
		super();
		proxyMapper = new HashMap<String, HJT212LocalProxy>(); 
	}
	
	public void registProxy(String mn, HJT212LocalProxy proxy)
	{
		proxyMapper.put(mn, proxy);
	}
	
	public HJT212LocalProxy getProxy(String mn)
	{
		return proxyMapper.get(mn);
	}
	
}
