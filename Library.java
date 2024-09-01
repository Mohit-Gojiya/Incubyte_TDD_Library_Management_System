import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Library {

    String Name;
    private final Map<String, Book> Book_Inventory;
    private final Map<String, User> User_Catalog;
    private final Map<String, String> BorrowedBooks = new HashMap<>();
    private final Map<String, Book> BorrowedBookDetails;


    //------------------------------------------------Library---------------------------------------------------------//

    public Library(String Name)
    {
        if (Name == null || Name.isBlank() || Name.length() <= 3)
        { throw new IllegalArgumentException("Library Name Should not be null"); }

        this.Name = Name;
        this.Book_Inventory = new HashMap<>();
        this.User_Catalog = new HashMap<>();
        this.BorrowedBookDetails = new HashMap<>();
    }

    public Book get_Isbn_from_Library_Inventory(String Isbn) { return Book_Inventory.get(Isbn); }

    //----------------------------------------------------------------------------------------------------------------//


    //--------------------------------------------User_Management-----------------------------------------------------//

    public void addUser(User user)
    {
        if (user == null) { throw new IllegalArgumentException("User should not be null");}
        if (User_Catalog.containsKey(user.getUserName()))
        { throw new IllegalArgumentException("User already exists in catalog"); }
        User_Catalog.put(user.getUserName(), user);
    }

    public User getUserByName(String username) { return User_Catalog.get(username); }

    //----------------------------------------------------------------------------------------------------------------//


    //--------------------------------------------Book_Management-----------------------------------------------------//

    public void addBook(User user, Book book) throws Exception
    {
        if (book == null) { throw new IllegalArgumentException("Book Null"); }
        if (user.isPermittedToAddBook()) { Book_Inventory.put(book.getIsbn(), book); }
        else { throw new Exception("You are not authorized to add book"); }
    }

    public Map<String, Book> viewAvailableBooks() { return Map.copyOf(Book_Inventory); }

    public Book getBookByISBN(String Isbn) { return Book_Inventory.get(Isbn); }

    //----------------------------------------------------------------------------------------------------------------//


    //--------------------------------------------Borrow_&_Return-----------------------------------------------------//

    public void borrowBook(User user, String Isbn)
    {
        Book book = Book_Inventory.get(Isbn);
        if (BorrowedBooks.containsKey(Isbn)) { throw new IllegalArgumentException("Book is already borrowed"); }
        if (book != null)
        {
            BorrowedBooks.put(Isbn, user.getUserName());
            BorrowedBookDetails.put(Isbn, book);
            Book_Inventory.remove(Isbn);
        }
        else throw new IllegalArgumentException("Book not found");
    }

    public void returnBook(User user, String Isbn)
    {
        Book book = BorrowedBookDetails.get(Isbn);
        if (!BorrowedBooks.containsKey(Isbn)) { throw  new IllegalArgumentException("Book was not borrowed by any user");}

        if (Objects.equals(user.getUserName(), BorrowedBooks.get(Isbn)))
        {
            Book_Inventory.put(Isbn, book);
            BorrowedBooks.remove(Isbn);
            BorrowedBookDetails.remove(Isbn);
        }
        else throw new IllegalArgumentException("book was not borrowed by this user");
    }

    public String getBorrowerNameByISBN(String Isbn) { return BorrowedBooks.get(Isbn); }

    //----------------------------------------------------------------------------------------------------------------//






















}
