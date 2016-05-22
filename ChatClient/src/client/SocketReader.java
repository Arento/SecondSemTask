package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import client.Message;
import client.Message.MessageType;

public class SocketReader extends Thread {
	BufferedReader in;

	public SocketReader(Socket socket) throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public void run(){
		String input = null;
		while(true)
		{
			try {
				input = in.readLine();
				if(input.equals("--end--"))
					break;
				Message message = new Message(input);
				if(message.type == MessageType.Text)
					System.out.println("<" + message.author + ">:" + message.text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
