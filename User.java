public class User {

    public enum Role {
        LIBRARIAN,
        USER
    }

    String UserName;
    private Role UserRole;
    public User(String UserName, Role UserRole) {
        this.UserName = UserName;
        this.UserRole = UserRole;
    }

    public String getUserName() {
        return UserName;
    }

    public Role getUserRole() {
        return UserRole;
    }

    public boolean isPermittedToAddBook() {
        return UserRole == Role.LIBRARIAN;
    }
}
