// TestDatabase.java
public class TestDatabase {
    public static void main(String[] args) {
        DatabaseController db = new DatabaseController();
        
        if (db.testConnection()) {
            System.out.println("Database connected!");
            
            // Test saving a user
            User testUser = new User("TEST1", "test@email.com", "Test", "User", "Doe");
            testUser.setUserPassword("password123");
            
            if (db.saveUser(testUser)) {
                System.out.println("User saved successfully!");
            }
            
            // Test loading
            User loadedUser = db.loadUser("0002");
            if (loadedUser != null) {
                System.out.println("User loaded: " + loadedUser.getFullName());
            }
            
        } else {
            System.out.println("Connection failed!");
        }
        
        db.closeConnection();
    }
}