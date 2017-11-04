import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
//import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
// a principal class do programa. Parece robusta mas terá ainda que sofrer alterações com a parte concorrencial.
//
public class Server {
	private LinkedList<News> news = new LinkedList<>(); // repo 
	private LinkedList<Worker> workers = new LinkedList<>();
	private LinkedList<Result> successfullSearchNews = new LinkedList<>();
//	private HashMap<String, Result> hashResults = new HashMap<>();
	
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
		
		for(int i = 0; i < news.size() / 10; i++)   // criação de workers
			workers.add(new Worker());
	}
	
	public LinkedList<News> getNews() {
		return news;
	}
	
	public LinkedList<Worker> getWorkers() {
		return workers;
	}
	
	
	public void assignWork(String word){
		successfullSearchNews.clear();
		for (News news : news) {  // assign workers
			int random = new Random().nextInt(workers.size());
			workers.get(random).assignTask(new Task(word, news));
		}
		for (Worker worker : workers ) { // distribui trabalho pelos workers
			successfullSearchNews.addAll(worker.workListOfTasks());
		}

	}
	
	public LinkedList<Result> getSuccessfullSearchNews() {
		organizeResults();
		return successfullSearchNews;
	}
	
	
	public News getNewsWithId(int id){
		return news.get(id); 
	}

	
	// organiza os resultados por numero de occorencias
	private void organizeResults() {
		successfullSearchNews.sort(new Comparator<Result>() {
			@Override
	        public int compare(Result r1, Result r2) {
				if(r1.getNumberOfOccurrences() < r2.getNumberOfOccurrences()){
		            return 1;
		        } else {
		            return -1;
		        }
	        }
		});
	}
	
//	public HashMap<String, Result> resultsHash(){
//		for(Result result : successfullSearchNews){
//			hashResults.put(result.getNumberOfOccurrences() + " - " + getNewsWithId(result.getNewsId()).getTitle(), result);
//		}
//		return hashResults;
//		
//	}
}
