import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {


    Library library = new Library("LD_College_Library");

    @Test
    public void test_Library_constructor_avaiable(){
        assertNotNull(library);
    }

    @Test
    public void test_LibraryName_ShouldNot_BeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Library(null));
    }

    @Test
    public void test_LibraryName_ShouldNot_BeNull_AndEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Library(" "));
    }

    @Test
    public void test_LibraryName_ShouldNot_Be_LessThenThree_() {
        assertThrows(IllegalArgumentException.class, () -> new Library("ABC"));
    }

    @Test
    public void test_ShouldAddBook_ToLibrary_ByOnlyPermittedUser() throws Exception {
        Library library = new Library("LD_College_Library");

        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));
        User user = new User("Mohit", User.Role.LIBRARIAN);
        library.addBook(user,book);

        Book storedBook = library.get_Isbn_from_Library_Inventory("9780132350884");

        assertNotNull(storedBook);
        assertEquals("9780132350884", storedBook.getIsbn());
        assertEquals("Clean Code", storedBook.getTitle());
        assertEquals("Robert Cecil Martin", storedBook.getAuthor());
        assertEquals(Year.of(2012), storedBook.getYear());
        assertEquals(book, storedBook);
    }

    @Test
    public void test_ShouldAddUser_ToLibrary() {

        User librarian = new User("Mohit", User.Role.LIBRARIAN);

        library.addUser(librarian);

        User user = library.getUserByName("Mohit");
        assertEquals(librarian, user);
    }

    @Test
    public void test_ShouldNot_AllowDuplicateUsers() {

        User primaryLibrarian = new User("Mohit", User.Role.LIBRARIAN);
        User secondaryLibrarian = new User("Mohit", User.Role.LIBRARIAN);

        library.addUser(primaryLibrarian);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.addUser(secondaryLibrarian));
        assertEquals("User already exists in catalog", exception.getMessage());
    }

    @Test
    public void test_ShouldFetchUser_ByUsername() {

        User primaryLibrarian = new User("Mohit", User.Role.LIBRARIAN);

        library.addUser(primaryLibrarian);
        User fetchedUser = library.getUserByName("Mohit");
        assertEquals(primaryLibrarian, fetchedUser);
    }

    @Test
    public void test_ShouldRetrieve_AllAvailableBooks() throws Exception {
        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        Book book1 = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));
        Book book2 = new Book("9780134685991", "Effective Java", "Joshua Bloch", Year.of(2018));

        library.addUser(librarian);
        library.addBook(librarian, book1);
        library.addBook(librarian, book2);

        Map<String, Book> availableBooks = library.viewAvailableBooks();

        assertEquals(2, availableBooks.size());
        assertTrue(availableBooks.containsKey("9780132350884"));
        assertTrue(availableBooks.containsKey("9780134685991"));
    }

    @Test
    public void test_ShouldReturn_Immuitable_HashMap() throws Exception {
        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        Book book1 = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));

        library.addUser(librarian);
        library.addBook(librarian, book1);

        Map<String, Book> availableBooks = library.viewAvailableBooks();

        assertThrows(UnsupportedOperationException.class, () -> availableBooks.put("9780134685991", new Book("9780134685991", "Effective Java", "Joshua Bloch", Year.of(2018))));
    }

    @Test
    public void test_ShouldAllow_ToBorrowBook_FromLibrary() throws Exception {
        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        User user = new User("Milan", User.Role.USER);
        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));

        library.addUser(librarian);
        library.addUser(user);
        library.addBook(librarian, book);

        library.borrowBook(user, "9780132350884");

        Book borrowedBook = library.getBookByISBN("9780132350884");
        assertNull(borrowedBook, "borrowedBook should be null as it has been borrowed earlier.");
    }

    @Test
    public void test_ShouldThrowException_WhenBookNotFoundDuring_BorrowRequest() {

        User user = new User("Mohit", User.Role.USER);

        library.addUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.borrowBook(user, "9780132350884"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    public void test_ShouldThrowException_WhenBook_IsAlreadyBorrowed() throws Exception {

        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        User user1 = new User("Milan", User.Role.USER);
        User user2 = new User("Ram", User.Role.USER);
        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));

        library.addUser(librarian);
        library.addUser(user1);
        library.addUser(user2);
        library.addBook(librarian, book);

        library.borrowBook(user1, "9780132350884");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.borrowBook(user2, "9780132350884"));
        assertEquals("Book is already borrowed", exception.getMessage());
    }

    @Test
    public void test_ShouldReturn_BorrowerName_WhoBorrowedBook() throws Exception {
        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        User user = new User("Milan", User.Role.USER);
        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));

        library.addUser(librarian);
        library.addUser(user);
        library.addBook(librarian, book);

        library.borrowBook(user, "9780132350884");

        String borrowerName = library.getBorrowerNameByISBN("9780132350884");

        assertEquals(user.getUserName(), borrowerName);
    }

    @Test
    public void test_ShouldAllowUser_ToReturnBook_ToLibrary() throws Exception {
        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        User user = new User("Milan", User.Role.USER);
        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));

        library.addUser(librarian);
        library.addUser(user);
        library.addBook(librarian, book);

        library.borrowBook(user, "9780132350884");
        library.returnBook(user, "9780132350884");

        Book returnedBook = library.getBookByISBN("9780132350884");
        assertNotNull(returnedBook, "Returned book have be available in the books catalog.");
    }

    @Test
    public void test_ShouldThrowException_WhenUserReturnsBook_ThatIsNotBorrowedByHim() throws Exception {
        User librarian = new User("Mohit", User.Role.LIBRARIAN);
        User user1 = new User("Milan", User.Role.USER);
        User user2 = new User("Ram", User.Role.USER);
        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", Year.of(2012));

        library.addUser(librarian);
        library.addUser(user1);
        library.addUser(user2);
        library.addBook(librarian, book);

        library.borrowBook(user1, "9780132350884");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.returnBook(user2, "9780132350884"));
        assertEquals("book was not borrowed by this user", exception.getMessage());
    }

}