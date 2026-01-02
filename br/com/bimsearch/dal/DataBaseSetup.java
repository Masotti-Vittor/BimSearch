package br.com.bimsearch.dal;

import java.sql.*;

public class DataBaseSetup {

	public static void setup() {
		// Root access to create DB and user
		String rootUrl = "jdbc:h2:./bimsearch";
		String rootUser = "";
		String rootPassword = ""; // Change this if we add a password
		
		try {
			Class.forName("org.h2.Driver");
			try (Connection conn = DriverManager.getConnection(rootUrl, rootUser, rootPassword);
				Statement stmt = conn.createStatement()) {
				
				// 1. Create database if it doesn't exist
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS bimuser (id IDENTITY PRIMARY KEY, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, perfil VARCHAR(50))");

				// 2. Create the user if it doesn't exist
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM bimuser");
				rs.next();
				if (rs.getInt(1) == 0) {
					stmt.executeUpdate("INSERT INTO bimuser (username, password, perfil) VALUES ('admin', 'admin1', 'admin')");
					System.out.println("User 'ADMIN' created.");
				} else {
					System.out.println("User table already exists.");
				}

				// 3. Grant privileges and flush
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS images (id IDENTITY PRIMARY KEY, image_path VARCHAR(400), numero_conexoes INT, argola BOOLEAN, cabo BOOLEAN, conduite BOOLEAN, cabo_conduite BOOLEAN, equipamento BOOLEAN, aereo BOOLEAN, tamanho INT, nameId VARCHAR(255))");
				System.out.println("Database and user setup completed.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			return; // Don't continue if setup failed
		}
	}
}