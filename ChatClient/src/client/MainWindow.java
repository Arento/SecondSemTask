package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea messageArea;
	JTextField textField;

	public MainWindow() {
		textField = new JTextField();
		textField.setEnabled(false);
		setTitle("Chat Client");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JTextField nameField = new JTextField(16);
		JTextField ipField = new JTextField(16);
		JTextField portField = new JTextField(5);
		JButton connect = new JButton("Connect");
		connect.setMnemonic(1);
		connect.setActionCommand("connect");
		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fjfj");
				try {
					Client.start(new String[] { nameField.getText(), portField.getText(), ipField.getText() });
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		nameField.setText("");
		ipField.setText("***.***.***.***");
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(4, 2));
		northPanel.add(new JLabel("Username: ", SwingConstants.RIGHT));
		northPanel.add(nameField);
		northPanel.add(new JLabel("Hostname: ", SwingConstants.RIGHT));
		northPanel.add(ipField);
		northPanel.add(new JLabel("Port: ", SwingConstants.RIGHT));
		northPanel.add(portField);
		northPanel.add(connect);
		add(northPanel, BorderLayout.NORTH);
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		messageArea = new JTextArea();
		messageArea.setLineWrap(true);
		messageArea.setEditable(false);

		JScrollPane areaScroll = new JScrollPane(messageArea);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Message message = new Message("text " + textField.getText() + Client.name);
						Client.out.write(message);
						textField.setText("");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		southPanel.add(areaScroll, BorderLayout.CENTER);
		southPanel.add(textField, BorderLayout.SOUTH);
		add(southPanel);
	}
}
