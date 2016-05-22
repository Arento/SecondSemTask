package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import server.EchoServer;
import server.Message.MessageType;

public class User extends Thread {
	String name;
	Socket connection;
	BufferedReader in;
	PrintWriter out;

	public User(Socket socket) throws IOException {
		connection = socket;
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		out = new PrintWriter(connection.getOutputStream(), true);
		name = "Unknown";
	}

	public void send(String message) {
		out.println(message);
	}

	@Override
	public void run() {
		String message = null;
		while (true) {
			try {
				message = in.readLine();
			} catch (IOException e) {
				System.err.println("IOException while recieving message");
			}
			if (message != null) {
				Message formattedMessage = new Message(message);
				if (formattedMessage.type != MessageType.Unknown) {
					for (int i = 0; i < EchoServer.users.size(); i++) {
						if (EchoServer.users.get(i) != this)
							EchoServer.users.get(i).send(message);
					}
					if (formattedMessage.type == MessageType.Exit) {
						try {
							in.close();
							connection.close();
						} catch (IOException e) {
						}
						out.close();
						
						synchronized(EchoServer.users){
							EchoServer.users.remove(this);
						}
						return;
					}
				}
			}
		}
	}
}
