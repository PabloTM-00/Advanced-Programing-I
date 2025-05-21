package bookStore;

public class FlexSalesBookStore extends BookStore {
    private FlexDiscount discountPolicy;

    public FlexSalesBookStore(FlexDiscount discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void setDiscount(FlexDiscount discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public FlexDiscount getDiscount() {
        return discountPolicy;
    }

    @Override
    public void addBook(String author, String title, double basePrice) {
        Book book = new Book(author, title, basePrice);
        double discount = discountPolicy.getDiscount(book);
        
        Book bookToAdd;
        if (discount > 0) {
            bookToAdd = new BookOnSale(author, title, basePrice, discount);
        } else {
            bookToAdd = book;
        }
        
        super.addBook(bookToAdd);
    }

    @Override
    public String toString() {
        return discountPolicy + "\n" + super.toString();
    }
}