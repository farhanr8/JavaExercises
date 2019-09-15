package assignment6;

import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientGUI {
	
	private static ChatClient ChatClient;
	public static String UserName = "Anonymous";
	
	@FXML
	public TextField TF_username;
	public TextField TF_message;
	public TextArea TA_conversation;
	
	public void loginButtonClicked() {
		
		if(!TF_username.getText().equals("")) {
			 UserName = TF_username.getText().trim();
			 ServerMain.CurrentUsers.add(UserName);
			 Connect();
		 }
	}
	
	public void Connect() {
		try {
			final int port = 444;
			final String host = "Abigail";
			Socket sock = new Socket(host,port);
			System.out.println("You connected to: " + host);
			
			ChatClient = new ChatClient(sock,TF_message, TA_conversation);
			
			PrintWriter out = new PrintWriter(sock.getOutputStream());
			out.println(UserName);
			out.flush();
			
			Thread x = new Thread(ChatClient);
			x.start();
		}
		catch(Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}
	
	public void sendButtonClicked() {
		
		if(!TF_message.getText().equals("")) {
			 ChatClient.send(TF_message.getText());
			 TF_message.requestFocus();
		 }
	}
	
	public void disconnectButtonClicked() {
		
		try {
			ChatClient.disconnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
