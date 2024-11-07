package wretailsystem;
/*
This class handles the user authentication by checking the input 
with the credentials from a file to validate login attempts.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Password {
    private Map<String, String> userCredentials = new HashMap<>();

    public Password(String filePath) {
        loadCredentials(filePath);
    }

    private void loadCredentials(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    userCredentials.put(username, password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users.txt file: " + e.getMessage());
        }
    }

    public boolean authenticate() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        if (username != null && password != null) {
            String storedPassword = userCredentials.get(username);
            if (storedPassword != null && storedPassword.equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Password password = new Password("users.txt");
        password.authenticate();
    }
}