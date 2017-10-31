import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Server {
	private LinkedList<News> news = new LinkedList<>();

	
	public Server() throws FileNotFoundException{
		File dir = new File("news29out");
		File[] directoryListing = dir.listFiles();
  
		if (directoryListing != null) {
			for (File child : directoryListing) {
				news.add(new News(child, news.size() + 1));
			}
		} 
	}
	
	public LinkedList<News> getNews() {
		return news;
	}
}