
public class Client {
	private String word;
	private Server server;
	
	public Client(Server server, String word){
		this.server = server;
		this.word = word;
	}
	
	public void start() {
		server.assignWork(word);
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}	
}
