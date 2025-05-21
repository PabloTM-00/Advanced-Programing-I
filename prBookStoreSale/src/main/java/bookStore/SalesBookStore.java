package bookStore;

public class SalesBookStore extends BookStore {
    private double discount;
    private String[] authorsOnSale;

    public SalesBookStore(double d, String[] a) {
        this.discount = d;
        this.authorsOnSale = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            this.authorsOnSale[i] = a[i].toLowerCase();
        }
    }

    public String[] getSale() {
        String[] copy = new String[authorsOnSale.length];
        System.arraycopy(authorsOnSale, 0, copy, 0, authorsOnSale.length);
        return copy;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public void addBook(String author, String title, double basePrice) {
        boolean isOnSale = false;
        String lowerAuthor = author.toLowerCase();
        for (String saleAuthor : authorsOnSale) {
            if (saleAuthor.equals(lowerAuthor)) {
                isOnSale = true;
                break;
            }
        }

        if (isOnSale) {
            super.addBook(new BookOnSale(author, title, basePrice, discount));
        } else {
            super.addBook(new Book(author, title, basePrice));
        }
    }

    @Override
    public String toString() {
        StringBuilder authorsList = new StringBuilder("[");
        for (int i = 0; i < authorsOnSale.length; i++) {
            if (i > 0) authorsList.append(", ");
            authorsList.append(authorsOnSale[i]);
        }
        authorsList.append("]");
        
        return String.format("%.1f%%%s\n%s", discount, authorsList, super.toString());
    }
}