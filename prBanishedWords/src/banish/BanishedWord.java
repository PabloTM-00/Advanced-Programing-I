package banish;

import java.util.Objects;

public class BanishedWord {
	private String text;
	private int year;

	public BanishedWord(String text, int year) {
		super();
		this.text = text;
		this.year = year;
	}

	public String getText() {
		return text;
	}

	public int getYear() {
		return year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(text.toLowerCase(), year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BanishedWord other = (BanishedWord) obj;
		return Objects.equals(text, other.text) && year == other.year;
	}

	@Override
	public String toString() {
		return "("+ text + ", " + year + ")";
	}

	

	
}
