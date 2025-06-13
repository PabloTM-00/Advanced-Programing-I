package wordIndex;

import java.io.PrintWriter;

public interface Index {

	void addSentence(String sentence);

	void solve(String delimiters);

	void presentIndex(PrintWriter pw);

	default void presentIndexConsole() {
		presentIndex(new PrintWriter(System.out,true));
	}
	
}
