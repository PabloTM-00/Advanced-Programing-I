package simpleFilesWordCounting;

import java.io.*;
import java.util.*;

public class WordCounter {
	private static final int INITIAL_SIZE = 10;
	private int wordsNumber;
	private WordInText[] words;
	
	public WordCounter() {
		words = new WordInText[INITIAL_SIZE];
		wordsNumber = 0;
	}
	public WordCounter(int size) {
		words = new WordInText[size];
		wordsNumber = 0;
	}
	
	private int position(String word) {
		WordInText aux = new WordInText(word);
		int pos = -1;
		
		for(int i = 0; i < wordsNumber; i++) {
			if(aux.equals(words[i])) {
				pos = i;
			}
		}
		return pos;
	}
	
	protected void include(String word) {
		int pos = position(word);
		if(pos == -1) {
			if(words.length <= wordsNumber) {
				words = Arrays.copyOf(words, wordsNumber + 1);
			}
			words[wordsNumber] = new WordInText(word);
			wordsNumber++;
			} else {
			words[pos].increment();
			}
		}
	
	private void includeAll(String line, String del) {
		String[] words = line.split(del);
		
		for(String word : words) {
			if(!word.isEmpty()) {
				include(word);
			}
		}
	}
	
	public void includeAll(String[] text, String del) {
		for(int i = 0; i < text.length;i++) {
			includeAll(text[i],del);
		}
	}
	
	public void includeAllFromFile(String filename, String del) {
		File file = new File(filename);
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				includeAll(sc.nextLine(),del);
			}
			sc.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public WordInText find(String pal) {
		for(int i = 0; i < wordsNumber; i++) {
			if(words[i].equals(pal)) {
				return words[i];
			}
		}
		
		throw new NoSuchElementException("Not found: " + pal);
	}
	
	@Override
	public String toString() {
	    StringJoiner sj = new StringJoiner(" - ", "[", "]");
	    for (int i = 0; i < wordsNumber; i++) {
	        sj.add(words[i].toString());
	    }
	    return sj.toString();
	}
	
	
	public void presentWords(String filename) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(filename);
		presentWords(pw);
		}
	
	public void presentWords(PrintWriter pw) {
		for(int i = 0; i < wordsNumber; i++) {
			pw.println(words[i].toString());
		}
		pw.close();
	}
}