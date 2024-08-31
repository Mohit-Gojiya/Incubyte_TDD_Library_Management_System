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
    public void testShouldRetrieveAllAvailableBooks() throws Exception {
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
}