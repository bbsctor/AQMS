package app.center.custom;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import core.structure.DataPackage;
import core.structure.util.QNGenerator;
import app.center.func.slave.CenterRecvProcessor;
import app.util.HJT212Communicate;
import app.util.LocalInfo;
import app.util.RequestFactory;

public class HJT212LocalProxy extends LocalProxy{
		
	public LocalInfo localInfo;
	public HJT212Communicate cBase;
	public RequestFactory reqFactory;
	private DataPackage inPck;
	private DataPackage outPck;
	
	public CenterRecvProcessor recvProcessor;
	
	public boolean retChangedFlag;
	public String retString;
	
	public LocalProxyManager proxyManager;
	protected LinkedBlockingQueue<CustomRequest> list = new LinkedBlockingQueue<CustomRequest>();
	
	//public LinkedBlockingQueue<CustomResponse> respList;
	
	public boolean registFlag;

	public HJT212LocalProxy(HJT212Communicate cBase, LocalProxyManager manager/*,LinkedBlockingQueue<CustomResponse> repList*/) {
		super();
		//this.respList = repList;
		
		this.cBase = cBase;
		localInfo = new LocalInfo(null, null, null);
		recvProcessor = new CenterRecvProcessor(localInfo);		
		reqFactory = new RequestFactory(localInfo, new QNGenerator());
		proxyManager = manager;
	}
	public void loadingRTData(boolean loadRtnFlag)
	{
		
	}
	public void addReq(CustomRequest req)
	{
		list.add(req);
	}
	
	public void addResp(CustomResponse resp)
	{
		resp.mn =localInfo.MN;
		//respList.add(resp);
		responseList.add(resp);
	}
	
	@Override
	protected void handleReceive() {
		// TODO Auto-generated method stub
//		ArrayList<DataPackage> list = new ArrayList<DataPackage>();		
//		while(isRunning)
//		{	
//				try {
//					list = cBase.recvPck();
//					for(int i=0; i<list.size();i++)
//					{										
//						queue.put(list.get(i));
//					}
//					
//					if()
//					inPck = (DataPackage)queue.take();
//					recvProcessor.process(inPck, outPck);
//					
//					
//					
//					inPck = (DataPackage)queue.take();
//					setChanged();
//					notifyObservers(inPck);//通知观察者
//					
//					
//					
//				}catch(IOException ee)
//				{
//					isRunning = false;
//					ee.printStackTrace();
//					break;
//					
//				}catch (InterruptedException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//											
//		}
	}
	
	public boolean testNetConn() {
		// TODO Auto-generated method stub
		boolean ret=true;
		try {//发送检测是否断开  
            cBase.sock.sendUrgentData(0xFF);
            return ret;
        } catch (IOException e) {//断开产生异常，关闭对象  
           
        	ret =false; 
        	System.out.println("isrunning =  "+isRunning);
        	return ret;
//        	try {
//            	 cBase.sock.close();
//			} catch (IOException e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}            	         
//            cBase.sock = null;             
//            e.printStackTrace();             
        } 

		
	}
	
	@Override
	protected void recvToQueue() {
		// TODO Auto-generated method stub
		//从socket中读数据，存入缓存队列queue
		ArrayList<DataPackage> list = new ArrayList<DataPackage>();
		
		while(isRunning)
		{	
//			boolean ret = testNetConn();
//			System.out.println("HJT212LocalProxy.recvToQueue()     ret ="+ret);
//			if(testNetConn())
//			{
				try {
					list = cBase.recvPck();
					for(int i=0; i<list.size();i++)
					{										
						queue.put(list.get(i));
					}
				}catch(SocketTimeoutException exception)				   
				{
					isRunning = false;
					System.out.println("HJT212LocalProxy.recvToQueue().....SocketTimeoutException.......isRunning="+isRunning);
					try {
						if(cBase.sock!=null)
						{
							cBase.sock.close();
							System.out.println("HJT212LocalProxy.recvToQueue()......socket   closed!!!");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
					exception.printStackTrace();
				}catch(IOException ee)
				{
					isRunning = false;
					ee.printStackTrace();
					break;
					
//				}catch(SocketException exception){
//					
//					
//				} catch (UnsupportedEncodingException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//					break;
					
				}catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
					//System.out.println("CommunicateBase.recvBytes()error:"+e.getMessage());
				}
//			}else {
//				isRunning =false;
//			}											
		}
	}
	
	

	@Override
	protected void handleRecv() {
		// TODO Auto-generated method stub
		//从缓存队列中取，然后通知观察者
		
		//handleRegist();
		while(isRunning)
		{
			try {
				//inPck = (DataPackage)queue.take();
				inPck = (DataPackage)queue.poll(10, TimeUnit.SECONDS);
				if (inPck !=null) {
					setChanged();
					notifyObservers(inPck);//通知观察者
				}

					
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}			
		}		
	}

	public void handleRegist(DataPackage inpack){
			recvProcessor.process(inpack, outPck);
			//System.out.println("localInfo.MN="+localInfo.MN);
			proxyManager.registProxy(localInfo.MN, this);
			
	}
//	protected void handleRegist(){
//		try {
//			inPck = (DataPackage)queue.take();
//			recvProcessor.process(inPck, outPck);
//			System.out.println("localInfo.MN="+localInfo.MN);
//			proxyManager.registProxy(localInfo.MN, this);
//				
//		}catch(InterruptedException e)
//		{
//			e.printStackTrace();
//		}	
//		
//	}


	
		


	
}
