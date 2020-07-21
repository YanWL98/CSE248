package sample.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import sample.Administration;
import sample.Book;
import sample.Patron;
import sample.ReservedRecord;
import sample.TransactionRecord;
import sample.save.AdministrationSave;
import sample.save.AlertBox;
import sample.save.BookSave;
import sample.save.PatronSave;
import sample.save.ReservedSave;
import sample.save.TransactionSave;
import sample.save.UserSave;

public class Controller {

	public void initBooks() throws FileNotFoundException {
		List<String> isbnList = BookSave.getISBNList();
		List<String> titleList = BookSave.getTitleList();
		List<String> nameList = UserSave.getNameList();
		int min = Math.min(Math.min(isbnList.size(), titleList.size()), nameList.size());
		List<Book> books = new ArrayList<>();
		for (int i = 0; i < min - 1; i++) {
			Book book = new Book();
			book.setTitle(titleList.get(i));
			book.setISBN(isbnList.get(i));
			book.setAuthor(nameList.get(i));
			book.setPrice(BookSave.getPrice());
			book.setReturned(false);
			books.add(book);
		}
		String json = JSONArray.toJSON(books).toString();
		FileWriter fw;
		File f = new File("Books.txt");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(json, 0, json.length());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Book> getBooks() {
		try {
			return BookSave.getAllBooks();
		} catch (FileNotFoundException e) {
			AlertBox.display("Tips", "Failed to get all book!");
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkUser(String name, String password) {
		List<Patron> patrons = qryAllPatrons();
		for (Patron patron : patrons) {
			String patronName = patron.getName();
			String pswd = patronName + patron.getId();
			if (name.equals(patronName) && password.equals(pswd)) {
				return true;
			}
		}
		return false;
	}

	public void bookingBook(Book book, Patron patron) {
		List<ReservedRecord> allReserveds = ReservedSave.getAllReserveds();
		ReservedRecord reservedRecord = new ReservedRecord();
		reservedRecord.setId(ReservedSave.getmaxId() + 1);
		reservedRecord.setIsbn(book.getISBN());
		reservedRecord.setPatronId(patron.getId());
		allReserveds.add(reservedRecord);
		try {
			ReservedSave.saveAllReserved(allReserveds);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AlertBox.display("Tips", "SUCCEED!");
	}

	public void borrowBook(Book book, Patron patron) {
		if (!book.getReturned()) {

			List<TransactionRecord> transactionRecords = TransactionSave.getAllTransactions();
			TransactionRecord transactionRecord = new TransactionRecord();
			transactionRecord.setId(TransactionSave.getmaxId() + 1);
			transactionRecord.setIsbn(book.getISBN());
			transactionRecord.setPatronId(patron.getId());
			transactionRecord.setCreateDate(new Date().toString());
			transactionRecord.setIsReturn(0);
			transactionRecords.add(transactionRecord);
			try {
				TransactionSave.saveAllTransactions(transactionRecords);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				List<Book> allBooks = BookSave.getAllBooks();
				for (Book allBook : allBooks) {
					if (book.getISBN().equals(allBook.getISBN())) {
						allBook.setReturned(!book.getReturned());
						break;
					}
				}
				BookSave.saveAllBook(allBooks);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			AlertBox.display("Tips", "SUCCEED!");
		} else {
			AlertBox.display("Tips", "Already Borrowed!");
		}
	}
	
	public void returnBook(TransactionRecord transactionRecord) throws FileNotFoundException {

		String isbn = transactionRecord.getIsbn();
		List<Book> allBooks = BookSave.getAllBooks();
		for (Book allBook : allBooks) {
			if (isbn.equals(allBook.getISBN())) {
				allBook.setReturned(false);
			}
		}
		BookSave.saveAllBook(allBooks);

		List<TransactionRecord> allTransactions = TransactionSave.getAllTransactions();
		Iterator<TransactionRecord> it = allTransactions.iterator();
		while (it.hasNext()) {
			TransactionRecord transaction = it.next();
			if (transaction.getId().equals(transactionRecord.getId())) {
				it.remove();
			}
		}
		TransactionSave.saveAllTransactions(allTransactions);
		AlertBox.display("Tips", "SUCCEED");
	}

	public List<ReservedRecord> getAllReserveds(Patron patron) {
		List<ReservedRecord> allReserveds = ReservedSave.getAllReserveds();
		List<ReservedRecord> result = new ArrayList<>();
		if (null != allReserveds && allReserveds.size() > 0) {
			for (ReservedRecord allReserved : allReserveds) {
				if (allReserved.getPatronId().equals(patron.getId())) {
					result.add(allReserved);
				}
			}
			return result;
		} else {
			return result;
		}
	}

	public List<TransactionRecord> getAllTransactions(Patron patron) {
		List<TransactionRecord> allTransactions = TransactionSave.getAllTransactions();
		List<TransactionRecord> result = new ArrayList<>();
		if (null != allTransactions && allTransactions.size() > 0) {
			for (TransactionRecord transactionRecord : allTransactions) {
				if (transactionRecord.getPatronId().equals(patron.getId())) {
					result.add(transactionRecord);
				}
			}
			return result;
		} else {
			return result;
		}
	}

	

	public List<Patron> qryAllPatrons() {
		List<Patron> patrons = new ArrayList<>();
		try {
			patrons = PatronSave.getAllPartons();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return patrons;
	}

	public void addBook(String str, Patron patron) throws FileNotFoundException {
		Book book = (Book) JSONObject.parse(str);
		List<Administration> administrations = AdministrationSave.getAllAdministrations();
		for (Administration administration : administrations) {
			if (patron.getId().equals(administration.getId()) && patron.getName().equals(administration.getName())) {
				List<Book> allBooks = BookSave.getAllBooks();
				allBooks.add(book);
				BookSave.saveAllBook(allBooks);
				AlertBox.display("Tips", "SUCCEED");
				break;
			} else {
				AlertBox.display("Tips", "Only administrators can add");
			}
		}
	}

	public void updateBook(Book book) throws FileNotFoundException {
		List<Book> allBooks = BookSave.getAllBooks();
		for (Book allBook : allBooks) {
			if (allBook.getISBN().equals(book.getISBN())) {
				allBook.setReturned(book.getReturned());
				allBook.setPrice(book.getPrice());
				allBook.setAuthor(book.getAuthor());
				allBook.setTitle(book.getTitle());
			}
		}
		BookSave.saveAllBook(allBooks);
	}

	public void delBook(String str, Patron patron) throws FileNotFoundException {
		if (null != str && str.length() > 0) {
			List<Administration> administrations = AdministrationSave.getAllAdministrations();
			for (Administration administration : administrations) {
				if (patron.getId().equals(administration.getId())
						&& patron.getName().equals(administration.getName())) {
					List<Book> allBooks = BookSave.getAllBooks();
					Iterator<Book> iterator = allBooks.iterator();
					while (iterator.hasNext()) {
						Book next = iterator.next();
						if (str.equals(next.getISBN())) {
							iterator.remove();
						}
					}
					BookSave.saveAllBook(allBooks);
					AlertBox.display("Tips", "SUCCEED");
					break;
				} else {
					AlertBox.display("Tips", "Only administrators can delete");
				}
			}
		}
	}

	public Book getBookByISBN(String ISBN) throws FileNotFoundException {
		List<Book> allBooks = BookSave.getAllBooks();
		for (Book allBook : allBooks) {
			if (allBook.getISBN().equals(ISBN)) {
				return allBook;
			}
		}
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Controller controller = new Controller();

		controller.initBooks();
	}
}
