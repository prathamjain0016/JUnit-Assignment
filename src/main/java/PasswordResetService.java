package authentication;

import java.util.HashMap;

public class PasswordResetService {
    private MailService mailService;
    private HashMap<String, user> users;

    public PasswordResetService(MailService mailService, HashMap<String, user> users) {
        this.mailService = mailService;
        this.users = users;
    }

    public boolean sendPasswordResetLink(String email) {
        if (!isValidEmail(email)) {
            return false; // Invalid email
        }

        if (!users.containsKey(email)) {
            return false; // Email not found in the user database
        }

        user user = users.get(email);
        String resetLink = generateResetLink(user);
        return mailService.sendMail(email, "Password Reset Link", resetLink);
    }

    private String generateResetLink(user user) {
        // Generate a unique reset link for the user
        return "https://example.com/reset-password?user=" + user.getEmail();
    }

    private boolean isValidEmail(String email) {
        // Simple email validation
        return email != null && email.contains("@");
    }
}

