package app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class CommunicateBase {
	public InputStream in;
	public OutputStream out;
	public Socket sock;
	//public boolean socketConnFlag=true;
	
	public CommunicateBase(Socket sock)
	{
		this.sock = sock;
		init();
	}
	
	void init()
	{
		try {
			in = sock.getInputStream();
			out = sock.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendBytes(byte[] bytes) throws IOException
	{
//		try {
			out.write(bytes);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public byte[] recvBytes() throws IOException
	{
		byte[] bytes = new byte[1024];
//		try {
			int len = in.read(bytes);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("CommunicateBase.recvBytes()error:"+e.getMessage());
////			socketConnFlag = false;
//			throw e;
//		}
			byte[] validBytes = Arrays.copyOfRange(bytes, 0, len);	
			//System.out.println("CommunicateBase.recvBytes()----------有效长度为："+len+"------数组长度为："+bytes.length+"------validBytes.length="+validBytes.length);
		
		//return bytes;
			return validBytes;
	}
	
	protected void sendStr(String data) throws IOException
	{
		sendBytes(data.getBytes("US-ASCII"));	
	}
	
	protected String recvStr() throws IOException
	{
		return new String(recvBytes(), "US-ASCII");
	}
}
