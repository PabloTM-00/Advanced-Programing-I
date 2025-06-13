package wordIndex;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PositionInLineIndex extends AbstractIndex {

	SortedMap<String,SortedMap<Integer,SortedSet<Integer>>> index;
	
	public PositionInLineIndex() {
		index = new TreeMap<>();
	}

	@Override
	public void solve(String delimiters) {
		index.clear();
		
		for(int i = 0; i < sentences.size(); i++) {
			String sentence = sentences.get(i);
			
			Scanner sc = new Scanner(sentence);
			sc.useDelimiter(delimiters);
			
			int pos = 0; // New counter for position in each line
			
			while(sc.hasNext()) {
				String word = sc.next().toLowerCase().trim();
				
				if(word.isEmpty()) continue;
				
				if(!index.containsKey(word)) {
					index.put(word,new TreeMap<>());
				}
				
				Map<Integer, SortedSet<Integer>> lineMap = (Map<Integer, SortedSet<Integer>>) index.get(word);
				
				if(!lineMap.containsKey(i)) {
					lineMap.put(i, new TreeSet<>());
				}
				
				lineMap.get(i).add(pos);
				
				pos++;				
			}
			sc.close();
		}
	}

	@Override
	public void presentIndex(PrintWriter pw) {
	    for (String word : index.keySet()) {
	        pw.println(word);
	        Map<Integer, SortedSet<Integer>> lineMap = index.get(word);

	        for (Integer line : lineMap.keySet()) {
	            String positionsStr = lineMap.get(line).stream()
	                .map(String::valueOf)
	                .collect(Collectors.joining(","));

	            pw.println(line + " <" + positionsStr + ">");
	        }
	    }
	    pw.flush();
	}


}
