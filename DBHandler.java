package employeeManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** * A utility class for establishing a database connection using properties file. */
public class DBHandler {
	
	 /**     
	 *Returns a database connection based on properties file.     
	 *@return a Connection object representing a database connection     
	 **/
	public static Connection getConnection() throws IOException, SQLException {
        // Create a new Properties object to hold the database connection properties        
		Properties props = new Properties();
        // Read the properties file from the file system  
		try (FileInputStream inputFile = new FileInputStream("C:\\Users\\AnkitKumar\\eclipse-workspace\\ankit\\src\\employeeManagement\\monthly_report.txt")) {
            props.load(inputFile);
        } catch (IOException e) {
            throw new IOException("Error while reading the properties file: " + e.getMessage());
        }
        // Get the database connection properties from the Properties object        
		String url = props.getProperty("DB_URL");
        String user = props.getProperty("USER");
        String password = props.getProperty("PASS");
        // Establish a database connection using the connection properties        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            throw new SQLException("Error while connecting to the database: " + e.getMessage());
        }
    }
}
