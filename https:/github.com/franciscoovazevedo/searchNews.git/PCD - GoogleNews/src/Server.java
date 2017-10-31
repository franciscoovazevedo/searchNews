import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
// a principal class do programa. Parece robusta mas terá ainda que sofrer alterações com a parte concorrencial.
//
public class Server {
	private LinkedList<News> news = new LinkedList<>();
	private LinkedList<Worker> workers = new LinkedList<>();
	private LinkedList<Result> successfullSearchNews = new LinkedList<>();
	
	public Server() throws FileNotFoundException{
		File dir = new File("news29out");
		File[] directoryListing = dir.listFiles();
  
		if (directoryListing != null) {
			for (File child : directoryListing) {
				News noticia = new News(child);
				news.add(noticia);
				noticia.setId(news.indexOf(noticia));
			}
		} 
		
		for(int i = 0; i < news.size() / 10; i++)
			workers.add(new Worker());
	}
	
	public LinkedList<News> getNews() {
		return news;
	}
	
	public LinkedList<Worker> getWorkers() {
		return workers;
	}
	
	
	public void assignWork(String word){

		for (News news : news) {
			int random = new Random().nextInt(workers.size());
			workers.get(random).assignTask(new Task(word, news));
		}
		for (Worker worker : workers ) {
			successfullSearchNews.addAll(worker.workListOfTasks());
		}

	}
	
	public LinkedList<Result> getSuccessfullSearchNews() {
		return successfullSearchNews;
	}
	
	
	public News getNewsWithId(int id){
		return news.get(id); 
	}

	
}
