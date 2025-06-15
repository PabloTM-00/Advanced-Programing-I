package tvschedule;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShortShows implements TVAdvice {

	private int maxDur;
	
	public ShortShows(int maxDur) {
		super();
		this.maxDur = maxDur;
	}

	@Override
	public SortedSet<TVShow> advice(Collection<TVShow> shows) {
		SortedSet<TVShow> set = new TreeSet<>();
		
		for(TVShow show : shows) {
			if(show.getDuration() < maxDur) {
				set.add(show);
			}
		}
		return set;
	}

}
