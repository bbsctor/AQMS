package simpleServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


import java.util.concurrent.TimeUnit;

import app.center.custom.CustomRequest;
import app.center.custom.CustomResponse;
import app.center.custom.HJT212LocalProxy;
import app.center.custom.LocalProxyManager;


public class WebServerSimple {

	public static final int PORT = 60002;
	private final Executor exec = Executors.newFixedThreadPool(10);
	public LocalProxyManager proxyManager;
	public WebServerSimple(LocalProxyManager proxyManager)
	{
		this.proxyManager = proxyManager;
	}
	
	public void webInit(){
		ServerSocket serverSocket;
        try {  
            serverSocket = new ServerSocket(PORT);  
            System.out.println("WEBServer starting...\n");
            while (true) {   
                Socket webClient = serverSocket.accept();            	
				exec.execute(new Handler(webClient));
           
            }  
        } catch (Exception e) {  
            System.out.println("server error:" + e.getMessage());  
        } 
	}
	protected class Handler implements Runnable {  
		InputStream webin;
		OutputStream webout;
		Socket webClient;  
		
		public Handler(Socket webClient) { 
		     this.webClient = webClient;
		}  
		
		public void run() {
		      try {
		    	  System.out.println("WebServerSimple connect one!");
		    	  
		    	  webin = webClient.getInputStream(); 
		    	  webout = webClient.getOutputStream();
		    	  
		    	  InputStreamReader inReader = new InputStreamReader(webin);
		    	  BufferedReader bufferedReader = new BufferedReader(inReader);
		    	  
		    	  OutputStreamWriter outWriter = new OutputStreamWriter(webout);
		    	  BufferedWriter bufferedWriter = new BufferedWriter(outWriter);
		    	  
		    	  
		    	  String buffer = null;
		    	  while((buffer =bufferedReader.readLine())!=null){
		    		  //System.out.println("WebServerSimple readbuffer:"+buffer);
		    		  String[] splitStrings=buffer.split(";");//MN;CN;Para
		    		  HJT212LocalProxy proxy = proxyManager.getProxy(splitStrings[0]);
		    		  String writeString =null;
		    		  if(proxy==null || !proxy.isRunning)
		    		  {
		    			  writeString = splitStrings[0]+";1;null\n";
		    			  //System.out.println("webServerSimple rtn  one errer sysRtn !");
		    			  bufferedWriter.write(writeString);
		    			  bufferedWriter.flush();
		    			  continue;
		    		  }
		    		  
		    		  String[] reqPara =null;
		    		  if(splitStrings.length==3){
		    			  reqPara = new String[] {splitStrings[2]};
		    		  }
		    		  else if (splitStrings.length==4) {
		    			  reqPara = new String[] {splitStrings[2],splitStrings[3]};
		    		  }
		    		  CustomRequest req = new CustomRequest(splitStrings[1],reqPara);
		    		  proxy.addReq(req);
		    		  boolean flag =false;
		    		  while(proxy.isRunning)
		    		  {
		    			  
		    			  try {
							//CustomResponse response = proxy.responseList.poll(10, TimeUnit.SECONDS);
		    				  CustomResponse response = proxy.responseList.take();
							if(response !=null)
							{
								//System.out.println("webServerSimple read  one sysRtn !");
								writeString=response.mn+";"+response.retType+";"+response.respString+"\n";
								flag = true;
								break;
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		  }
		    		  if(!flag)
		    			  writeString = splitStrings[0]+";1;null\n";
		    		  //System.out.println("WebServerSimple writeString:"+writeString);
		    		  bufferedWriter.write(writeString);
		    		  bufferedWriter.flush();
		    	  }
		    	  
		            //关闭相对应的资源
		            bufferedWriter.close();
		            outWriter.close();
		            webout.close();
		            bufferedReader.close();
		            inReader.close();
		            webin.close();
		            webClient.close();
		            System.out.println("WebServerSimple.Handler.run()------------close one WEBsocket!111111111111");
			} catch (IOException e) {
				// TODO: handle exception
				 try {
					webClient.close();
					System.out.println("WebServerSimple.Handler.run()------------close one WEBsocket!2222222222222");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}  
	 } 
}
