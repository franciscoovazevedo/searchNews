import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class GUI {
	
	// SWING COMPONENTS
	private JFrame frame;
	private JTextArea newsContent = new JTextArea();
	private DefaultListModel<String> results = new DefaultListModel<>();
	private JList<String> titleList = new JList<String>();
	private JPanel mainPanel;
	private JScrollPane scrollPane = new JScrollPane();
	private Border border = BorderFactory.createLineBorder(Color.black, 1);
	private String searchedWord;
	
	
	// Client and Server
	private Server server;
	private Client client;
	
	// variaveis usasdas para "trabalhar" nalguns algoritmos
	private HashMap<String, Result> hashResults = new HashMap<>();
	private boolean pressed = false;

	
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

	private void searchBar() { // TextField e Button. Maximiza a janela após a primeira pesquisa
		
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
				
				// first search
				if(!pressed){
					mainPanel();
					pressed = true;
				}
				searchedWord = searchField.getText();
				client = new Client(server, searchedWord);
				client.start();
				titleList();
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
		//lista de titulos - se tiver vazia lança aviso sonoro com JOptionPane a informar do erro
		results.clear();	
		hashResults.clear();
		for(Result result : server.getSuccessfullSearchNews()){
			results.addElement(result.getNumberOfOccurrences() + " - " + server.getNewsWithId(result.getNewsId()).getTitle());
			hashResults.put(result.getNumberOfOccurrences() + " - " + server.getNewsWithId(result.getNewsId()).getTitle(), result);
		}
		
		if(results.isEmpty()){ 
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "Word " + searchedWord + " not found in the news DB");
		}else{
			titleList.setModel(results);;
		}
		
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	           searchAction();
	         }
	    };
	    
	    titleList.setBorder(border);
	    scrollPane.setViewportView(titleList);
	    mainPanel.add(scrollPane, BorderLayout.EAST);
	    newsContent("Select the news you want to read...");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // MAXIMIZA A JANELA
		
		titleList.addMouseListener(mouseListener);
	}
	
	private void searchAction(){
		String selectedItem =  titleList.getSelectedValue();
        addTextArea(selectedItem);
	}
	private void newsContent(String content) {
		// Ainda falta a scroll bar!!! 
		newsContent.setText(content);
		newsContent.setEnabled(false);
		newsContent.setLineWrap(true);
		newsContent.setWrapStyleWord(true);
		newsContent.setBorder(border);
//		scrollPane.setViewportView(newsContent);
//		System.out.println(newsContent.getText());
		mainPanel.add(newsContent, BorderLayout.WEST);
		
	}
	private void addTextArea( String selectedItem) {
		selectedItem = server.getNewsWithId(hashResults.get(selectedItem).getNewsId()).getContent();
		newsContent(selectedItem);
		highLightWord(); 
	}
	
	private void highLightWord(){
		// sublinha as palavras a amarelo. ainda falta resolver o problema
		// com as palavras coladas a pontuação.
		

		Highlighter highlighter = newsContent.getHighlighter();
	    HighlightPainter painter = 
	             new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
	    String word = client.getWord();
		int length = word.length();
		List<String> list = Arrays.asList(newsContent.getText().split(" "));
		ArrayList<Integer> indexes = new ArrayList<>();
		int currentIndex = 0;
	
		for (String string : list) {
			if(string.toLowerCase().equals(( word.toLowerCase()  )))
				indexes.add(currentIndex);
			currentIndex += string.length() + 1;
		}
		
		for (Integer integer : indexes) {
			int limit = integer + length;
			try {
				highlighter.addHighlight(integer, limit, painter);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
	}
}
