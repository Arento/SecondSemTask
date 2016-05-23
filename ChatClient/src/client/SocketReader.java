package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import client.Message;
import client.Message.MessageType;

public class SocketReader extends Thread {
	BufferedReader in;
	Socket socket;

	public SocketReader(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public void run(){
		String input = null;
		while(!socket.isClosed())
		{
			try {
				input = in.readLine();
				Message message = new Message(input);
				if(message.type == MessageType.Text)
					MainWindow.setMessage("<" + message.author + ">:" + message.text + "\n");
			} catch (IOException e) {
				break;
			}
		}
		
		
	}
}
