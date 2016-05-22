package client;

import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	static String name;
	static SocketWriter out;
	static SocketReader  in;
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

		out = new SocketWriter(socket);
		in = new SocketReader(socket);

		out.write(new Message("name "+name));
		in.start();
	}
}
