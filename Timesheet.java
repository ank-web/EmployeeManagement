package employeeManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Timesheet {
	
	private static final String userName = "CISADM";
	private static final String password = "CISADM";
	private static final String url ="jdbc:oracle:thin:@//localhost:1521/orcl";
	
	private int id, emp_id;
	private LocalDate today_date;
	private int hoursWorked;
//	public Timesheet(int id,int emp_id,LocalDate date,int hoursWorked){
//		this.id=id;
//		this.emp_id=emp_id;
//		this.today_date=today_date;
//		this.hoursWorked=hoursWorked;
//	}
	public int getId() {
		return id;
	}
	public int getEmpId() {
		return emp_id;
	}
	public LocalDate getDate() {
		return today_date;
	}
	public int getHoursWorked() {
		return hoursWorked;
	}
	
	public void updateTimeSheet() throws ClassNotFoundException, SQLException {
	    Scanner scanner = new Scanner(System.in);
	    Connection connection = null;
	    PreparedStatement ps = null;
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        connection = DriverManager.getConnection(url, userName, password);

	        System.out.print("Enter employee ID: ");
	        int emp_id = scanner.nextInt();

	        System.out.print("Enter date (yyyy-mm-dd): ");
	        LocalDate date = LocalDate.parse(scanner.next());

	        System.out.print("Enter hours worked: ");
	        int hoursWorked = scanner.nextInt();

	        String query = "UPDATE timesheet SET hours_worked = ? WHERE emp_id = ? AND date = ?";
	        ps = connection.prepareStatement(query);
	        ps.setInt(1, hoursWorked);
	        ps.setInt(2, emp_id);
	        ps.setDate(3, java.sql.Date.valueOf(date));
	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Time sheet updated successfully.");
	        } else {
	            System.out.println("No time sheet records found for the given input.");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error updating time sheet: " + e.getMessage());
	    } catch (DateTimeParseException e) {
	        System.out.println("Error: invalid date format.");
	    } catch (NumberFormatException e) {
	        System.out.println("Error: invalid numeric format.");
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	        if (ps != null) {
	            ps.close();
	        }
	        scanner.close();
	    }
	}

	
	public void sendTimesheetReminder() {
	    try {
	        // Create a connection to the database
	        Connection conn = DriverManager.getConnection(url, userName, password);
	        
	        // Create a statement
	        Statement stmt = conn.createStatement();
	        
	        // Query to get employees who have not submitted timesheets for the current week
	        LocalDate today = LocalDate.now();
	        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
	        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
	        String query = "SELECT e.email FROM employee e \r\n"
	        		+ "JOIN timesheet t ON e.emp_id = t.emp_id \r\n"
	        		+ "WHERE t.hoursWorked = 0;";
	       

	        ResultSet rs = stmt.executeQuery(query);
	        
	        // Loop through the result set and send reminders to the employees
	        while (rs.next()) {
	            String emp_id = rs.getString("email");
	            String message = "Dear Employee,\n\nThis is a reminder to submit your timesheet for the current week.\n\nThank you,\nThe Management";
	            //sendEmail(email, message);
	            System.out.println(message);
	        }
	        
	        // Close the resources
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}


}
