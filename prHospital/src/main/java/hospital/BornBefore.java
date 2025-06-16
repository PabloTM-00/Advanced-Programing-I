package hospital;

public class BornBefore implements Criterion {

	private int year;
	
	public BornBefore(int year) {
		super();
		this.year = year;
	}

	@Override
	public boolean meetsCondition(Patient p, Room r) {
		if((p.getBirthYear() - year) < 0) return true;
		
		else return false;
	}
}
