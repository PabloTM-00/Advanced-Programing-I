package regatta;

import java.util.Comparator;

public class SatShip implements Comparator<Ship> {
	
	@Override
	public int compare(Ship o1, Ship o2) {
		Position p = new Position(0,0);
		
		int result = (int) Double.compare((o1.getPosition().distance(p)), o2.getPosition().distance(p));
		
		if(result != 0) return result;
		
		return o1.compareTo(o2);
	}

}
