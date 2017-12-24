package Socket;

import Values.DataDetail;

public class ServerController {
	
	private ServerThread serverThread = null;
	private int port = 0;
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public boolean startServer(int port) {
		try {
			this.port = port;
			this.serverThread = new ServerThread(port);
			this.serverThread.start();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean closeServer() {
		try {
			this.serverThread.stopServer();
			this.serverThread.stop();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getReceiveLastMessage() {
		DataDetail dataDetail = serverThread.getLastData();
		if (dataDetail != null) {
			return dataDetail.message;
		} else {
			return "No Data";
		}
	}
}
