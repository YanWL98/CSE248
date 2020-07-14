package p1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException {
		StringBuilder books = new StringBuilder();
		StringBuilder patrons = new StringBuilder();
		//ArrayList<Book> books = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Book b = new Book();
			books.append(b).append("\n");
		}
		
		//ArrayList<Patron> patrons = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Patron p = new Patron();
			patrons.append(p).append("\n");
		}
		
		try {
			FileOutputStream out1 = new FileOutputStream("books.txt");
			FileOutputStream out2 = new FileOutputStream("patrons.txt");
			out1.write(books.toString().getBytes());
			out1.flush();
			out1.close();
			out2.write(patrons.toString().getBytes());
			out2.flush();
			out2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
