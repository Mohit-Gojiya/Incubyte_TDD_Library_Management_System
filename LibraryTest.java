import org.junit.jupiter.api.Test;

import java.time.Year;

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
        Library library = new Library("LD_College_Library");
        User librarian = new User("Mohit", User.Role.LIBRARIAN);

        library.addUser(librarian);

        User user = library.getUserByName("Mohit");
        assertEquals(librarian, user);
    }

}