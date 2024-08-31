import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Library {

    String Name;

    private final Map<String,Book> Book_Inventory;
    private final Map<String, User> User_Catalog;

    public Library (String Name)
    {
        if (Name == null || Name.isEmpty() || Name.length() <= 3)
        { throw new IllegalArgumentException("Library Name Should not be null"); }
        this.Name = Name;
        this.Book_Inventory = new HashMap<String, Book>();
        this.User_Catalog = new HashMap<String, User>();
    };

    public void addBook(User user , Book book) throws Exception {
       if (user.isPermittedToAddBook()) {
           Book_Inventory.put(book.getIsbn(), book);
       }
       else {
           throw new Exception("You are not authorized to add book");
       }
    };

    public Book get_Isbn_from_Library_Inventory(String Isbn) {
        return Book_Inventory.get(Isbn);
    };

    public void addUser(User user) {
        if(User_Catalog.containsKey(user.getUserName())){
            throw new IllegalArgumentException("User already exists in catalog");
        }
       User_Catalog.put(user.getUserName(),user);
    };

    public User getUserByName(String username) {
        return User_Catalog.get(username);
    };

    public Map<String,Book> viewAvailableBooks() {
    return Collections.unmodifiableMap(new HashMap<>(Book_Inventory));
    };
}
