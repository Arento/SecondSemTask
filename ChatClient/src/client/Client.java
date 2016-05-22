package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	static String name;

	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 10000);
		} catch (UnknownHostException e1) {
			System.out.println("socket unknown host");
			System.exit(-1);
		}

		SocketWriter out = new SocketWriter(socket);
		SocketReader in = new SocketReader(socket);

		out.start();
		in.start();
		while (true) {
			Thread.sleep(100);
		}
		// socket.close();
	}
}
