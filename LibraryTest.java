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

}