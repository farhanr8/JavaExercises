package assignment6;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerReturn implements Runnable{
	
	Socket sock;
	private Scanner input;
	private PrintWriter out;
	String message = "";
	
	public ServerReturn(Socket s) {
		this.sock = s;
		
	}
	
	public void CheckConnection() throws IOException{
		
		if(!sock.isConnected()) {
			
			for(int i = 1; i <= ServerMain.ConnectionArray.size(); i++) {
				
				if(ServerMain.ConnectionArray.get(i) == sock) {
					ServerMain.ConnectionArray.remove(i);
					
				}
			}
			
			for(int i = 1; i <= ServerMain.ConnectionArray.size(); i++) {
				
				Socket temp_sock = (Socket) ServerMain.ConnectionArray.get(i-1);
				PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
				temp_out.println(temp_sock.getLocalAddress().getHostName() + " disconnected");
				temp_out.flush();
				System.out.println(temp_sock.getLocalAddress().getHostName() + " disconnected");
			}
			
		}
	}
	
	public void run() {
		try {
			try {
				input = new Scanner(sock.getInputStream());
				out = new PrintWriter(sock.getOutputStream());
				
				while(true) {
					CheckConnection();
					if(!input.hasNext()) {
						return;
					}
					
					message = input.nextLine();
					
					System.out.println("Client said: " + message);
					
					for(int i = 1; i <= ServerMain.ConnectionArray.size(); i++) {
						Socket temp_sock = (Socket) ServerMain.ConnectionArray.get(i-1);
						PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
						temp_out.println(message);
						temp_out.flush();
						System.out.println("Sent to: " + temp_sock.getLocalAddress().getHostName());
					}
					
				}
			}
			
			finally {
				sock.close();
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
