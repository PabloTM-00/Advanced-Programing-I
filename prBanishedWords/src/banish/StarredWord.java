package banish;

public class StarredWord implements BanishingStrategy {
    @Override
    public String replaceBanishedWord(String bWord) {
        if (bWord.length() <= 2) {
            return bWord; // No stars for words of length 2 or less
        }

        StringBuilder result = new StringBuilder();
        result.append(bWord.charAt(0)); // Add the first letter
        for (int i = 1; i < bWord.length() - 1; i++) {
            result.append('*'); // Add stars in between
        }
        result.append(bWord.charAt(bWord.length() - 1)); // Add the last letter

        return result.toString();
    }
}
