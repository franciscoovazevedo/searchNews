// A classe result é o que o Client recebe. Consiste numa class simples mas que permite juntar a ID 
// da noticia que continha a palavra dada pelo cliente e o número de vezes que essa palavra occorre
public class Result {
	private int newsId;
	private int numberOfOccurrences;
	
	public Result(int newsId, int numberOfOccurrences){
		this.newsId = newsId;
		this.numberOfOccurrences = numberOfOccurrences;
	}
	
	public int getNewsId() {
		return newsId;
	}

	public int getNumberOfOccurrences() {
		return numberOfOccurrences;
	}
	
	
	
}	
