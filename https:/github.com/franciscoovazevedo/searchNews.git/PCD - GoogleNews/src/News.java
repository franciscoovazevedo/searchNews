

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class News {
	private int id;
	private String title;
	private String content;
	private Scanner scanner;
	
	public News(File file, int id) throws FileNotFoundException {
		this.id = id;
		scanner = new Scanner(file);
		if(scanner.hasNextLine())
			title = scanner.nextLine();
		while(scanner.hasNextLine())
			content = scanner.nextLine();
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
