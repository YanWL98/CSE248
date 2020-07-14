package p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
	
	private int countTile = 0;
	private String title;
	private String ISBN;
	private String author;
	private String price;
	private Boolean isReturned = false;
	
	
	public Book() throws FileNotFoundException {
		super();
		this.title = setTitle();
		this.ISBN = setISBN();
		Name a = new Name();
		this.author = a.getName();
		this.price = setPrice();
		this.isReturned = isReturned();
	}

	public String getTitle() {
		return title;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public String getPrice() {
		return price;
	}
	
	public Boolean isReturned() {
		return isReturned;		
	}
	
	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

	private String setPrice() {
		double min = 0.00;
		double max = 99.99;
		double randomPrice = (double) (Math.random() * (max - min + 1) + min);
		String temp = String.format("%.2f", randomPrice);
		return temp;
	}
	
	private String setTitle() throws FileNotFoundException {
		String temp;
		Scanner scanner = new Scanner(new FileInputStream("textbook_titles.txt"));
		ArrayList<String> titleList = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			countTile++;
			titleList.add(line);
		}
		int min = 0;
		int max = countTile;
		int random = (int) (Math.random() * (max - min + 1) + min);
		temp = titleList.get(random).toString();
		return temp;
	}

	private String setISBN() throws FileNotFoundException {
		int count = 0;
		String temp;
		Scanner scanner = new Scanner(new FileInputStream("textbook_isbns.txt"));
		ArrayList<String> ISBNList = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			count++;
			ISBNList.add(line);
		}
		int min = 0;
		int max = count;
		int random = (int) (Math.random() * (max - min + 1) + min);
		temp = ISBNList.get(random).toString();
		return temp;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", ISBN=" + ISBN + ", author=" + author + ", price=" + price + ", isReturned="
				+ isReturned + "]";
	}
	

//	public static void main(String[] args) throws FileNotFoundException {
//		ArrayList<Book> books = new ArrayList<>();
//		for (int i = 0; i < 10; i++) {
//			Book b = new Book();
//			books.add(b);
//		}
//		System.out.println(books);
//
//	}


}
