package employeeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Salary {
	private static final String userName = "CISADM";
	private static final String password = "CISADM";
	private static final String url ="jdbc:oracle:thin:@//localhost:1521/orcl";
	
	private int emp_id;
	private double salary;
	private int leaveBalance;
	
	//methods
	public double getSalary() {return 0.0;};
	
	public double calculateSalary(int emp_id) throws SQLException, ClassNotFoundException {
	    // Establish a connection to the database
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	        connection = DriverManager.getConnection(url, userName, password);

	        // Retrieve salary and leave balance for the given employee
	        String query = "SELECT salary, leave_balance FROM employee JOIN leave_balance ON employee.emp_id = leave_balance.emp_id WHERE employee.emp_id = ?";
	        ps = connection.prepareStatement(query);
	        ps.setInt(1, emp_id);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            salary = rs.getDouble("salary");
	            leaveBalance = rs.getInt("leave_balance");
	        }

	        // Update salary in the employee table based on the leave balance
	        double updatedSalary = salary;
	        if (leaveBalance < 4) {
	            updatedSalary -= Math.abs(4 - leaveBalance) * (salary / 30); // Assuming a month has 30 days
	        }
	        String salaryUpdateQuery = "UPDATE employee SET salary = ? WHERE emp_id = ?";
	        ps = connection.prepareStatement(salaryUpdateQuery);
	        ps.setDouble(1, updatedSalary);
	        ps.setInt(2, emp_id);
	        ps.executeUpdate();

	        // Set the updated salary for the current object and return it
	        salary = updatedSalary;
	        return salary;
	    } finally {
	        // Close the database resources
	        if (rs != null) {
	            rs.close();
	        }
	        if (ps != null) {
	            ps.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}
}
