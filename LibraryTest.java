import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void test_LibraryName_ShouldNot_BeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Library(null));
    }

    @Test
    public void test_LibraryName_ShouldNot_BeNull_AndEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Library(" "));
    }

}