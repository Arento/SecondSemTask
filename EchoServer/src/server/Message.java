package server;

public class Message {
	enum MessageType {
		Name, Text, Exit, Unknown;
	}

	MessageType type;
	String text;
	String author;

	public Message(String rawMessage) {
		String[] message = rawMessage.split(" ");
		if (message[0].equals("name")) {
			type = MessageType.Name;
			author = message[1];
		} else if (message[0].equals("text")) {
			type = MessageType.Text;
			text = message[1];
			author = message[2];
		} else if (message[0].equals("exit")) {
			type = MessageType.Exit;
			author = message[1];
		} else {
			type = MessageType.Unknown;
		}
	}

	public String toString() {
		if (type == MessageType.Name) {
			return "name " + author;
		} else if (type == MessageType.Text) {
			return "text " + text + " " + author;
		} else if (type == MessageType.Exit) {
			return "exit " + author;
		} else {
			return "unknown";
		}
	}
}
