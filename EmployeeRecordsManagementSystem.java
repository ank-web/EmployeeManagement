package employeeManagement;


public class EmployeeRecordsManagementSystem {
	private static Connection conn;
    
    public EmployeeRecordsManagementSystem() throws SQLException {
        // Initialize the database connection
    	private static String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    	private static String USER = "CISADM";
    	private static String PASS = "CISADM";
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    
	
	public static void main(String args[]) {
		
	}
}
