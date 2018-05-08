package com.ly.service.impl;

import org.springframework.stereotype.Service;

import com.ly.service.WebConnectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
@Service("WebConnectService")
public class WebConnectImpl implements WebConnectService {
 Socket socket=null;
	public void connect() {	
            //鍒涘缓Socket瀵硅薄
          try {
			socket=new Socket("localhost",8888);
		} catch (UnknownHostException e) {		
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
    }

	public String SendAndGetMessage(String message) {
		 String info="";
		 try {
	            //鏍规嵁杈撳叆杈撳嚭娴佸拰鏈嶅姟绔繛鎺�
	            OutputStream outputStream=socket.getOutputStream();//鑾峰彇涓�涓緭鍑烘祦锛屽悜鏈嶅姟绔彂閫佷俊鎭�
	            PrintWriter printWriter=new PrintWriter(outputStream);//灏嗚緭鍑烘祦鍖呰鎴愭墦鍗版祦
	            printWriter.print("鏈嶅姟绔綘濂斤紝鎴戞槸Balla_鍏斿瓙");
	            printWriter.flush();
	            socket.shutdownOutput();//鍏抽棴杈撳嚭娴�
	            
	            InputStream inputStream=socket.getInputStream();//鑾峰彇涓�涓緭鍏ユ祦锛屾帴鏀舵湇鍔＄鐨勪俊鎭�
	            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//鍖呰鎴愬瓧绗︽祦锛屾彁楂樻晥鐜�
	            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缂撳啿鍖�
	           
	            String temp=null;//涓存椂鍙橀噺
	            while((temp=bufferedReader.readLine())!=null){
	                info+=temp;
	                System.out.println("瀹㈡埛绔帴鏀舵湇鍔＄鍙戦�佷俊鎭細"+info);
	            }
	            
	            //鍏抽棴鐩稿搴旂殑璧勬簮
	            bufferedReader.close();
	            inputStream.close();
	            printWriter.close();
	            outputStream.close();
	           // socket.close();
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return info;
	}
	
	public void closeconnect() {
		// TODO Auto-generated method stub
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
