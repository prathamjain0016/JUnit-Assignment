package authentication_test;

import org.junit.jupiter.api.Test;

import authentication.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationServiceTest {

    @Test
    public void testInvalidEmail() {
        AuthenticationService authService = new AuthenticationService();
        String result = authService.authenticateUser("invalid@example.com", "password123");
        assertEquals("Invalid email", result);
    }

    @Test
    public void testWrongPassword() {
        AuthenticationService authService = new AuthenticationService();
        String result = authService.authenticateUser("example@example.com", "wrongpassword");
        assertEquals("Wrong password", result);
    }

    @Test
    public void testSuccessfulLogin() {
        AuthenticationService authService = new AuthenticationService();
        String result = authService.authenticateUser("example@example.com", "password123");
        assertEquals("Login successful", result);
    }
}
