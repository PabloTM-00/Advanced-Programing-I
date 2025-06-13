package wordIndex;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractIndex implements Index {

	protected List<String> sentences;
	
	public AbstractIndex() {
		sentences = new ArrayList<>();
	}
	
	public void addSentence(String s) {
		sentences.add(s);
	}

}
