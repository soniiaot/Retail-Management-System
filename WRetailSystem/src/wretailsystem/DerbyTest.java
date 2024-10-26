/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wretailsystem;

/**
 *
 * @author fancy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String url = "jdbc:derby:rms;create=true";
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Connected to embedded Derby database!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}


