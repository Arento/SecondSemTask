package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	static String name;
	static SocketWriter out;
	static SocketReader in;
	static Socket socket;

	public static void start(String[] args) throws Exception {
		name = args[0];
		socket = null;
		try {
			socket = new Socket(args[2], Integer.parseInt(args[1]));
			out = new SocketWriter(socket);
			in = new SocketReader(socket);

			out.write(new Message("name " + name));
			in.start();

		} catch (Exception e1) {
			System.out.println("Socket falls on initialization");
			Exception e = null;
			throw e;

		}

	}

	public static void close() throws IOException {
		out.write(new Message(1));
		synchronized (socket) {
			socket.close();
		}
	}

}
