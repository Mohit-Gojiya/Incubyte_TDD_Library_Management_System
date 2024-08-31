public class Library {

    private final String Name;

    public Library (String Name)
    {
        if (Name == null) { throw new IllegalArgumentException("Library Name Should not be null"); }
        this.Name = Name;
    }
}
