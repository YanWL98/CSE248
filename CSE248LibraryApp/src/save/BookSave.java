package sample.save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;

import sample.Book;


public class BookSave {

    public static String getPrice() {
        double min = 0.00;
        double max = 99.99;
        double randomPrice = (double) (Math.random() * (max - min + 1) + min);
        String temp = String.format("%.2f", randomPrice);
        return temp;
    }

    public static List<String> getTitleList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("textbook_titles.txt"));
        List<String> titleList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            titleList.add(line);
        }
        return titleList;
    }

    public static List<String> getISBNList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("textbook_isbns.txt"));
        List<String> ISBNList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ISBNList.add(line);
        }
        return ISBNList;
    }

    public static List<Book> getAllBooks() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("Books.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return JSONArray.parseArray(stringBuilder.toString(), Book.class);
    }

    public static void saveAllBook(List<Book> books) throws FileNotFoundException {
        String toJSON = JSONArray.toJSON(books).toString();
        FileWriter fw = null;
        File f = new File("Books.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(toJSON, 0, toJSON.length());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
