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
	private LocalDate date;
	private int hoursWorked;
	public Timesheet(int id,int emp_id,LocalDate date,int hoursWorked){
		this.id=id;
		this.emp_id=emp_id;
		this.date=date;
		this.hoursWorked=hoursWorked;
	}
	public int getId() {
		return id;
	}
	public int getEmpId() {
		return emp_id;
	}
	public LocalDate getDate() {
		return date;
	}
	public int getHoursWorked() {
		return hoursWorked;
	}
	
	public void addTimeSheet() throws ClassNotFoundException, SQLException {
		String fileName="";
		Connection connection=null;
		PreparedStatement ps = null;
		Scanner scanner=null;
	    try {
	        File file = new File(fileName);
	        scanner = new Scanner(file);
	        Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url,userName,password);

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] tokens = line.split(",");

	            int id = Integer.parseInt(tokens[0]);
	            int emp_id = Integer.parseInt(tokens[1]);
	            LocalDate date = LocalDate.parse(tokens[2]);
	            int hoursWorked = Integer.parseInt(tokens[3]);

	            String query = "Update timesheet SET hours_worked = ? WHERE id = ? AND emp_id = ? date = ? ";
	            try {
	                ps = connection.prepareStatement(query);
	                ps.setInt(1, id);
	                ps.setInt(2, emp_id);
	                ps.setDate(3, java.sql.Date.valueOf(date));
	                ps.setInt(4, hoursWorked);
	                ps.executeUpdate();
	            } catch (SQLException e) {
	                System.out.println("Error adding timesheet to database: " + e.getMessage());
	            }
	        }

	        System.out.println("Time sheet data from file " + fileName + " added successfully.");
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: file " + fileName + " not found.");
	    } catch (DateTimeParseException e) {
	        System.out.println("Error: invalid date format in file " + fileName + ".");
	    } catch (NumberFormatException e) {
	        System.out.println("Error: invalid numeric format in file " + fileName + ".");
	    }finally {
	    	if(connection!=null) {
	    		connection.close();
	    	}if(ps!=null) {
	    		ps.close();
	    	}if(scanner!=null) {
	    		scanner.close();
	    	}
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
