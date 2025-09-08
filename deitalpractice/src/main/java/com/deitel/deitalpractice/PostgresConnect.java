package com.deitel.deitalpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostgresConnect {
    public static void main(String[] args) {
        String host = "localhost";
        String port = "30007";
        String database = "mydb";
        String user = "dbuser";
        String password = "dbpass";

        String url = String.format(
            "jdbc:postgresql://%s:%s/%s",
            host, port, database
        );

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to PostgreSQL successfully!");

            String sql = "SELECT version()";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("PostgreSQL version: " + rs.getString(1));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("❌ Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
