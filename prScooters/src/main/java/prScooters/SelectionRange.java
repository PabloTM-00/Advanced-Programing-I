package prScooters;

public class SelectionRange implements Selection{

	private double threshold;
	
	public SelectionRange(double threshold) {
		this.threshold = threshold;
	}

	@Override
	public boolean select(Scooter s) {
		if(s.getRange() < threshold) return true;
		
		return false;
	}

}
