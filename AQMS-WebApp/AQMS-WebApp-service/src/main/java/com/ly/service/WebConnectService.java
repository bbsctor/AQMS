package com.ly.service;

public interface WebConnectService {
	
	void connect();	 
	String SendAndGetMessage(String message);
	void closeconnect();

}
