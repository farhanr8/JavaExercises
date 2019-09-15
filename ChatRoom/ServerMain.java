package assignment6;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ServerMain {
	
	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<String> CurrentUsers = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		
		try {
			final int port = 444;
			ServerSocket server = new ServerSocket(port);
			System.out.println("Waiting for clients...");
			
			while(true) {
				Socket sock = server.accept();
				ConnectionArray.add(sock);
				
				System.out.println("Client connected from: " + sock.getLocalAddress().getHostName());
				
				AddUserName(sock);
				
				ServerReturn chat = new ServerReturn(sock);
				Thread X = new Thread(chat);
				X.start();
				
			}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		//ServerMain SERVER = new ServerMain();
		//SERVER.run();
	}
	
	public static void AddUserName(Socket X) throws IOException {
		
		Scanner input = new Scanner(X.getInputStream());
		String UserName = input.nextLine();
		CurrentUsers.add(UserName);
		
		for(int i = 1; i <= ServerMain.ConnectionArray.size(); i++) {
			Socket temp_sock = (Socket) ServerMain.ConnectionArray.get(i-1);
			PrintWriter out = new PrintWriter(temp_sock.getOutputStream());
			out.println("#?!" + CurrentUsers);
			out.flush();
		}
		
	}
	
	/*
	public void run() throws Exception {
		ServerSocket SRVSOCK = new ServerSocket(444);
		Socket SOCK = SRVSOCK.accept();
		InputStreamReader IR = new InputStreamReader (SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String MESSAGE = BR.readLine();
		System.out.println(MESSAGE);
		
		if(MESSAGE != null) {
			PrintStream PS = new PrintStream(SOCK.getOutputStream());
			PS.println("Message received!");
		}
	}
	*/

}
