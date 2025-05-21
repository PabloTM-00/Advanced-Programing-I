package bookStore;

import java.util.Arrays;
import java.util.List;

public class DiscountAuthor implements FlexDiscount {
    private final double discountPercentage;
    private final List<String> authors;

    public DiscountAuthor(double discountPercentage, String[] authors) {
        this.discountPercentage = discountPercentage;
        this.authors = Arrays.asList(authors);
    }

    @Override
    public double getDiscount(Book book) {
        for (String author : authors) {
            if (author.equalsIgnoreCase(book.getAuthor())) {
                return discountPercentage;
            }
        }
        return 0.0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.1f%%[", discountPercentage));
        if (!authors.isEmpty()) {
            sb.append(authors.get(0));
            for (int i = 1; i < authors.size(); i++) {
                sb.append(", ").append(authors.get(i));
            }
        }
        sb.append("]");
        return sb.toString();
    }
}