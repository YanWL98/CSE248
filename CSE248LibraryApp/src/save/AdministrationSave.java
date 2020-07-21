package sample.save;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;

import sample.Administration;


public class AdministrationSave {


    public static List<Administration> getAllAdministrations() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("Administration.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return JSONArray.parseArray(stringBuilder.toString(), Administration.class);
    }
}
