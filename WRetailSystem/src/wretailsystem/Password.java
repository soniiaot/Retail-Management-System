package wretailsystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Password {
    //File where user data is stored
    private static final String USERS_FILE = "users.txt";  
    private Map<String, String> users;  // Map to hold usernames and passwords

    //Initializes the users map and loads user data from file
    public Password() {
        users = new HashMap<>();
        loadUsersFromFile();
    }

    //Load users from a file into the users map
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                
                //Ensure the line has two parts, the username and password
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);  
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
    }

    //Authenticate the user by checking if the password matches the password
    public boolean authenticate(String username, String password) {
        return password.equals(users.get(username));
    }

    //Add a new user to the file and the map
    public void addUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + ":" + password);  
            writer.newLine();  
            users.put(username, password);  
        } catch (IOException e) {
            System.out.println("Error writing to users file: " + e.getMessage());
        }
    }
}