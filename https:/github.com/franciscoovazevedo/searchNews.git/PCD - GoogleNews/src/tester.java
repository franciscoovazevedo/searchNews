import java.io.File;
import java.io.FileNotFoundException;

public class tester {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("Tue24Oct2017050015GMT.txt");
		News noticia = new News(file, 1);
		System.out.println(noticia.getTitle());
		System.out.println(noticia.getContent());
	}
	
}
