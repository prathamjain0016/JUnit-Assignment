package authentication;

import java.util.HashMap;

public class AuthenticationService {
    private HashMap<String, user> users;

    public AuthenticationService() {
        users = new HashMap<>();
        // Simulated user database
        users.put("example@example.com", new user("example@example.com", "password123"));
    }

    public String authenticateUser(String email, String password) {
        if (!users.containsKey(email)) {
            return "Invalid email";
        }

        user user = users.get(email);
        if (!password.equals(user.getPassword())) {
            return "Wrong password";
        }

        return "Login successful";
    }
}
