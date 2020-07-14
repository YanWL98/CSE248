package p1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Patron {
	public String name;
	public String id;
	public String phone;
	
	private static int idCounter = 0;
	
	

	public Patron(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
		this.id = String.valueOf(++idCounter);
	}


	public Patron() throws FileNotFoundException {
		super();
		Name a = new Name();
		this.name = a.getName();
		this.phone = setPhone();
		this.id = String.valueOf(++idCounter);
	}


	public String getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public String setPhone() {
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


	@Override
	public String toString() {
		return "Patron [name=" + name + ", id=" + id + ", phone=" + phone + "]";
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		ArrayList<Patron> p = new ArrayList<>();
//		for (int i = 0; i < 10; i++) {
//			Patron b = new Patron();
//			p.add(b);
//		}
//		System.out.println(p);
//
//	}
	
}
