package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWriter extends Thread {
	PrintWriter out;
	BufferedReader in;

	public SocketWriter(Socket socket) throws IOException {
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	public void run() {
		String input = null;
		out.println("name " + Client.name);
		while (true) {
			try {
				input = in.readLine();
				if (input.equals("--exit--"))
					break;
				out.println("text " + input + " " + Client.name);

			} catch (IOException e) {
				System.out.println("Exception in SocketWriter");
			}
		}
		out.println("exit");
		try {
			in.close();
		} catch (IOException e) {
			System.out.println("Exception in SocketWriter");
		}
		out.close();
		return;
	}
}
