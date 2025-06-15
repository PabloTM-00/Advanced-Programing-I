package tvschedule;

import java.util.Collection;
import java.util.SortedSet;

public interface TVAdvice {
	SortedSet<TVShow> advice(Collection<TVShow> shows);
}
