package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

import server.User;

public class EchoServer {
	static Vector<User> users;

	@SuppressWarnings("resource")
	public static void main() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10000);
		} catch (IOException e) {
			System.out.println("ServerSocket falls on initialization");
			System.exit(1);
		}
		while (true) {
			try {
				users.add(new User(serverSocket.accept()));
				users.lastElement().start();
			} catch (IOException e) {
				System.out.println("IOExsception while adding new connection");
			}
		}
	}
}
