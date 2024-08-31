public class Library {

    String Name;

    public Library (String Name)
    {
        if (Name == null || Name.isEmpty() )
        { throw new IllegalArgumentException("Library Name Should not be null"); }
        this.Name = Name;
    }
}
