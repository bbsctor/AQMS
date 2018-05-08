package simpleServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import app.center.custom.CustomResponse;
import app.center.custom.HJT212LocalProxy;
import app.center.custom.HJT212QueueLocalProxy;
import app.center.custom.LocalProxyManager;
import app.util.HJT212Communicate;

public class SimpleCenter {
	public static final int PORT = 60001;
	public LocalProxyManager proxyManager;
	private final Executor exec = Executors.newFixedThreadPool(20);
	//public LinkedBlockingQueue<CustomResponse> respList = new LinkedBlockingQueue<CustomResponse>();
	
	public LinkedBlockingQueue<CustomResponse> rrrr;
    public SimpleCenter() {
		super();
		proxyManager = new LocalProxyManager();
	}

	public static void main(String[] args) {  
        System.out.println("server start...\n");  
        SimpleCenter server = new SimpleCenter();  
        server.init();  
    }  
	
  
    public void init() { 
    	ServerSocket serverSocket;
        try {  
            serverSocket = new ServerSocket(PORT);  
            while (true) {  

                Socket client = serverSocket.accept();  

                exec.execute(new HandlerThread(client));  
            }  
        } catch (Exception e) {  
            System.out.println("�������쳣: " + e.getMessage());  
        } 
    }  

	  protected class HandlerThread implements Runnable {  
		  protected HJT212LocalProxy localProxy;
		  
		  public HandlerThread(Socket client) { 
		      HJT212Communicate cBase = new HJT212Communicate(client);
		      //localProxy = new HJT212LocalProxy(cBase, proxyManager);
		      localProxy = new HJT212QueueLocalProxy(cBase, proxyManager);
		  }  
		
		  public void run() {
		      localProxy.start();
		  }  
	  } 
}
