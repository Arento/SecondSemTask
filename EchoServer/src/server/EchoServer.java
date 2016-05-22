package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

import server.User;

public class EchoServer {
	static Vector<User> users;

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		ServerSocket serverSocket = null;
		if (args.length == 0) {
			System.out.println("Please, give a port as first argument");
			System.exit(-1);
		}
		users = new Vector<User>();
		try {
			serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			System.out.println("Server started.");
		} catch (Exception e) {
			System.out.println("ServerSocket falls on initialization");
			System.exit(1);
		}
		while (true) {
			try {
				users.add(new User(serverSocket.accept()));
				System.out.println("Client connected");
				users.lastElement().start();
				System.out.println("Client started");
			} catch (IOException e) {
				System.out.println("IOExsception while adding new connection");
			}
		}
	}
}
