package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Values.DataDetail;

public class ServerThread extends Thread {

	private boolean controlServer = true;
	private ServerSocket serverSocket;
	private String contactIpAdderss = "";
	private ArrayList<DataDetail> dataList = new ArrayList<>();
	
	public ServerThread(int serverPort) {
		try {
			this.serverSocket = new ServerSocket(serverPort);
		} catch (Exception e) {
			System.err.println("Socket starting was fail.");
			System.err.println(e.getMessage());
		}
	}
	
	public void run() {
		Socket socket = null;
		BufferedReader reader = null;
		PrintStream writer;
		String data = null;
		
		System.out.println("Server is Starting");
		System.out.println();
		while (this.controlServer) {
			data = null;
			try {
				synchronized (this.serverSocket) {
					socket = this.serverSocket.accept();
					setContactIpAdderss(socket.getInetAddress().getHostAddress());
				}
				System.out.println("Get contact from IP: " + getIpAddress());
				socket.setSoTimeout(15000);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintStream(socket.getOutputStream());
				
				data = reader.readLine();
				if (data != null) {
					dataList.add(new DataDetail(getIpAddress(), data, getTime()));
					writer.println("Check");
				} else {
					writer.println("Error");
				}
				writer.flush();
				System.out.println("Clinet: " + data);
				System.out.println(getTime());
				System.out.println("------------------------------------");
				System.out.println();
				socket.close();
			} catch (Exception e) {
				System.err.println("Contact Error");
				System.err.println(e.getMessage());
			}
		}
	}
	
	private String getTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");//設定時間格式
        return sdFormat.format(new Date());//取得現在時間
	}
	
	public void startServer() {
		this.controlServer = true;
	}
	
	public void stopServer() {
		this.controlServer = false;
		try {
			this.serverSocket.close();
			System.out.println("Server closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isStart() {
		return this.controlServer;
	}
	
	public void setContactIpAdderss(String contactIpAddress) {
		this.contactIpAdderss = contactIpAddress;
	}
	
	public String getIpAddress() {
		return this.contactIpAdderss;
	}
	
	public ArrayList<DataDetail> getData() {
		return this.dataList;
	}
	
	public DataDetail getLastData() {
		int index = this.dataList.size();
		if (index != 0) {
			index --;
			return this.dataList.get(index);
		} else {
			return null;
		}
	}
	
	public DataDetail getNewData() {
		int index = this.dataList.size()-1;
		return this.dataList.get(index);
	}
}
