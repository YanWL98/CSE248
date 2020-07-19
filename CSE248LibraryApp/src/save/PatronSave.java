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

import sample.Patron;

public class PatronSave {
	public static String setPhone() {
        String temp = null;
        int min = 100;
        int max1 = 999;
        int randomNumberA = (int) (Math.random() * (max1 - min + 1) + min);
        int randomNumberB = (int) (Math.random() * (max1 - min + 1) + min);
        String a = String.valueOf(randomNumberA);
        String b = String.valueOf(randomNumberB);

        int min2 = 1000;
        int max2 = 9999;
        int randomNumberC = (int) (Math.random() * (max2 - min2 + 1) + min2);
        String c = String.valueOf(randomNumberC);
        return temp = a + "-" + b + "-" + c;
    }

    public static List<Patron> getAllPartons() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("Patrons.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return JSONArray.parseArray(stringBuilder.toString(), Patron.class);
    }

    private static void onlyRunSaveAllPatron() {
        List<Patron> patrons = new ArrayList<>();
        List<String> nameList = null;
        try {
            nameList = UserSave.getNameList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PatronSave patronSave = new PatronSave();
        String id = String.valueOf(patronSave.getPatronMAXId());
        for (String name : nameList) {
            Patron patron = new Patron();
            patron.setId(id);
            patron.setPhone(setPhone());
            patron.setName(name);
            patrons.add(patron);
            int i = Integer.parseInt(id);
            i++;
            id = String.valueOf(i);
        }
        String toJSON = JSONArray.toJSON(patrons).toString();
        FileWriter fw = null;
        File f = new File("Patrons.txt");
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

    private int getPatronMAXId() {
        List<Patron> allPartons = null;
        try {
            allPartons = getAllPartons();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int Max = 0;
        if (null != allPartons && allPartons.size() > 0) {
            for (Patron allParton : allPartons) {
                if (Max < Integer.parseInt(allParton.getId())) {
                    Max = Integer.parseInt(allParton.getId());
                }
            }
            return Max + 1;
        }
        return Max + 1;
    }

    public static void main(String[] args) {
        onlyRunSaveAllPatron();
    }
}
