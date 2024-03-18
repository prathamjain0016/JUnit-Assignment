package authentication_test;

import org.junit.jupiter.api.Test;

import authentication.MailService;
import authentication.PasswordResetService;
import authentication.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.*;

import java.util.HashMap;

public class PasswordResetServiceTest {

    @Test
    public void testSendPasswordResetLink_ValidEmailInMap() {
        // Creating a mock MailService
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        // Creating a mock user database to check for invalid user.
        HashMap<String, user> mockUsers = new HashMap<>();
        user user = new user("test@example.com", "oldPassword");
        mockUsers.put("test@example.com", user);

        // Creating a PasswordResetService using mock MailService and mock user database
        PasswordResetService passwordResetService = new PasswordResetService(mockMailService, mockUsers);

        // Test for sending password reset link for valid email in the database.
        boolean result = passwordResetService.sendPasswordResetLink("test@example.com");

        // Verifying that the sendMail method was called correctly
        verify(mockMailService).sendMail(eq("test@example.com"), eq("Password Reset Link"), anyString());
        assertTrue(result);
    }

    @Test
    public void testSendPasswordResetLink_InvalidEmail() {
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        
        HashMap<String, user> mockUsers = new HashMap<>();

        
        PasswordResetService passwordResetService = new PasswordResetService(mockMailService, mockUsers);

        // Test sending password reset link for invalid email
        boolean result = passwordResetService.sendPasswordResetLink("invalid@example.com");

        // Verify that sendMail method was not called for invalid email
        verify(mockMailService, never()).sendMail(anyString(), anyString(), anyString());
        assertFalse(result);
    }

    @Test
    public void testSendPasswordResetLink_ValidEmailNotInMap() {
        
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        
        HashMap<String, user> mockUsers = new HashMap<>();

        
        PasswordResetService passwordResetService = new PasswordResetService(mockMailService, mockUsers);

        // Test sending password reset link for valid email not in database.
        boolean result = passwordResetService.sendPasswordResetLink("test@example.com");

        // Verify that sendMail method was not called for email not in database.
        verify(mockMailService, never()).sendMail(anyString(), anyString(), anyString());
        assertFalse(result);
    }
}