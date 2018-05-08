package app.center.custom;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class LocalProxy extends Observable {


	public boolean isRunning;
	
	// 仓库最大存储量
	private final int MAX_SIZE = 100;

	// 仓库存储的载体
	//协议帧缓存队列
	protected LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(MAX_SIZE);
	public LinkedBlockingQueue<CustomResponse> responseList = new LinkedBlockingQueue<CustomResponse>();
	
	protected void recvToQueue(){
		//从socket中读数据，存入缓存队列
		// receive data from socket to recvQueue		
	}
	protected void handleReq() {
		//handle reqList
	}
	protected void handleRecv() {
		//handle recvQueue
	}
	protected void handleReceive(){
		// handle receive data to list and notify observers
	}
	
	protected void addRepList(){
		// add 反控响应结果
		
	}

	public void start()
	{
		
		isRunning = true;
		new Thread(){
			public void run()
			{
				recvToQueue();
			}
		}.start();
		
		new Thread(){
			public void run()
			{
				handleReq();
			}
		}.start();
		

		new Thread(){
			public void run()
			{
				handleRecv();
			}
		}.start();
		
			
	
	}
	
	public void stop()
	{
		isRunning = false;
	}
}
