package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Administration extends Patron{
	
	private int rank = 1;	

	public Administration(String name, String phone, int rank) {
		this.rank = rank;
	}


	public Administration() throws FileNotFoundException{
		
	}


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
	@Override
	public String toString() {
		return "Administration [name=" + name + ", id=" + id + ", phone=" + phone + ", rank=" + rank + "]";
	}


	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Administration> Administration = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Administration b = new Administration();
			Administration.add(b);
		}
		System.out.println(Administration);

	}

}