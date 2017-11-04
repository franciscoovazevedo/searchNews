import java.io.FileNotFoundException;

public class tester {
	
	public static void main(String[] args) throws FileNotFoundException {
		Server serve = new Server();
		Client client = new Client(serve, "Marcelo Rebelo");
		GUI GUIinterface = new GUI(serve, client);
//		client.start();
		GUIinterface.launch();
		
//		for (Result result : serve.getSuccessfullSearchNews()) {
//			System.out.println("In the news " + serve.getNews().get(result.getNewsId()).getTitle() +
//					" the word " +  client.getWord() + " occurred " + result.getNumberOfOccurrences() + " times!");
//		}
		
	}
	
}
