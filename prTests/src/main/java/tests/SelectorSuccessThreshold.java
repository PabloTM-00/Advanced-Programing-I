package tests;

import java.util.Set;

public class SelectorSuccessThreshold implements Selector{

	private final int minimumRate;
	
	public SelectorSuccessThreshold(int minimumRate) {
		if(minimumRate < 0) throw new AppException("Minimum success rate cant be negative");
		
		this.minimumRate = minimumRate;
	}
	
	public int getMinimumRate() {
		return minimumRate;
	}

	@Override
	public boolean isSelectable(Set<Exercise> set) {
		if(set == null || set.isEmpty()) return false;

		int totSuc = 0;
		int totEx = 0;
		
		for(Exercise e : set) {
			totSuc += e.getSuccessful();
			totEx += e.getExecuted();
		}
		
		if(totEx == 0) return false; // Stops without having to catch NumberFormatException and directly returns false
		
		int rate = (100 * totSuc) / totEx;
		
		if(rate > minimumRate) return true;
		
		return false;
	}

}
