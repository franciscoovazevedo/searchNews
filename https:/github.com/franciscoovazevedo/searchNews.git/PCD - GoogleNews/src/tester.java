import java.io.FileNotFoundException;

public class tester {
	
	public static void main(String[] args) throws FileNotFoundException {
		Server serve = new Server();
		
		serve.assignWork("fogos");
		
		for (Result result : serve.getSuccessfullSearchNews()) {
			System.out.println("In the news " + serve.getNews().get(result.getNewsId()).getTitle() +
					" the word fogos occurred " + result.getNumberOfOccurrences() + " times!");
		}
		
	}
	
}
