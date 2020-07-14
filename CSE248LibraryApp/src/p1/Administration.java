package p1;

public class Administration extends Patron{
	
	private int rank = 1;	

	public Administration(String name, String phone, int rank) {
		super(name, phone);
		this.rank = rank;
	}


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	

}
