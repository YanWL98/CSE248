package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Book {
	
	private String title;
	private String ISBN;
	private String author;
	private String price;
	private Boolean isReturned;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getReturned() {
		return isReturned;
	}

	public void setReturned(Boolean returned) {
		isReturned = returned;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", ISBN=" + ISBN + ", author=" + author + ", price=" + price + ", isReturned="
				+ isReturned + "]";
	}


	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Book> books = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Book b = new Book();
			books.add(b);

		}
		System.out.println(books);

	}


}

