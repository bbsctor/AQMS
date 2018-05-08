package simpleServer;

public class TestServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Server starting...\n");  
        SimpleCenter server = new CustomReqServer(); 
        
        new Thread(){
        	public void run(){
        		WebServerSimple webServer = new WebServerSimple(server.proxyManager);
                webServer.webInit();
        	}
        }.start();
        
        server.init();  
	}

}
