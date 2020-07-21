package sample.save;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSave {
	private static List<String> getLastNameList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("Last Names.txt"));
        List<String> lNameList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lNameList.add(line);
        }
        return lNameList;
    }

    private static List<String> getFirstNameList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("First Names.txt"));
        List<String> fNameList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            fNameList.add(line);
        }
        return fNameList;
    }

    public static List<String> getNameList() throws FileNotFoundException {
        List<String> firstNameList = getFirstNameList();
        List<String> lastNameList = getLastNameList();
        int min;
        if (firstNameList.size() > lastNameList.size()) {
            min = lastNameList.size() - 1;
        } else {
            min = firstNameList.size() - 1;
        }
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < min; i++) {
            String name = firstNameList.get(i) + " " + lastNameList.get(i);
            nameList.add(name);
        }
        return nameList;
    }
}
