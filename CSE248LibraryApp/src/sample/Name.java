<<<<<<< HEAD:CSE248LibraryApp/src/sample/Name.java
package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Name {
	
	private String firstName;
	private String lastName;
	private String name;	
	
	public Name() throws FileNotFoundException {
		super();
		this.firstName = setFirstName();
		this.lastName = setLastName();
		this.name = setName();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}	
	
	public String getName(){		
		return name;		
	}
	
	private String setName() {
		name = firstName + " " + lastName;
		return name;
	}

	private String setLastName() throws FileNotFoundException{
		int count = 0;
		String temp;
		Scanner scanner = new Scanner(new FileInputStream("Last Names.txt"));
		ArrayList<String> lNameList = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			count++;
			lNameList.add(line);
		}
		int min = 0;
		int max = count;
		int random = (int) (Math.random() * (max - min + 1) + min);
		temp = lNameList.get(random).toString();
		return temp;
	}
	
	private String setFirstName() throws FileNotFoundException{
		int count = 0;
		String temp;
		Scanner scanner = new Scanner(new FileInputStream("First Names.txt"));
		ArrayList<String> fNameList = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			count++;
			fNameList.add(line);
		}
		int min = 0;
		int max = count;
		int random = (int) (Math.random() * (max - min + 1) + min);
		temp = fNameList.get(random).toString();
		return temp;
	}
		
	


	@Override
	public String toString() {
		return "name=" + name ;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Name> names = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Name a = new Name();
			names.add(a);
		}
		System.out.println(names);

	}
	
}

=======
package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Name {
	
	private String firstName;
	private String lastName;
	private String name;	
	
	public Name() throws FileNotFoundException {
		super();
		this.firstName = setFirstName();
		this.lastName = setLastName();
		this.name = setName();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}	
	
	public String getName(){		
		return name;		
	}
	
	private String setName() {
		name = firstName + " " + lastName;
		return name;
	}

	private String setLastName() throws FileNotFoundException{
		int count = 0;
		String temp;
		Scanner scanner = new Scanner(new FileInputStream("Last Names.txt"));
		ArrayList<String> lNameList = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			count++;
			lNameList.add(line);
		}
		int min = 0;
		int max = count;
		int random = (int) (Math.random() * (max - min + 1) + min);
		temp = lNameList.get(random).toString();
		return temp;
	}
	
	private String setFirstName() throws FileNotFoundException{
		int count = 0;
		String temp;
		Scanner scanner = new Scanner(new FileInputStream("First Names.txt"));
		ArrayList<String> fNameList = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			count++;
			fNameList.add(line);
		}
		int min = 0;
		int max = count;
		int random = (int) (Math.random() * (max - min + 1) + min);
		temp = fNameList.get(random).toString();
		return temp;
	}
		
	


	@Override
	public String toString() {
		return "name=" + name ;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Name> names = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Name a = new Name();
			names.add(a);
		}
		System.out.println(names);

	}
	
}

>>>>>>> 8b43c08878d7e1fae1b3e48719cf58391da893d3:CSE248LibraryApp/src/p1/Name.java
