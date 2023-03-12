package employeeManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Report {
	private int id;
	private int emp_id;
	private String status;
	
	private static final String userName = "CISADM";
	private static final String password = "CISADM";
	private static final String url ="jdbc:oracle:thin:@//localhost:1521/orcl";
	
//	public Report(int id,int emp_id,String status) {
//		this.id=id;
//		this.emp_id=emp_id;
//		this.status=status;
//	}
	
	//methods
	
	// Generate a report of the total salary paid to all employees for a given month and year    
	public double getMonthlySalaryReport(int month, int year) throws SQLException {
		double totalSalary = 0;
		Connection conn = DriverManager.getConnection(url, userName, password);
    	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Salary WHERE MONTH(date) = ? AND YEAR(date) = ?");
    	pstmt.setInt(1, month);
    	pstmt.setInt(2, year);
    	ResultSet rs = pstmt.executeQuery();
    	while (rs.next()) {
        	double salary = rs.getDouble("salary");
        	totalSalary += salary;
    	}
    	rs.close();
    	pstmt.close();
    	return totalSalary;
	}
	
	public double calculateAverageSalaryByDepartment(String department) throws SQLException {
	    double avgSalary = 0.0;
	    int count = 0;
	    try (Connection conn = DriverManager.getConnection(url, userName, password);
	         PreparedStatement ps = conn.prepareStatement("SELECT salary FROM employee WHERE department = ?")) {
	        ps.setString(1, department);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            avgSalary += rs.getDouble("salary");
	            count++;
	        }
	        if (count > 0) {
	            avgSalary /= count;
	        }
	    }
	    return avgSalary;
	}
	
	public Map<String, Integer> calculateEmployeesByDepartment() throws SQLException {
	    Map<String, Integer> employeesByDepartment = new HashMap<>();
	    try (Connection conn = DriverManager.getConnection(url, userName, password);
	         Statement stmt = conn.createStatement()) {
	        ResultSet rs = stmt.executeQuery("SELECT department, COUNT(*) AS count FROM employee GROUP BY department");
	        while (rs.next()) {
	            employeesByDepartment.put(rs.getString("department"), rs.getInt("count"));
	        }
	    }
	    return employeesByDepartment;
	}
	
	public Map<String, Integer> calculateEmployeesByJobTitle() throws SQLException {
	    Map<String, Integer> employeesByJobTitle = new HashMap<>();
	    try (Connection conn = DriverManager.getConnection(url, userName, password);
	         Statement stmt = conn.createStatement()) {
	        ResultSet rs = stmt.executeQuery("SELECT jobtitle, COUNT(*) AS count FROM employee GROUP BY jobtitle");
	        while (rs.next()) {
	            employeesByJobTitle.put(rs.getString("jobtitle"), rs.getInt("count"));
	        }
	    }
	    return employeesByJobTitle;
	}
	
	public Map<Integer, Integer> calculateTotalHoursWorkedByEmployee() throws SQLException {
	    Map<Integer, Integer> totalHoursByEmployee = new HashMap<>();
	    try (Connection conn = DriverManager.getConnection(url, userName, password);
	         Statement stmt = conn.createStatement()) {
	        ResultSet rs = stmt.executeQuery("SELECT emp_id, SUM(hoursworked) AS total_hours FROM timesheet GROUP BY emp_id");
	        while (rs.next()) {
	            totalHoursByEmployee.put(rs.getInt("emp_id"), rs.getInt("total_hours"));
	        }
	    }
	    return totalHoursByEmployee;
	}
	
	public double getAverageSalary() throws SQLException {
	    double averageSalary = 0;
	    try {
	        Connection conn = DriverManager.getConnection(url, userName, password);
	        Statement stmt = conn.createStatement();
	        String query = "SELECT AVG(salary) as avg_salary FROM employee";
	        ResultSet rs = stmt.executeQuery(query);
	        if (rs.next()) {
	            averageSalary = rs.getDouble("avg_salary");
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	    return averageSalary;
	}
	
	public int getNumberOfEmployees() throws SQLException {
	    int numEmployees = 0;
	    try {
	        Connection conn = DriverManager.getConnection(url, userName, password);
	        Statement stmt = conn.createStatement();
	        String query = "SELECT COUNT(*) as num_employees FROM employee";
	        ResultSet rs = stmt.executeQuery(query);
	        if (rs.next()) {
	            numEmployees = rs.getInt("num_employees");
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	    return numEmployees;
	}
	
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
	        //FileWriter writer = new FileWriter("monthly_report.txt");
	        //writer.write("Monthly Report for Employees with Status: " + status + "\n\n");

	        // Loop through the result set and process each employee record
	        while (rs.next()) {
	            int emp_id = rs.getInt("emp_id");
	            String name = rs.getString("name");
	            double salary = rs.getDouble("salary");
	            int leaveBalance = rs.getInt("leave_balance");
	            System.out.println("Working Properly");

	            // Calculate the required metrics for the monthly report
	            // ...

	            // Write the employee information and metrics to the report file
//	            writer.write("Employee ID: " + emp_id + "\n");
//	            writer.write("Name: " + name + "\n");
//	            writer.write("Salary: " + salary + "\n");
//	            writer.write("Leave Balance: " + leaveBalance + "\n");
//	            // Write the calculated metrics to the report file
//	            // ...
//	            writer.write("\n");
	        }

	        // Close the report file
	        //writer.close();
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
