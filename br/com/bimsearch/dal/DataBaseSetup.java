package br.com.bimsearch.dal;

import java.sql.*;

public class DataBaseSetup {

    public static void setup() {
        // Root access to create DB and user
        String rootUrl = "jdbc:mysql://localhost:3306/";
        String rootUser = "root";
        String rootPassword = ""; // Change this if your root has a password

        try (Connection conn = DriverManager.getConnection(rootUrl, rootUser, rootPassword);
             Statement stmt = conn.createStatement()) {

            // 1. Create database if it doesn't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS connector_db");

            // 2. Create the user if it doesn't exist
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM mysql.user WHERE user = 'appuser'");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate("CREATE USER 'appuser'@'localhost' IDENTIFIED BY ''");
                System.out.println("User 'appuser' created.");
            } else {
                System.out.println("User 'appuser' already exists.");
            }

            // 3. Grant privileges and flush
            stmt.executeUpdate("GRANT ALL PRIVILEGES ON connector_db.* TO 'appuser'@'localhost'");
            stmt.executeUpdate("FLUSH PRIVILEGES");

            System.out.println("Database and user setup completed.");

        } catch (SQLException e) {
            e.printStackTrace();
            return; // Don't continue if setup failed
        }

        // 4. Connect as appuser to create tables and default admin user
        String appUrl = "jdbc:mysql://localhost:3306/connector_db";
        String appUser = "appuser";
        String appPassword = "";  // You said this is now empty

        try (Connection appConn = DriverManager.getConnection(appUrl, appUser, appPassword);
             Statement appStmt = appConn.createStatement()) {

            // Create bimuser table
            appStmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS bimuser (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL, " +
                "password VARCHAR(50) NOT NULL)"
            );

            // Insert admin if table is empty
            ResultSet rs = appStmt.executeQuery("SELECT COUNT(*) FROM bimuser");
            rs.next();
            if (rs.getInt(1) == 0) {
                appStmt.executeUpdate("INSERT INTO bimuser (username, password) VALUES ('admin', 'admin1')");
                System.out.println("Default user 'admin' created.");
            } else {
                System.out.println("User table already populated.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

