import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUInterface {
	private Server server;
	private Client client;
	private JFrame frame;

	public GUInterface(Server server, Client client){
		this.server = server;
		this.client = client;
		launchInterface();
	}

	private void launchInterface() {
		frame = new JFrame("SearchNews - PCD");
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		addFrameContent();
		
		frame.pack();
		
		frame.setVisible(true);
	}

	private void addFrameContent() {
		JPanel panelNORTH = new JPanel();
		panelNORTH.setLayout(new GridLayout(1,0));
		frame.add(panelNORTH,BorderLayout.NORTH);
		
		JTextField searchField = new JTextField();
		panelNORTH.add(searchField);
		JButton searchButton = new JButton("SEARCH");
		panelNORTH.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	client.setWord(searchField.getText());
	    	
	        System.out.println("Text=" + searchField.getText());
	      }
	    });
		
		
		
		
		
	}
}
