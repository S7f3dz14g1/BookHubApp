package szwedo.bookhubapp.models;

public class UserBookDetails {

    private final long totalBooks;

    private final long currentBook;

    public UserBookDetails(long totalBooks, long currentBooks) {
        this.totalBooks = totalBooks;
        this.currentBook = currentBooks;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getCurrentBooks() {
        return currentBook;
    }
}
