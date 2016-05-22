package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWriter {
	PrintWriter out;

	public SocketWriter(Socket socket) throws IOException {
		out = new PrintWriter(socket.getOutputStream(), true);
	}

	public void write(Message message) {
		try {
			out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
