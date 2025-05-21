package banish;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextBanisher {

	private List<BanishedWord> banishedWords;

	public TextBanisher(List<BanishedWord> banishedWords) {
		super();
		this.banishedWords = new ArrayList<>();
	}

	@Override
	public String toString() {
		return banishedWords.toString();
	}
	
	public void readBanishedWords(String filepath) throws BanishException {
		try {
			File file = new File(filepath);
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if(!(line.isEmpty())) {
					String[] parts = line.split(", ");
					if(parts.length != 2) {
						throw new BanishException("Invalid format: " + line);
					}
					String word = parts[0].trim();
					int year = Integer.parseInt(parts[1].trim());
					BanishedWord bw = new BanishedWord(word,year);
					banishedWords.add(bw);
				}
			
			}
			sc.close();
		}
		catch(IOException | NumberFormatException e) {
			throw new BanishException("Error reading file: " + filepath);
			
		}
		
	}
	
	public void findBanishedWords(String textFilePath) throws BanishException {
		List<BanishedWord> foundWords = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(textFilePath));
		String line;
		while ((line = br.readLine()) != null) {
		    String[] words = line.split("\\W+");
		    for (String word : words) {
		        String wordL = word.toLowerCase();
		        for (BanishedWord bw : banishedWords) {
		            if (bw.getText().equalsIgnoreCase(wordL) && !foundWords.contains(bw)) {
		                foundWords.add(bw);
		            }
		        }
		    }
		}
		br.close();
		
		StringJoiner sj = new StringJoiner(", ", "(", ")");
		for (BanishedWord bw : foundWords) {
		    sj.add(bw.getText());
		}
		System.out.println(sj.toString());

		}
		catch(IOException e) {
			throw new BanishException("Error reading file: " + textFilePath);
		}
	}
	}
	}
}
