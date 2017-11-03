// cada Task será uma thread!
public class Task {
	private String word;
	private News news;
	
	public Task(String word, News news){
		this.word = word;
		this.news = news;
		
	}
	
	public boolean existsWord(){
		return news.hasWord(word);
	}
	
	public int countsWords(){
		return news.howManyTimesWord(word);
	}
	
	public int getNewsId() {
		return news.getId();
	}
	
	
	@Override
	public String toString() {
		return "Task: find the word " + this.word + " in the news " + news.getTitle();
	}
}
