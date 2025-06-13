package wordIndex;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class CounterIndex extends AbstractIndex{

	SortedMap<String,Integer> index;
	
	public CounterIndex() {
		index = new TreeMap<>();
	}

	@Override
	public void solve(String delimiters) {
		index.clear();
		
		for(String sentence : sentences) {
			Scanner sc = new Scanner(sentence);
			sc.useDelimiter(delimiters);
			
			while(sc.hasNext()) {
				String word = sc.next().toLowerCase();
												
				if(index.containsKey(word)) {
					index.put(word, index.get(word) + 1);
				}
				else {
					index.put(word, 1);
				}
			}
			sc.close();
		}
	}

	@Override
	public void presentIndex(PrintWriter pw) {
		for (Map.Entry<String, Integer> entry : index.entrySet()) {
		    String word = entry.getKey();
		    int count = entry.getValue();
		    pw.println(word + " " + count);
		}
		pw.flush();
	}

}
