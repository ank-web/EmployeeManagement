package employeeManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Report {
	private int id;
	private int emp_id;
	private String status;
	
	private static final String userName = "CISADM";
	private static final String password = "CISADM";
	private static final String url ="jdbc:oracle:thin:@//localhost:1521/orcl";
	
	public Report(int id,int emp_id,String status) {
		this.id=id;
		this.emp_id=emp_id;
		this.status=status;
	}
	
	//methods 
	public void generate_monthly_report(String status) throws IOException {
	    // Connect to the database using JDBC or another ORM framework
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DriverManager.getConnection(url, userName, password);
	        String query = "SELECT * FROM employees WHERE status = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, status);
	        rs = stmt.executeQuery();

	        // Open a file for writing the monthly report
	        FileWriter writer = new FileWriter("monthly_report.txt");
	        writer.write("Monthly Report for Employees with Status: " + status + "\n\n");

	        // Loop through the result set and process each employee record
	        while (rs.next()) {
	            int emp_id = rs.getInt("emp_id");
	            String name = rs.getString("name");
	            double salary = rs.getDouble("salary");
	            int leaveBalance = rs.getInt("leave_balance");

	            // Calculate the required metrics for the monthly report
	            // ...

	            // Write the employee information and metrics to the report file
	            writer.write("Employee ID: " + emp_id + "\n");
	            writer.write("Name: " + name + "\n");
	            writer.write("Salary: " + salary + "\n");
	            writer.write("Leave Balance: " + leaveBalance + "\n");
	            // Write the calculated metrics to the report file
	            // ...
	            writer.write("\n");
	        }

	        // Close the report file
	        writer.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the database connection, statement, and result set
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public void generate_monthly_report(double minSalary, double maxSalary) {
	    // Connect to the database using JDBC or another ORM framework
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DriverManager.getConnection(url, userName, password);
	        String query = "SELECT * FROM employees WHERE salary >= ? AND salary <= ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setDouble(1, minSalary);
	        stmt.setDouble(2, maxSalary);
	        rs = stmt.executeQuery();

	        // Open a file for writing the monthly report
	        FileWriter writer = new FileWriter("monthly_report.txt");
	        writer.write("Monthly Report for Employees with Salary Between " + minSalary + " and " + maxSalary + "\n\n");

	        // Loop through the result set and process each employee record
	        while (rs.next()) {
	            int emp_id = rs.getInt("emp_id");
	            String name = rs.getString("name");
	            double salary = rs.getDouble("salary");
	            int leaveBalance = rs.getInt("leave_balance");

	            // Calculate the required metrics for the monthly report
	            // ...

	            // Write the employee information and metrics to the report file
	            writer.write("Employee ID: " + emp_id + "\n");
	            writer.write("Name: " + name + "\n");
	            writer.write("Salary: " + salary + "\n");
	            writer.write("Leave Balance: " + leaveBalance + "\n");
	            // Write the calculated metrics to the report file
	            // ...
	            writer.write("\n");
	        }

	        // Close the report file
	        writer.close();
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the database connection, statement, and result set
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



}
