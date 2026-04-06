/**
 * Superpowers Model: Book (符合工程规范的图书模型)
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    // Getter & Setter
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }

    @Override
    public String toString() {
        return "Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + 
               ", status=" + (isBorrowed ? "已借出" : "在馆") + '}';
    }
}
