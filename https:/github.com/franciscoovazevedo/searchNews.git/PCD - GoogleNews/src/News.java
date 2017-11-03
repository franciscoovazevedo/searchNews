// mais uma classe que pode já estar finalizada. Não vemos onde poderemos alterar o comportamento 
// desta classe1

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class News {
	private int id;
	private String title;
	private String content;
	private Scanner scanner;
	
	public News(File file) throws FileNotFoundException {
		scanner = new Scanner(file);
		if(scanner.hasNextLine())
			title = scanner.nextLine();
		while(scanner.hasNextLine())
			content = scanner.nextLine();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean hasWord(String word) {
		if(title.toLowerCase().contains(word.toLowerCase()) || content.toLowerCase().contains(word.toLowerCase()))
			return true;
		
		return false;
	}
	
	public int howManyTimesWord(String word) {
		if(hasWord(word)){
			int counter = content.toLowerCase().split("\\b" + word.toLowerCase() + "\\b").length - 1;
			counter += title.toLowerCase().split("\\b" + word.toLowerCase() + "\\b").length - 1;
			return counter;
		}
		
		return 0;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getId() {
		return id;
	}
	

	
	
}
