package simpleFilesWordCounting;

public class WordInText {
	private String word;
	private int times;
	
	public WordInText(String word) {
		super();
		this.word = word.toUpperCase();
		this.times = 1;
		}
	
	public void increment() {
		times += 1;
	}
	
	@Override
	public String toString() {
		return word + ": " + times;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof WordInText)) return false;

	    WordInText other = (WordInText) o;
	    return word != null && word.equals(other.word);
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}
}
