import simpleFilesWordCounting.*;

public class MainWordInText {
	
	public static void main (String[] args) {
		WordInText word1 = new WordInText("wood");
		WordInText word2 = new WordInText("Wood");
		
		word1.increment();
		
		System.out.println("Word 1 = " + word1.toString());
		System.out.println("Word 2 = " + word2.toString());
		
		if (word1.equals(word2)) {
			System.out.println("The words are equal");
		}
		else {
			System.out.println("The words are not equal");
		}
	}
}