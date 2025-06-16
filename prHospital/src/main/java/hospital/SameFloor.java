package hospital;

public class SameFloor implements Criterion{

	private int floor;
	
	public SameFloor(int floor) {
		super();
		this.floor = floor;
	}

	@Override
	public boolean meetsCondition(Patient p, Room r) {
		if(r.getFloor() == this.floor) return true;
		
		else return false;
	}

}
