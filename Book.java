import java.time.Year;

public class Book {

    String Isbn;
    String Title;
    String Author;
    java.time.Year Year;

    public Book(String ISBN, String TITLE, String AUTHOR, Year PUBLICATION_YEAR) {
        this.Isbn = ISBN;
        this.Title = TITLE;
        this.Author = AUTHOR;
        this.Year = PUBLICATION_YEAR;
    }

    public String getIsbn() {
        return Isbn;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public Year getYear() {
        return Year;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) return true;
        if(object == null || (this.getClass() != object.getClass())) return false;
        Book book = (Book) object;
        return Isbn.equals(book.Isbn);
    }

    @Override
    public int hashCode() {
        return Isbn.hashCode();
    }

}
