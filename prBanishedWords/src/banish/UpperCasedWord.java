package banish;

public class UpperCasedWord implements BanishingStrategy {

	@Override
    public String replaceBanishedWord(String bWord) {
        StringBuilder reversed = new StringBuilder(bWord.toUpperCase());
        return reversed.reverse().toString();
    }

}
