import org.junit.jupiter.api.Test;

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
    public void test_ShouldAddBook_ToLibrary() {
        Library library = new Library("LD_College_Library");

        Book book = new Book("9780132350884", "Clean Code", "Robert Cecil Martin", 2012);
        library.addBook(book);

        Book storedBook = getBookByISBN("9780132350884");

        assertNotNull(storedBook);
        assertEquals("9780132350884", storedBook.getISBN());
        assertEquals("Clean Code", storedBook.getTitle());
        assertEquals("9Robert Cecil Martin", storedBook.getAuthor());
        assertEquals(2012, storedBook.getPublicationYear());
    }

}