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

import sample.ReservedRecord;


public class ReservedSave {
	public static List<ReservedRecord> getAllReserveds(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("ReservedRecords.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("file not found!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return JSONArray.parseArray(stringBuilder.toString(), ReservedRecord.class);
    }

    public static void saveAllReserved(List<ReservedRecord> reservedRecords) throws FileNotFoundException {
        String toJSON = JSONArray.toJSON(reservedRecords).toString();
        FileWriter fw = null;
        File f = new File("ReservedRecords.txt");
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
        List<ReservedRecord> allReserveds = getAllReserveds();
        int maxId = 0;
        if (null != allReserveds && allReserveds.size() > 0) {
            for (ReservedRecord allReserved : allReserveds) {
                if (allReserved.getId() > maxId) {
                    maxId = allReserved.getId();
                }
            }
            return maxId;
        }else {
            return maxId;
        }
    }
}
