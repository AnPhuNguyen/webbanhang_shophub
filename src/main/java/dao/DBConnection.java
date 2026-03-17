package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static String request = "jdbc:sqlserver://DESKTOP-24QOS1E;"
			+ "databaseName=QLweb_ban_hang;"
			+ "user=sa;"
			+ "password=12345678;"
			+ "trustServerCertificate=true";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(request);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
        
    }
}