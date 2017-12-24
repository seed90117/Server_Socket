package UserInterface;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Socket.ServerController;


public class MainView extends JFrame {
	
	/**
	 * M10456012
	 * Kevin Yen
	 * kelly10056040@gmail.com
	 */
	private static final long serialVersionUID = 1L;
	private ServerController server = new ServerController();
	//*****宣告介面*****//
	Container cp = this.getContentPane();
	
	//*****宣告元件*****//
	JLabel portNumberLabel = new JLabel("Port Number:");
	JLabel statusLabel = new JLabel("Status: Server stop");
	
	JTextField portNumberTextField = new JTextField("8000");
	
	JButton stopServerButton = new JButton("Stop Server");
	JButton startServzerButton = new JButton("Start Server");
	
	MainView()
	{
		//*****設定介面*****//
		this.setSize(300, 250);
		this.setLayout(null);
		this.setTitle("Server Socket");
		
		//*****設定元件位置*****//
		portNumberLabel.setBounds(30, 30, 100, 30);
		portNumberTextField.setBounds(120, 30, 110, 30);
		
		stopServerButton.setBounds(30, 70, 210, 30);
		startServzerButton.setBounds(30, 110, 210, 30);
		statusLabel.setBounds(30, 150, 150, 30);
		
		cp.add(portNumberLabel);
		cp.add(portNumberTextField);
		cp.add(stopServerButton);
		cp.add(startServzerButton);
		cp.add(statusLabel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		stopServerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (server.closeServer()) {
					statusLabel.setText("Status: Server stop");
				} else {
					statusLabel.setText("Status: Server stop fail");
				}
			}});
		
		startServzerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(server.startServer(Integer.parseInt(portNumberTextField.getText()))) {
					statusLabel.setText("Status: Server start");
				} else {
					statusLabel.setText("Status: Server start fail");
				}
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainView();
	}
}
