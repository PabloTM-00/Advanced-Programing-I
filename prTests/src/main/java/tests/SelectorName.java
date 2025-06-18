package tests;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SelectorName implements Selector{

	private final SortedSet<String> names;
	
	public SelectorName(Set<String> set) {
		names = new TreeSet<>();
		
		if(set == null) throw new AppException("Set received cant be null");
		
		for(String s : set) {
			String name = s.toUpperCase();
			
			names.add(name);
		}
	}

	public SortedSet<String> getNames() {
		return names;
	}

	@Override
	public boolean isSelectable(Set<Exercise> set) {
		if(set == null || set.isEmpty()) return false;
		
		for(Exercise e : set) {
			for(String name : names) {
				if(e.getName().equalsIgnoreCase(name)) return true;
			}
		}
		return false;
	}

}
