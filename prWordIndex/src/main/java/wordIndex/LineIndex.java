package wordIndex;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class LineIndex extends AbstractIndex{

	SortedMap<String,SortedSet<Integer>> index;
	
	public LineIndex() {
		index = new TreeMap<>();
	}

	@Override
	public void solve(String delimiters) {
		index.clear();
		
		for(int i = 0; i < sentences.size(); i++) {
			String sentence = sentences.get(i);
			
			Scanner sc = new Scanner(sentence);
			sc.useDelimiter(delimiters);
			
			while(sc.hasNext()) {
				String word = sc.next().toLowerCase().trim();
							
				if(!index.containsKey(word)) {
					index.put(word, new TreeSet<>());
				}
				index.get(word).add(i + 1);                            
			}
			sc.close();
		}
	}

	@Override
	public void presentIndex(PrintWriter pw) {
		for(Entry<String, SortedSet<Integer>> entry : index.entrySet()) {
			String word = entry.getKey();
			SortedSet<Integer> count = entry.getValue();
			
			pw.println(word + " " + count);
		}
		pw.flush();
	}

}
