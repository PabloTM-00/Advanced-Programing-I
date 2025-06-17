package prScooters;

public class SelectionPosition implements Selection{

	private Position positionRef;
	private double maxDistance;

	public SelectionPosition(Position positionRef, double maxDistance) {
		super();
		this.positionRef = positionRef;
		this.maxDistance = maxDistance;
	}
	
	@Override
	public boolean select(Scooter s) {
		if(s.getPosition().distance(positionRef) < maxDistance) return true;
		
		return false;
	}
	
	

}
