package assignment6;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatClient implements Runnable {
	
	Socket sock;
	Scanner input;
	Scanner send = new Scanner(System.in);
	PrintWriter out;
	TextField TF_message;
	TextArea TA_conversation;

	
	public ChatClient (Socket s,TextField TF_message,TextArea TA_conversation ) {
		this.sock = s;
		this.TF_message = TF_message;
		this.TA_conversation = TA_conversation;
	}
	
	
	public void run() {
		
		try {
			
			try {
				input = new Scanner(sock.getInputStream());
				out = new PrintWriter(sock.getOutputStream());
				out.flush();
				CheckStream();
			}
			
			finally {
				sock.close();
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void disconnect() throws IOException {
		
		out.println(ClientGUI.UserName + " has disconnected");
		out.flush();
		sock.close();
		JOptionPane.showMessageDialog(null, "You disconnected");
		System.exit(0);
		
	}
	
	public void CheckStream() {
		while(true) {
			receive();
		}
	}
	
	public void receive() {
		if(input.hasNext()) {
			String message = input.nextLine();
			if(message.contains("$?!")) {
				String temp1 = message.substring(3);
				temp1 = temp1.replace("[", "");
				temp1 = temp1.replace("]", "");
				
				String[] CurrentUsers = temp1.split(", ");
				//ClientGUI.JL_ONLINE.setListData(CurrentUsers);
			}
			else {
				TA_conversation.appendText(message + "\n");
			}
		}
	}
	
	public void send(String str) {
		
		out.println(ClientGUI.UserName + ": " + str);
		out.flush();
		TF_message.setText("");  
		 
	}

}
