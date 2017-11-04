import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class GUI {
	
	private JTextArea newsContent = new JTextArea();
	private DefaultListModel<String> results = new DefaultListModel<>();
	private JList<String> titleList = new JList<String>(results);
	private HashMap<String, Result> hashResults = new HashMap<>();
	private JPanel mainPanel;
	
	private Server server;
	private Client client;
	private JFrame frame;

	private boolean pressed = false;
	private Border border = BorderFactory.createLineBorder(Color.black, 1);

	
	public GUI(Server server, Client client){
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
	}
	
	public void launch(){
		frame.setVisible(true);
	}

	private void searchBar() {
		
		JPanel panelNORTH = new JPanel();
		panelNORTH.setLayout(new FlowLayout());
		frame.add(panelNORTH,BorderLayout.NORTH);
		
		JTextField searchField = new JTextField("write something here...");
		searchField.setSize(200, 24);

		panelNORTH.add(searchField);
		JButton searchButton = new JButton("SEARCH");
		panelNORTH.add(searchButton);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!pressed){
					mainPanel();
				}
				pressed = true;
				client = new Client(server, searchField.getText());
				client.start();
				titleList();
				System.out.println("Text=" + searchField.getText());
	      }
	    });
	}
	
	
	private void addFrameContent() {
		searchBar();
	}

	private void mainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,0));
		
		frame.add(mainPanel, BorderLayout.CENTER);
		
	}

	private void titleList() {
		results.clear();	
		hashResults.clear();
		for(Result result : server.getSuccessfullSearchNews()){
			results.addElement(result.getNumberOfOccurrences() + " - " + server.getNewsWithId(result.getNewsId()).getTitle());
			hashResults.put(result.getNumberOfOccurrences() + " - " + server.getNewsWithId(result.getNewsId()).getTitle(), result);
		}
		
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	           String selectedItem =  titleList.getSelectedValue();
	           addTextArea(selectedItem);
	         }
	    };
	    titleList.setBorder(border);
	    mainPanel.add(titleList, BorderLayout.EAST);
	    newsContent("");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		titleList.addMouseListener(mouseListener);
		
	}
	
	private void newsContent(String content) {
		newsContent.setText(content);
		newsContent.setEnabled(false);
		newsContent.setLineWrap(true);
		newsContent.setBorder(border);
		mainPanel.add(newsContent, BorderLayout.WEST);

	}
	private void addTextArea( String content) {
		content = server.getNewsWithId(hashResults.get(content).getNewsId()).getContent();
		newsContent(content);
	}
}
