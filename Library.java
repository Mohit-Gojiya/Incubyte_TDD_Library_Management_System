import java.util.HashMap;
import java.util.Map;

public class Library {

    String Name;

    private final Map<String,Book> Book_Inventory;

    public Library (String Name)
    {
        if (Name == null || Name.isEmpty() || Name.length() <= 3)
        { throw new IllegalArgumentException("Library Name Should not be null"); }
        this.Name = Name;
        this.Book_Inventory = new HashMap<String, Book>();
    }

    public void addBook(User user , Book book) {
       if (user.isPermittedToAddBook()) {
           Book_Inventory.put(book.getIsbn(), book);
       }
       else {
           throw new SecurityException("You are not authorized to add book");
       }
    }

    public Book get_Isbn_from_Library_Inventory(String Isbn) {
        return Book_Inventory.get(Isbn);
    };

}
