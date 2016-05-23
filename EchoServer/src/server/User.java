package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

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
		try {
			connection.setKeepAlive(true);
		} catch (SocketException e2) {
			System.exit(1);
		}
		while (!connection.isClosed() && connection.isConnected() && !connection.isInputShutdown()) {
			try {
				message = in.readLine();
				if (message != null) {
					Message formattedMessage = new Message(message);
					if (formattedMessage.type != MessageType.Unknown) {
						for (int i = 0; i < EchoServer.users.size(); i++) {
							EchoServer.users.get(i).send(message);
						}
					}
				}
			} catch (Exception e) {
				try {
					close();
				} catch (Exception e1) {
					System.exit(-1);
				}
				break;
			}
		}
	}

	public void close() throws Exception {
		in.close();
		out.close();
		connection.close();
		synchronized (EchoServer.users) {
			EchoServer.users.remove(this);
		}

	}
}
