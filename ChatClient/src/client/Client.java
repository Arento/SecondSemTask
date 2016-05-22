package client;

import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	static String name;

	public static void start(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Give me three arguments: name, port, host address");
		}
		name = args[0];
		Socket socket = null;
		try {
			socket = new Socket(args[2],Integer.parseInt(args[1]));
		} catch (UnknownHostException e1) {
			System.out.println("socket unknown host");
			System.exit(-1);
		}

		SocketWriter out = new SocketWriter(socket);
		SocketReader in = new SocketReader(socket);

		out.start();
		in.start();
	}
}
