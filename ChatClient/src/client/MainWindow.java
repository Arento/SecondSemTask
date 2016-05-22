package client;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainWindow implements Runnable  {
	@Override
	public void run(){
		JFrame window = new JFrame("Chat Client");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout layout = new GridBagLayout();
		JButton connect = new JButton("Connect");
		JButton send = new JButton("send");
		JList<JLabel> messages = new JList<JLabel>();
 		window.setLayout(layout);
		window.add(new JTextField());
		window.add(new JTextField());
		window.add(new JTextField());
		window.pack();
	
		window.setVisible(true);
	}   
	public static void main(String args[]){
		MainWindow mw = new MainWindow();
		SwingUtilities.invokeLater(mw);
	}
}
