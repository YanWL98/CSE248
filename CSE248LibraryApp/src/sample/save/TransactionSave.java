package sample.save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;

import sample.TransactionRecord;


public class TransactionSave {
	 public static List<TransactionRecord> getAllTransactions(){
	        Scanner scanner = null;
	        try {
	            scanner = new Scanner(new FileInputStream("TransactionRecords.txt"));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            System.out.println("file not found!");
	        }
	        StringBuilder stringBuilder = new StringBuilder();
	        while (scanner.hasNextLine()) {
	            stringBuilder.append(scanner.nextLine());
	        }
	        return JSONArray.parseArray(stringBuilder.toString(), TransactionRecord.class);
	    }

	    public static void saveAllTransactions(List<TransactionRecord> transactionRecords) throws FileNotFoundException {
	        String toJSON = JSONArray.toJSON(transactionRecords).toString();
	        FileWriter fw = null;
	        File f = new File("TransactionRecords.txt");
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

	    public static int getmaxId(){
	        List<TransactionRecord> allTransactions = getAllTransactions();
	        int maxId = 0;
	        if (null != allTransactions && allTransactions.size() > 0) {
	            for (TransactionRecord transactionRecord : allTransactions) {
	                if (transactionRecord.getId() > maxId) {
	                    maxId = transactionRecord.getId();
	                }
	            }
	            return maxId;
	        }else {
	            return maxId;
	        }
	    }
}
