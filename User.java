public class User {

    String UserName;
    String UserRole;
    public User(String UserName, String UserRole) {
        this.UserName = UserName;
        this.UserRole = UserRole;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserRole() {
        return UserRole;
    }

    public boolean isPermittedToAddBook() {
        return "Librarian".equalsIgnoreCase(UserRole);
    }
}
