package simpleServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import app.center.custom.CustomRequest;
import app.center.custom.CustomResponse;
import app.center.custom.HJT212LocalProxy;
import app.center.custom.HJT212QueueLocalProxy;
import app.util.HJT212Communicate;

public class CustomReqServer extends SimpleCenter{
	
	
	public static void main(String[] args) {  
        
    }
		
	private final Executor exec = Executors.newFixedThreadPool(20);//与下位机建立连接的连接池
	
		
	public void init() { 		
		ServerSocket serverSocket;
        try {  
            serverSocket = new ServerSocket(60001);
            
            System.out.println("sysServer starting...\n");
            while (true) {  
                // accept the connection
                Socket client = serverSocket.accept();
                client.setSoTimeout(60*1000);
                // handle this connection
                exec.execute(new CustomReqHandlerThread(client));  
            }  
        } catch (Exception e) {  
            System.out.println("Server error:" + e.getMessage());  
        } 
    }  
	
	public void handleResponse()
	{
		while(true)
		{
			try {
				HJT212LocalProxy proxy= proxyManager.getProxy("88888880000001");
				CustomResponse cResponse = (CustomResponse)proxy.responseList.take();
				String respsString=cResponse.mn+";"+cResponse.retType+";"+cResponse.respString;
				
				System.out.println("返回结果为："+respsString+"_____________________________");
				
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}				
	}
	
	  protected class CustomReqHandlerThread extends HandlerThread { 
		  protected HJT212QueueLocalProxy localProxy;
		  
		  public CustomReqHandlerThread(Socket client) {
			  super(client);
		    HJT212Communicate cBase = new HJT212Communicate(client);
			localProxy = new HJT212QueueLocalProxy(cBase, proxyManager);						
		  }		  		  	  
		  
		  public void run() {
			  System.out.println("sysServerSimple connect one!");
		      localProxy.start();
		      
			try {
				Thread.sleep(1*1000);
				CustomRequest req;
			    req = new CustomRequest("registReq", null);//regist!
				localProxy.addReq(req);	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		      
		      
//		      try {
//				Thread.sleep(2*1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//				new Thread(){
//				public void run()
//				{
//					handleResponse();
//				}
//			}.start();
//		      BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
//		      String read=null;
//		      CustomRequest req;
//		      while (true) {
//		    	  try {
//				  read = bReader.readLine();
//				  String[] readlist=read.split(" ");
//				  
//				  switch (readlist[0]) {
//				  case "1":
//					  req = new CustomRequest("startGettingRTData", null);
//					  localProxy.addReq(req);
//					  
//					  break;
//				  case "2":
//					  req = new CustomRequest("stopGettingRTData", null);
//					  localProxy.addReq(req);
//					  break;
//				  case "3":
//					  req = new CustomRequest("getSystemTime", null);
//					  localProxy.addReq(req);
//					  break;
//				  case "4":
//					  String dtime ="SystemTime="+readlist[1];
//					  String[] ststr= {dtime};					    	  
//					  req = new CustomRequest("setSystemTime", ststr);
//					  localProxy.addReq(req);
//					  break;
//				  case "5":
//					  String interval ="RtdInterval="+readlist[1];
//					  String[] intervalstr = {interval};
//					  req =new CustomRequest("setRtdInterval", intervalstr);
//					  localProxy.addReq(req);
//					  break;
////				  case "6":					  	
////					  String pw="PW="+readlist[1];
////					  String[] pwstr= {pw};
////					  req = new CustomRequest("setPassWord", pwstr);
////					  localProxy.addReq(req);
////					  break;
//				  default:
//					  break;
//					}
//			    	  						
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//				
//		      }
//		      
//		      		      
////		      try {
////		    	  	
////		    	  
////					Thread.sleep(2*1000);
////					req = new CustomRequest("getSystemTime", null);
////					localProxy.addReq(req);					
////					//handleResponse();
////					new Thread(){
////					public void run()
////					{
////						handleResponse();
////					}
////				}.start();
////					
////				Thread.sleep(6*1000);
////			    req = new CustomRequest("startGettingRTData", null);
////				localProxy.addReq(req);								
////				Thread.sleep(16*1000);
////				req = new CustomRequest("stopGettingRTData", null);
////				localProxy.addReq(req);
////												
//////				Thread.sleep(1*1000);
//////				req = new CustomRequest("getSystemTime", null);
//////				localProxy.addReq(req);
////				
////
////		    	  				
//////				String[] strlist= {"SystemTime=20040516010101"};					    	  
//////				Thread.sleep(2*1000);
//////				req = new CustomRequest("setSystemTime", strlist);
//////				localProxy.addReq(req);
////				
////				
//////			    Thread.sleep(6*1000);
//////			    String pw="PW=654321";
//////				String[] pwstr= {pw};
//////				req = new CustomRequest("setPassWord", pwstr);
//////				localProxy.addReq(req);
////												
////			  } catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////		      }
//		      
		  }  
	  } 
}
